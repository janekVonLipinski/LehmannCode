package LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.GaussAlgorithmus;

import LehmannCode.Matrix.MatrizenVerfahren.Matrix;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.ExceptionHandler.ExceptionHandler;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.LGSLoeser;
import LehmannCode.Vektor.Vektor;

public class GaussAlgorithmus implements LGSLoeser {
    private final Stufenform stufenform;
    private final Diagonalform diagonalform;
    private final ExceptionHandler exceptionHandler;
    private final GaussHilfsFunktionen gaussHilfsFunktionen;

    public GaussAlgorithmus(Stufenform stufenform, Diagonalform diagonalform, ExceptionHandler exceptionHandler, GaussHilfsFunktionen gaussHilfsFunktionen) {
        this.stufenform = stufenform;
        this.diagonalform = diagonalform;
        this.exceptionHandler = exceptionHandler;
        this.gaussHilfsFunktionen = gaussHilfsFunktionen;
    }

    @Override
    public Vektor loeseGleichungssystem(Matrix koeffizientenMatrix, Vektor loesungsVektor) {
        Vektor vektor = new Vektor(loesungsVektor);
        Matrix matrix = new Matrix(koeffizientenMatrix);

        exceptionHandler.istGleichungssystemValide(koeffizientenMatrix, vektor);

        Matrix erweitereKoeffizientenMatrix = gaussHilfsFunktionen.erzeugeErweiterteKoeffizientenMatrix(matrix, vektor);

        Matrix dreiecksMatrix = formeInStufenFormUm(erweitereKoeffizientenMatrix);
        Matrix diagonalMatrix = formeMatrixInDiagonalFormUm(dreiecksMatrix);

        return berechneXVektorAusDiagonalMatrixUndLoesungsvektor(diagonalMatrix);
    }

    private Vektor berechneXVektorAusDiagonalMatrixUndLoesungsvektor(Matrix diagonalMatrix) {
        double[][] diagonalmatrixArray = diagonalMatrix.getMatrix();
        double[] loesung = new double[diagonalmatrixArray.length];

        for (int zeile = 0; zeile < diagonalmatrixArray.length; zeile++) {
            loesung[zeile] = diagonalmatrixArray[zeile][diagonalMatrix.getAnzahlSpalten() - 1] / diagonalmatrixArray[zeile][zeile];
        }

        return new Vektor(loesung);
    }

    private Matrix formeMatrixInDiagonalFormUm(Matrix dreiecksMatrix) {
        return diagonalform.formeMatrixInDiagonalFormUm(dreiecksMatrix);
    }

    private Matrix formeInStufenFormUm(Matrix matrix) {
        return stufenform.formeMatrixInStufenformUm(matrix);
    }
}
