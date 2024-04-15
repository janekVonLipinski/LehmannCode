package LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.Determinantenverfahren;

import LehmannCode.Matrix.MatrizenVerfahren.Matrix;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.ExceptionHandler.ExceptionHandler;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.LGSLoeser;
import LehmannCode.Vektor.Vektor;

public class DeterminatenVerfahren implements LGSLoeser {

    private final ExceptionHandler exceptionHandler;

    public DeterminatenVerfahren(ExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    @Override
    public Vektor loeseGleichungssystem(Matrix koeffizientenMatrix, Vektor loesungsVektor) {
        int anzahlZeilen = koeffizientenMatrix.getAnzahlZeilen();
        double[] xVektor = new double[anzahlZeilen];

        exceptionHandler.istGleichungssystemValide(koeffizientenMatrix, loesungsVektor);
        exceptionHandler.istGleichungssystemEindeutigLoesbar(koeffizientenMatrix);

        double determinanteVonKoeffizientenMatrix = koeffizientenMatrix.getDeterminante();

        for (int i = 0; i < anzahlZeilen; i++) {
            Matrix matrixMitEingetztemVektor = setzeVektorInMatrixEin(koeffizientenMatrix, loesungsVektor, i);
            xVektor[i] = matrixMitEingetztemVektor.getDeterminante() / determinanteVonKoeffizientenMatrix;
        }

        return new Vektor(xVektor);
    }

    protected Matrix setzeVektorInMatrixEin(Matrix koeffizientMatrix, Vektor loesungsVektor, int spaltenIndex) {
        Matrix matrix = new Matrix(koeffizientMatrix);
        double[][] matrixArray = matrix.getMatrix().clone();
        double[] loesungVektorArray = loesungsVektor.getVektor();

        for (int i = 0; i < matrixArray.length; i++) {
                matrixArray[i][spaltenIndex] = loesungVektorArray[i];
        }

        return new Matrix(matrixArray);
    }
}
