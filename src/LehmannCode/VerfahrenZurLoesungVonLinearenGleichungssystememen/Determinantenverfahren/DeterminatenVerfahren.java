package LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.Determinantenverfahren;

import LehmannCode.Matrizen.IMatrix;
import LehmannCode.Matrizen.MatrixImplementierung.Matrix;
import LehmannCode.Vektor.IVektor;
import LehmannCode.Vektor.Vektor;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.ExceptionHandling.ExceptionHandler;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.LGSLoeser;

public class DeterminatenVerfahren implements LGSLoeser {

    private final ExceptionHandler exceptionHandler;

    public DeterminatenVerfahren(ExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    @Override
    public IVektor loeseGleichungssystem(IMatrix koeffizientenMatrix, IVektor loesungsVektor) {
        int anzahlZeilen = koeffizientenMatrix.getAnzahlZeilen();
        double[] xVektor = new double[anzahlZeilen];

        exceptionHandler.istGleichungssystemValide(koeffizientenMatrix, loesungsVektor);
        exceptionHandler.istGleichungssystemEindeutigLoesbar(koeffizientenMatrix);

        double determinanteVonKoeffizientenMatrix = koeffizientenMatrix.getDeterminante();

        for (int i = 0; i < anzahlZeilen; i++) {
            IMatrix matrixMitEingetztemVektor = setzeVektorInMatrixEin(koeffizientenMatrix, loesungsVektor, i);
            xVektor[i] = matrixMitEingetztemVektor.getDeterminante() / determinanteVonKoeffizientenMatrix;
        }

        return new Vektor(xVektor);
    }

    protected IMatrix setzeVektorInMatrixEin(IMatrix koeffizientMatrix, IVektor loesungsVektor, int spaltenIndex) {
        Matrix matrix = new Matrix((Matrix) koeffizientMatrix);
        double[][] matrixArray = matrix.getMatrix();
        double[] loesungVektorArray = loesungsVektor.getVektor();

        for (int i = 0; i < matrixArray.length; i++) {
                matrixArray[i][spaltenIndex] = loesungVektorArray[i];
        }

        return new Matrix(matrixArray);
    }
}
