package TorusProjekt.Matrix.VerfahrenZurLoesungVonLinearenGleichungssystememen.GaussAlgorithmus;

import TorusProjekt.Matrix.MatrizenVerfahren.Matrix;
import TorusProjekt.Vektor.Vektor;

public class Diagonalform {

    private final GaussHilfsFunktionen gaussHilfsFunktionen;

    public Diagonalform(GaussHilfsFunktionen gaussHilfsFunktionen) {
        this.gaussHilfsFunktionen = gaussHilfsFunktionen;
    }

    protected Matrix getDiagonalMatrix(Matrix matrix, Vektor vektor) {

        double[][] matrixArray = matrix.getMatrix();
        double[] vektorArray = vektor.getVektorWerte();

        for (int j = matrixArray.length - 1; j >= 0; j--) {
            for (int k = 1; j - k >= 0; k++) {
                double[] zeileVonDerSubtrahiertWird = matrixArray[j-k];
                double[] zeileDieSubtrahiertWird = matrixArray[j];

                double koeffizient = gaussHilfsFunktionen.getKoeffizient(
                        zeileDieSubtrahiertWird[j],
                        zeileVonDerSubtrahiertWird[j]
                );

                matrixArray[j - k] = gaussHilfsFunktionen.subtrahiereZeile(zeileDieSubtrahiertWird,
                        zeileVonDerSubtrahiertWird, koeffizient);

                vektorArray[j - k] = vektorArray[j - k] - koeffizient * vektorArray[j];
            }
        }
        return new Matrix(matrixArray);
    }
}
