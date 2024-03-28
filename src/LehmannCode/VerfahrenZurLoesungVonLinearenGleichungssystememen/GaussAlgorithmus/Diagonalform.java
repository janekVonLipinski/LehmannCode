package LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.GaussAlgorithmus;

import LehmannCode.Matrix.MatrizenVerfahren.Matrix;
import LehmannCode.Vektor.Vektor;

public class Diagonalform {

    private final GaussHilfsFunktionen gaussHilfsFunktionen;

    public Diagonalform(GaussHilfsFunktionen gaussHilfsFunktionen) {
        this.gaussHilfsFunktionen = gaussHilfsFunktionen;
    }

    protected Matrix getDiagonalMatrix(Matrix matrix, Vektor vektor) {

        double[][] matrixArray = matrix.getMatrix();
        double[] vektorArray = vektor.getVektorWerte();

        for (int j = matrixArray.length - 1; j >= 0; j--) {
            subtrahiereJteZeileVonAllenDarueberLiegendenZeilenSodassWerteNullWerden(j, matrixArray, vektorArray);
        }
        return new Matrix(matrixArray);
    }

    private void subtrahiereJteZeileVonAllenDarueberLiegendenZeilenSodassWerteNullWerden(
            int j, double[][] matrixArray, double[] vektorArray) {
        for (int k = 1; j - k >= 0; k++) {
            double[] zeileVonDerSubtrahiertWird = matrixArray[j -k];
            double[] zeileDieSubtrahiertWird = matrixArray[j];

            subtrahiereVonJMinusKteZeileSodassElementNullWird(
                    zeileDieSubtrahiertWird, j, zeileVonDerSubtrahiertWird, matrixArray, k, vektorArray);
        }
    }

    private void subtrahiereVonJMinusKteZeileSodassElementNullWird(
            double[] zeileDieSubtrahiertWird, int j, double[] zeileVonDerSubtrahiertWird,
            double[][] matrixArray, int k, double[] vektorArray) {
        double koeffizient = gaussHilfsFunktionen.getKoeffizient(
                zeileDieSubtrahiertWird[j],
                zeileVonDerSubtrahiertWird[j]
        );

        matrixArray[j - k] = gaussHilfsFunktionen.subtrahiereZeile(zeileDieSubtrahiertWird,
                zeileVonDerSubtrahiertWird, koeffizient);

        vektorArray[j - k] = vektorArray[j - k] - koeffizient * vektorArray[j];
    }
}
