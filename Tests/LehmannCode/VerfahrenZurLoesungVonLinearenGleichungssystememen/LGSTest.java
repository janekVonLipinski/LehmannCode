package LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen;

import LehmannCode.Matrix.MatrixUtil.MatrixHilfsfunktionen;
import LehmannCode.Matrix.MatrizenVerfahren.Matrix;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.Determinantenverfahren.DeterminatenVerfahren;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.ExceptionHandler.ExceptionHandler;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.GaussAlgorithmus.Diagonalform;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.GaussAlgorithmus.GaussAlgorithmus;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.GaussAlgorithmus.GaussHilfsFunktionen;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.GaussAlgorithmus.Stufenform;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.InverseMatrixVerfahren.InverseMatrixVerfahren;
import LehmannCode.Vektor.Vektor;
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
    private final DeterminatenVerfahren determinatenVerfahren = new DeterminatenVerfahren(new ExceptionHandler());
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