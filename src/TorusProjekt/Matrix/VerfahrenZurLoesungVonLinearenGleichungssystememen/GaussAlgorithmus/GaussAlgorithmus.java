package TorusProjekt.Matrix.VerfahrenZurLoesungVonLinearenGleichungssystememen.GaussAlgorithmus;

import TorusProjekt.Matrix.MatrizenVerfahren.Matrix;
import TorusProjekt.Matrix.VerfahrenZurLoesungVonLinearenGleichungssystememen.ExceptionHandler.ExceptionHandler;
import TorusProjekt.Matrix.VerfahrenZurLoesungVonLinearenGleichungssystememen.LGSLoeser;
import TorusProjekt.Vektor.Vektor;

import java.util.Arrays;

public class GaussAlgorithmus implements LGSLoeser {
    private final Stufenform stufenform;
    private final Diagonalform diagonalform;
    private final ExceptionHandler exceptionHandler;

    public GaussAlgorithmus(Stufenform stufenform, Diagonalform diagonalform, ExceptionHandler exceptionHandler) {
        this.stufenform = stufenform;
        this.diagonalform = diagonalform;
        this.exceptionHandler = exceptionHandler;
    }

    @Override
    public Vektor loeseGleichungssystem(Matrix koeffizientenMatrix, Vektor loesungsVektor) {
        Vektor vektor = new Vektor(loesungsVektor);
        Matrix matrix = new Matrix(koeffizientenMatrix);

        if (!exceptionHandler.istValide(koeffizientenMatrix, vektor)) {
            return null;
        }

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
