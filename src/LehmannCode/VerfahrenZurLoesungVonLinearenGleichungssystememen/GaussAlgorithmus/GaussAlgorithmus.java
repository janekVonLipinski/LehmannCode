package LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.GaussAlgorithmus;

import LehmannCode.Matrix.MatrizenVerfahren.Matrix;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.ExceptionHandler.ExceptionHandler;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.LGSLoeser;
import LehmannCode.Vektor.Vektor;

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

        exceptionHandler.istGleichungssystemValide(koeffizientenMatrix, vektor);

        Matrix dreiecksMatrix = formeMatrixInStufenformUmUndFormeVektorGenausoUm(matrix, vektor);
        Matrix diagonalMatrix = formeMatrixInDiagonalFormUmUndFormeVektorGenausoUm(dreiecksMatrix, vektor);

        return berechneXVektorAusDiagonalMatrixUndLoesungsvektor(vektor, diagonalMatrix);
    }

    private Vektor berechneXVektorAusDiagonalMatrixUndLoesungsvektor(Vektor loesungsVektor, Matrix diagonalMatrix) {
        double[] vektorArray = loesungsVektor.getVektor();
        double[][] diagonalmatrixArray = diagonalMatrix.getMatrix();
        double[] loesung = new double[diagonalmatrixArray.length];

        for (int i = 0; i < diagonalmatrixArray.length; i++) {
            loesung[i] = vektorArray[i] / diagonalmatrixArray[i][i];
        }

        return new Vektor(loesung);
    }

    private Matrix formeMatrixInDiagonalFormUmUndFormeVektorGenausoUm(Matrix dreiecksMatrix, Vektor vektor) {
        return diagonalform.formeMatrixInDiagonalFormUm(dreiecksMatrix, vektor);
    }

    private Matrix formeMatrixInStufenformUmUndFormeVektorGenausoUm(Matrix matrix, Vektor vektor) {
        return stufenform.formeMatrixInStufenformUm(matrix, vektor);
    }
}
