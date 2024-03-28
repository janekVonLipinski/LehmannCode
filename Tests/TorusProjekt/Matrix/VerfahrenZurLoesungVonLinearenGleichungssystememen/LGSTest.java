package TorusProjekt.Matrix.VerfahrenZurLoesungVonLinearenGleichungssystememen;

import TorusProjekt.Matrix.MatrixUtil.MatrixHilfsfunktionen;
import TorusProjekt.Matrix.MatrizenVerfahren.Matrix;
import TorusProjekt.Matrix.VerfahrenZurLoesungVonLinearenGleichungssystememen.Determinantenverfahren.DeterminatenVerfahren;
import TorusProjekt.Matrix.VerfahrenZurLoesungVonLinearenGleichungssystememen.ExceptionHandler.ExceptionHandler;
import TorusProjekt.Matrix.VerfahrenZurLoesungVonLinearenGleichungssystememen.GaussAlgorithmus.Diagonalform;
import TorusProjekt.Matrix.VerfahrenZurLoesungVonLinearenGleichungssystememen.GaussAlgorithmus.GaussAlgorithmus;
import TorusProjekt.Matrix.VerfahrenZurLoesungVonLinearenGleichungssystememen.GaussAlgorithmus.GaussHilfsFunktionen;
import TorusProjekt.Matrix.VerfahrenZurLoesungVonLinearenGleichungssystememen.GaussAlgorithmus.Stufenform;
import TorusProjekt.Matrix.VerfahrenZurLoesungVonLinearenGleichungssystememen.InverseMatrixVerfahren.InverseMatrixVerfahren;
import TorusProjekt.Vektor.Vektor;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LGSTest {

    private final GaussAlgorithmus gaussAlgorithmus = new GaussAlgorithmus(
            new Stufenform(new GaussHilfsFunktionen()),
            new Diagonalform(new GaussHilfsFunktionen()),
            new ExceptionHandler());
    private final DeterminatenVerfahren determinatenVerfahren = new DeterminatenVerfahren();
    private final InverseMatrixVerfahren inverseMatrixVerfahren = new InverseMatrixVerfahren(new ExceptionHandler(), new MatrixHilfsfunktionen());

    @ParameterizedTest
    @MethodSource("generateTestStream")
    void Given_GaussAlgorithmus(Matrix koeffizientenMatrix, Vektor loeusngsvektor, Vektor xVektor) {
        Vektor loesungMitGauss = gaussAlgorithmus.loeseGleichungssystem(koeffizientenMatrix,
                loeusngsvektor);
        assertTrue(MethodenZumTesten.sindWerteKleinerAlsDiffernz(xVektor, loesungMitGauss));
    }

    @ParameterizedTest
    @MethodSource("generateTestStream")
    void Given_Determinantenverfahren(Matrix koeffizientenMatrix, Vektor loeusngsvektor, Vektor xVektor) {
        Vektor loesungMitDet = determinatenVerfahren.loeseGleichungssystem(koeffizientenMatrix,
                loeusngsvektor);
        assertTrue(MethodenZumTesten.sindWerteKleinerAlsDiffernz(xVektor, loesungMitDet));
    }

    @ParameterizedTest
    @MethodSource("generateTestStream")
    void Given_inverse_matrixVerfahren(Matrix koeffizientenMatrix, Vektor loeusngsvektor, Vektor xVektor) {
        Vektor loesungMitInverser = inverseMatrixVerfahren.loeseGleichungssystem(koeffizientenMatrix, loeusngsvektor);
        System.out.println(xVektor);
        System.out.println(loesungMitInverser);
        assertTrue(MethodenZumTesten.sindWerteKleinerAlsDiffernz(xVektor, loesungMitInverser));
    }

    private static Stream<Arguments> generateTestStream() {
        return IntStream.range(2, 12)
                .mapToObj(i -> {
                    Matrix matrix = MethodenZumTesten.generateQuadratischeTestMatrix(i);
                    Vektor xVektor = MethodenZumTesten.generateXVektor(i);
                    Vektor loesungsVektor = xVektor.multipliziere(matrix);
                    return Arguments.of(matrix, loesungsVektor, xVektor);
                });
    }

}