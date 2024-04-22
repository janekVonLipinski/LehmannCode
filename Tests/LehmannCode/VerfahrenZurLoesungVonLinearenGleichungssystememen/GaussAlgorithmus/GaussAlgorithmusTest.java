package LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.GaussAlgorithmus;

import LehmannCode.Matrix.MatrizenVerfahren.Matrix;
import LehmannCode.Vektor.Vektor;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.Determinantenverfahren.DeterminatenVerfahren;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.ExceptionHandler.ExceptionHandler;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.MethodenZumTesten;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GaussAlgorithmusTest {

    private final GaussHilfsFunktionen gaussHilfsFunktionen = new GaussHilfsFunktionen();
    private final GaussAlgorithmus gaussAlgorithmus = new GaussAlgorithmus(
            new Stufenform(gaussHilfsFunktionen), new Diagonalform(gaussHilfsFunktionen),
            new ExceptionHandler(), gaussHilfsFunktionen);

    @Test
    void Given_element_Auf_Diagonale_Ist_Null_Then_LGS_wurde_korrekt_geloest() {
        //TODO Mehtode für das Testen von Vekoten schreiben
        double[][] matrixArray = {{2, 0.5, -6}, {1, 0, -0.75}, {(double) 2/3, -1, 8}};
        double[] vektorArray = {0, 2, 7};
        double[] loesung = {5.5, 34, 4.666666666666666};
        double[] abweichungsArray = new double[3];
        Arrays.fill(abweichungsArray, 0.01);

        Matrix m = new Matrix(matrixArray);
        Vektor v = new Vektor(vektorArray);
        Vektor l = new Vektor(loesung);

        Vektor loesungMitGauss = gaussAlgorithmus.loeseGleichungssystem(m, v);

        assertTrue(MethodenZumTesten.sindWerteKleinerAlsDiffernz(
                loesungMitGauss.subtrahiere(l), new Vektor(abweichungsArray)));
    }

    @Test
    void Given_Gleichung_ist_nicht_loesbar_Then_Exception_was_thrown() {

    }

}