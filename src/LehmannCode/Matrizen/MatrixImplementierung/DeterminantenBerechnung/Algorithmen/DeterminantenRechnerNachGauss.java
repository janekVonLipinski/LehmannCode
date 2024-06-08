package LehmannCode.Matrizen.MatrixImplementierung.DeterminantenBerechnung.Algorithmen;

import LehmannCode.Matrizen.IMatrix;
import LehmannCode.Matrizen.MatrixImplementierung.DeterminantenBerechnung.Determinante;
import LehmannCode.Matrizen.MatrixImplementierung.Matrix;

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
