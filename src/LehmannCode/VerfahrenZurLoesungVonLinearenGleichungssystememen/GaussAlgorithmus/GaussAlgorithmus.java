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

        werfeExceptionFallsGleichungssystemNichtEindeutigLoesbarIst(koeffizientenMatrix, vektor);

        Matrix erweitereKoeffizientenMatrix = erzeugeErweiterteKoeffizientenMatrix(matrix, vektor);
        Matrix dreiecksMatrix = formeInStufenFormUm(erweitereKoeffizientenMatrix);
        Matrix diagonalMatrix = formeInDiagonalFormUm(dreiecksMatrix);

        return berechneXVektorAusDiagonalMatrixUndLoesungsvektor(diagonalMatrix);
    }

    private Vektor berechneXVektorAusDiagonalMatrixUndLoesungsvektor(Matrix diagonalMatrix) {
        double[][] diagonalmatrixArray = diagonalMatrix.getMatrix();
        double[] xVektor = new double[diagonalmatrixArray.length];

        for (int zeile = 0; zeile < diagonalmatrixArray.length; zeile++) {

            double elementVonVektor = diagonalmatrixArray[zeile][diagonalMatrix.getAnzahlSpalten() - 1];
            double elementVonDiagonalMatrix = diagonalmatrixArray[zeile][zeile];

            xVektor[zeile] = elementVonVektor / elementVonDiagonalMatrix;
        }

        return new Vektor(xVektor);
    }

    private Matrix erzeugeErweiterteKoeffizientenMatrix(Matrix matrix, Vektor vektor) {
        return gaussHilfsFunktionen.erzeugeErweiterteKoeffizientenMatrix(matrix, vektor);
    }

    private void werfeExceptionFallsGleichungssystemNichtEindeutigLoesbarIst(Matrix koeffizientenMatrix, Vektor vektor) {
        exceptionHandler.istGleichungssystemValide(koeffizientenMatrix, vektor);
    }

    private Matrix formeInDiagonalFormUm(Matrix dreiecksMatrix) {
        return diagonalform.formeMatrixInDiagonalFormUm(dreiecksMatrix);
    }

    private Matrix formeInStufenFormUm(Matrix matrix) {
        return stufenform.formeMatrixInStufenformUm(matrix);
    }
}
