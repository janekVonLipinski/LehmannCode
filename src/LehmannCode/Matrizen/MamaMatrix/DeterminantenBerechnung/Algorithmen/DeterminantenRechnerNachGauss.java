package LehmannCode.Matrizen.MamaMatrix.DeterminantenBerechnung.Algorithmen;

import LehmannCode.Matrizen.IMatrix;
import LehmannCode.Matrizen.MamaMatrix.DeterminantenBerechnung.Determinante;
import LehmannCode.Matrizen.MamaMatrix.Matrix;

public class DeterminantenRechnerNachGauss implements Determinante {

    @Override
    public double getDeterminante(IMatrix m) {
        IMatrix matrix = new Matrix((Matrix) m);
        IMatrix dreiecksMatrix = matrix.getStufenForm();
        double[][] stufenMatrixArray = dreiecksMatrix.getMatrix();

        double ergebnis = 1;
        for (int i = 0; i < dreiecksMatrix.getAnzahlZeilen(); i++) {
            ergebnis *= stufenMatrixArray[i][i];
        }

        return ergebnis;
    }
}
