package TorusProjekt.Matrix.VerfahrenZurLoesungVonLinearenGleichungssystememen.GaussAlgorithmus;

import TorusProjekt.Matrix.MatrizenVerfahren.Matrix;
import TorusProjekt.Matrix.VerfahrenZurLoesungVonLinearenGleichungssystememen.LGSLoeser;
import TorusProjekt.Vektor.Vektor;

import java.util.Arrays;

public class GaussAlgorithmus implements LGSLoeser {
    private final Stufenform stufenform;
    private final Diagonalform diagonalform;

    public GaussAlgorithmus(Stufenform stufenform, Diagonalform diagonalform) {
        this.stufenform = stufenform;
        this.diagonalform = diagonalform;
    }

    public Vektor loeseGleichungssystem(Matrix koeffizientenMatrix, Vektor loesungsVektor) {

        if (koeffizientenMatrix.getAnzahlSpalten() != koeffizientenMatrix.getAnzahlZeilen()) {
            throw new IllegalArgumentException("Matrix muss quadratisch sein");
        }

        if (koeffizientenMatrix.getDeterminante() == 0) {
            throw new IllegalArgumentException("Gleichung ist nicht eindeutig lösbar");
        }

        Vektor vektor = new Vektor(loesungsVektor);
        Matrix matrix = new Matrix(koeffizientenMatrix);

        Matrix dreiecksMatrix = stufenform.getStufenForm(matrix, vektor);
        Matrix diagonalMatrix = diagonalform.getDiagonalMatrix(dreiecksMatrix, vektor);
        return berechneXVektorAusDiagonalMatrixUndLoesungsvektor(vektor, diagonalMatrix);
    }

    private Vektor berechneXVektorAusDiagonalMatrixUndLoesungsvektor(Vektor loesungsVektor, Matrix diagonalMatrix) {
        double[] vektorArray = loesungsVektor.getVektorWerte();
        double[][] diagonalmatrixArray = diagonalMatrix.getMatrix();
        double[] loesung = new double[diagonalmatrixArray.length];
        for (int i = 0; i < diagonalmatrixArray.length; i++) {
            loesung[i] = vektorArray[i] / diagonalmatrixArray[i][i];
        }
        return new Vektor(loesung);
    }
}
