package LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.GaussAlgorithmus;

import LehmannCode.Matrix.MatrizenVerfahren.Matrix;
import LehmannCode.Vektor.Vektor;

public class Stufenform {

    private final GaussHilfsFunktionen gaussHilfsFunktionen;

    public Stufenform(GaussHilfsFunktionen gaussHilfsFunktionen) {
        this.gaussHilfsFunktionen = gaussHilfsFunktionen;
    }

    protected Matrix formeMatrixInStufenformUm(Matrix matrix, Vektor vektor) {

        double[] vektorArray = vektor.getVektor();
        double[][] matrixArray = matrix.getMatrix();

        for (int j = 0; j < matrixArray.length; j++) {
            tauscheSolangeZeilenBisWertAufHauptDiagonaleNichtNullIst(matrixArray, j, vektorArray);
            subtrahiereJteZeileVonAllenDarunterStehendenZeilenSodassAlleWerteDarunterNullWerden(j, matrixArray, vektorArray);
        }
        return new Matrix(matrixArray);
    }

    private void subtrahiereJteZeileVonAllenDarunterStehendenZeilenSodassAlleWerteDarunterNullWerden(
            int j, double[][] matrixArray, double[] vektorArray) {
        for (int k = 1; j + k < matrixArray.length; k++) {
            double[] zeileVonDerSubtrahiertWird = matrixArray[j + k];
            double[] zeileDieSubtrahiertWird = matrixArray[j];

            subtrahiereVonJPlusKterZeileSodassElementNullWird(zeileDieSubtrahiertWird, j, zeileVonDerSubtrahiertWird,
                    matrixArray, k, vektorArray);
        }
    }

    private void subtrahiereVonJPlusKterZeileSodassElementNullWird(
            double[] zeileDieSubtrahiertWird, int j, double[] zeileVonDerSubtrahiertWird,
            double[][] matrixArray, int k, double[] vektorArray) {
        double koeffizient = gaussHilfsFunktionen.getKoeffizient(
                zeileDieSubtrahiertWird[j],
                zeileVonDerSubtrahiertWird[j]
        );

        matrixArray[j + k] = gaussHilfsFunktionen.subtrahiereZeile(zeileDieSubtrahiertWird,
                zeileVonDerSubtrahiertWird, koeffizient);

        vektorArray[j + k] = vektorArray[j + k] - koeffizient * vektorArray[j];
    }

    private void tauscheSolangeZeilenBisWertAufHauptDiagonaleNichtNullIst(double[][] matrixArray, int j, double[] vektorArray) {
        int i = 1;
        while (gaussHilfsFunktionen.istWertAufDiagonale0(matrixArray, j)) {
            gaussHilfsFunktionen.tausche(matrixArray, vektorArray, j, i);
            i++;
        }
    }
}
