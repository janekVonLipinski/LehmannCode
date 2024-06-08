package LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.GaussAlgorithmus;

import LehmannCode.Matrizen.IMatrix;
import LehmannCode.Matrizen.MatrixImplementierung.Matrix;
import LehmannCode.Vektor.IVektor;
import LehmannCode.Vektor.Vektor;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.ExceptionHandling.ExceptionHandler;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.LGSLoeser;

public class GaussAlgorithmus implements LGSLoeser {
    private final ExceptionHandler exceptionHandler;
    private final GaussHilfsFunktionen gaussHilfsFunktionen;

    public GaussAlgorithmus(ExceptionHandler exceptionHandler, GaussHilfsFunktionen gaussHilfsFunktionen) {
        this.exceptionHandler = exceptionHandler;
        this.gaussHilfsFunktionen = gaussHilfsFunktionen;
    }

    @Override
    public IVektor loeseGleichungssystem(IMatrix koeffizientenMatrix, IVektor loesungsVektor) {
        IVektor vektor = new Vektor((Vektor) loesungsVektor);
        IMatrix matrix = new Matrix((Matrix) koeffizientenMatrix);

        werfeExceptionFallsGleichungssystemNichtValideIst(matrix, vektor);

        IMatrix erweitereKoeffizientenMatrix = erzeugeErweiterteKoeffizientenMatrix(matrix, vektor);
        IMatrix dreiecksMatrix = erweitereKoeffizientenMatrix.getStufenForm();

        //werfeExceptionFallsGleichungssystemNichtEindeutigLoesbarIst(dreiecksMatrix);

        IMatrix diagonalMatrix = dreiecksMatrix.getDiagonalForm();

        return berechneXVektorAusDiagonalMatrixUndLoesungsvektor(diagonalMatrix);
    }

    private IVektor berechneXVektorAusDiagonalMatrixUndLoesungsvektor(IMatrix diagonalMatrix) {
        double[][] diagonalmatrixArray = diagonalMatrix.getMatrix();
        double[] xVektor = new double[diagonalmatrixArray.length];

        for (int zeile = 0; zeile < diagonalmatrixArray.length; zeile++) {

            double elementVonVektorInZeile = diagonalmatrixArray[zeile][diagonalMatrix.getAnzahlSpalten() - 1];
            double elementVonDiagonalMatrixInZeile = diagonalmatrixArray[zeile][zeile];

            xVektor[zeile] = elementVonVektorInZeile / elementVonDiagonalMatrixInZeile;
        }

        return new Vektor(xVektor);
    }

    private IMatrix erzeugeErweiterteKoeffizientenMatrix(IMatrix matrix, IVektor vektor) {
        return gaussHilfsFunktionen.erzeugeErweiterteKoeffizientenMatrix(matrix, vektor);
    }

    private void werfeExceptionFallsGleichungssystemNichtValideIst(IMatrix koeffizientenMatrix, IVektor vektor) {
        exceptionHandler.istGleichungssystemValide(koeffizientenMatrix, vektor);
    }

    private void werfeExceptionFallsGleichungssystemNichtEindeutigLoesbarIst(IMatrix matrix) {
        exceptionHandler.istGleichungssystemEindeutigLoesbar(matrix);
    }
}
