package LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.GaussAlgorithmus;

import LehmannCode.Matrix.MatrizenVerfahren.Matrix;
import LehmannCode.Vektor.Vektor;

public class Diagonalform {

    private final GaussHilfsFunktionen gaussHilfsFunktionen;

    public Diagonalform(GaussHilfsFunktionen gaussHilfsFunktionen) {
        this.gaussHilfsFunktionen = gaussHilfsFunktionen;
    }

    protected Matrix formeMatrixInDiagonalFormUm(Matrix matrix) {

        double[][] matrixArray = matrix.getMatrix();

        for (int j = matrixArray.length - 1; j >= 0; j--) {
            subtrahiereJteZeileVonAllenDarueberLiegendenZeilenSodassWerteNullWerden(j, matrixArray);
        }
        return new Matrix(matrixArray);
    }

    private void subtrahiereJteZeileVonAllenDarueberLiegendenZeilenSodassWerteNullWerden(
            int j, double[][] matrixArray) {
        for (int k = 1; j - k >= 0; k++) {
            double[] zeileVonDerSubtrahiertWird = matrixArray[j -k];
            double[] zeileDieSubtrahiertWird = matrixArray[j];

            subtrahiereVonJMinusKteZeileSodassElementNullWird(
                    zeileDieSubtrahiertWird, j, zeileVonDerSubtrahiertWird, matrixArray, k);
        }
    }

    private void subtrahiereVonJMinusKteZeileSodassElementNullWird(
            double[] zeileDieSubtrahiertWird, int j, double[] zeileVonDerSubtrahiertWird,
            double[][] matrixArray, int k) {
        double koeffizient = zeileVonDerSubtrahiertWird[j] / zeileDieSubtrahiertWird[j];

        matrixArray[j - k] = gaussHilfsFunktionen.subtrahiereZeile(zeileDieSubtrahiertWird,
                zeileVonDerSubtrahiertWird, koeffizient);
    }
}
