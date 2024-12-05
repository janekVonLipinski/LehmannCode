package LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.GaussAlgorithmus;

import LehmannCode.Matrizen.IMatrix;
import LehmannCode.Matrizen.MatrixImplementierung.Matrix;
import LehmannCode.Vektor.IVektor;
import LehmannCode.Vektor.Vektor;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.ExceptionHandling.ExceptionHandler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GaussAlgorithmusTest {

    private final GaussAlgorithmus gaussAlgorithmus = new GaussAlgorithmus(
            new ExceptionHandler());

    @Test
    void Given_element_Auf_Diagonale_Ist_Null_Then_LGS_wurde_korrekt_geloest() {
        double[][] matrixArray = {{2, 0.5, -6}, {1, 0, -0.75}, {(double) 2/3, -1, 8}};
        double[] vektorArray = {0, 2, 7};
        double[] loesung = {5.5, 34, 4.666666666666666};

        IMatrix m = new Matrix(matrixArray);
        IVektor v = new Vektor(vektorArray);
        IVektor l = new Vektor(loesung);

        IVektor loesungMitGauss = gaussAlgorithmus.loeseGleichungssystem(m, v);

        assertArrayEquals(l.getVektor(), loesungMitGauss.getVektor(), 0.001);
    }

    @Test
    void Given_LGS_nicht_loesbar_Then_exception_was_thrown() {
        double[][] matrixArray = {{2, 0.5, -6}, {1, 0, -0.75}, {0, 0, 0}};
        double[] vektorArray = {0, 2, 7};

        IMatrix m = new Matrix(matrixArray);
        IVektor v = new Vektor(vektorArray);

        assertThrows(IllegalArgumentException.class, () -> gaussAlgorithmus.loeseGleichungssystem(m, v));
    }

}