package LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.GaussAlgorithmus;

import LehmannCode.Matrizen.IMatrix;
import LehmannCode.Matrizen.MatrixImplementierung.Matrix;
import LehmannCode.Vektor.IVektor;
import LehmannCode.Vektor.Vektor;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.ExceptionHandling.ExceptionHandler;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.LGSLoeser;

public class GaussAlgorithmus implements LGSLoeser {
    private final ExceptionHandler exceptionHandler;

    public GaussAlgorithmus(ExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    @Override
    public IVektor loeseGleichungssystem(IMatrix koeffizientenMatrix, IVektor loesungsVektor) {
        IVektor vektor = new Vektor((Vektor) loesungsVektor);
        IMatrix matrix = new Matrix((Matrix) koeffizientenMatrix);

        werfeExceptionFallsGleichungssystemNichtValideIst(matrix, vektor);

        IMatrix erweitereKoeffizientenMatrix = erzeugeErweiterteKoeffizientenMatrix(matrix, vektor);
        IMatrix dreiecksMatrix = erweitereKoeffizientenMatrix.getStufenForm();

        werfeExceptionFallsGleichungssystemNichtEindeutigLoesbarIst(dreiecksMatrix);

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

    private IMatrix erzeugeErweiterteKoeffizientenMatrix(IMatrix m, IVektor v) {
        double[][] matrix = new Matrix((Matrix) m).getMatrix();
        double[] vektor = new Vektor((Vektor) v).getVektor();

        double[][] erweiterteKoeffizientenMatrix = new double[m.getAnzahlZeilen()][m.getAnzahlSpalten() + 1];

        for (int zeile = 0; zeile < m.getAnzahlZeilen(); zeile++) {
            if (m.getAnzahlSpalten() >= 0)
                System.arraycopy(matrix[zeile], 0, erweiterteKoeffizientenMatrix[zeile], 0, m.getAnzahlSpalten());
            erweiterteKoeffizientenMatrix[zeile][m.getAnzahlSpalten()] = vektor[zeile];
        }

        return new Matrix(erweiterteKoeffizientenMatrix);
    }

    private void werfeExceptionFallsGleichungssystemNichtValideIst(IMatrix koeffizientenMatrix, IVektor vektor) {
        exceptionHandler.istGleichungssystemValide(koeffizientenMatrix, vektor);
    }

    private void werfeExceptionFallsGleichungssystemNichtEindeutigLoesbarIst(IMatrix matrix) {
        exceptionHandler.hatMatrixNullZeile(matrix);
    }
}
