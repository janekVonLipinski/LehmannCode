package LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen;

import LehmannCode.Matrizen.IMatrix;
import LehmannCode.Matrizen.MatrixImplementierung.Matrix;
import LehmannCode.Vektor.IVektor;
import LehmannCode.Vektor.Vektor;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.Determinantenverfahren.DeterminatenVerfahren;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.ExceptionHandling.ExceptionHandler;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.GaussAlgorithmus.GaussAlgorithmus;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.GaussAlgorithmus.GaussHilfsFunktionen;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.InverseMatrixVerfahren.InverseMatrixVerfahren;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class LGSTest {

    private final GaussHilfsFunktionen gaussHilfsFunktionen = new GaussHilfsFunktionen();
    private final GaussAlgorithmus gaussAlgorithmus = new GaussAlgorithmus(new ExceptionHandler(), gaussHilfsFunktionen);
    private final DeterminatenVerfahren determinatenVerfahren = new DeterminatenVerfahren(new ExceptionHandler());
    private final InverseMatrixVerfahren inverseMatrixVerfahren = new InverseMatrixVerfahren(new ExceptionHandler());

    @ParameterizedTest
    @MethodSource("generateTestStream")
    void Given_GaussAlgorithmus(IMatrix koeffizientenMatrix, IVektor loeusngsvektor, IVektor xVektor) {
        IVektor loesungMitGauss = gaussAlgorithmus.loeseGleichungssystem(koeffizientenMatrix,
                loeusngsvektor);
        assertArrayEquals(xVektor.getVektor(), loesungMitGauss.getVektor(), 0.001);
    }

    @ParameterizedTest
    @MethodSource("generateTestStream")
    void Given_Determinantenverfahren(IMatrix koeffizientenMatrix, IVektor loeusngsvektor, IVektor xVektor) {
        IVektor loesungMitDet = determinatenVerfahren.loeseGleichungssystem(koeffizientenMatrix,
                loeusngsvektor);
        assertArrayEquals(xVektor.getVektor(), loesungMitDet.getVektor(), 0.001);
    }

    @ParameterizedTest
    @MethodSource("generateTestStream")
    void Given_inverse_matrixVerfahren(Matrix koeffizientenMatrix, Vektor loeusngsvektor, Vektor xVektor) {
        IVektor loesungMitInverser = inverseMatrixVerfahren.loeseGleichungssystem(koeffizientenMatrix, loeusngsvektor);
        assertArrayEquals(xVektor.getVektor(), loesungMitInverser.getVektor(), 0.001);
    }

    private static Stream<Arguments> generateTestStream() {
        return IntStream.range(2, 12)
                .mapToObj(i -> {
                    IMatrix matrix = MethodenZumTesten.generateQuadratischeTestMatrix(i);
                    IVektor xVektor = MethodenZumTesten.generateXVektor(i);
                    IVektor loesungsVektor = xVektor.multipliziere(matrix);
                    return Arguments.of(matrix, loesungsVektor, xVektor);
                });
    }

}