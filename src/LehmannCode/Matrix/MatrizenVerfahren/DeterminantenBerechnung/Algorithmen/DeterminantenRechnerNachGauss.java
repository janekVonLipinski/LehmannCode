package LehmannCode.Matrix.MatrizenVerfahren.DeterminantenBerechnung.Algorithmen;

import LehmannCode.Matrix.MatrizenVerfahren.DeterminantenBerechnung.Determinante;
import LehmannCode.Matrix.MatrizenVerfahren.Matrix;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.GaussAlgorithmus.Stufenform;

public class DeterminantenRechnerNachGauss implements Determinante {

    private final Stufenform stufenform;

    public DeterminantenRechnerNachGauss(Stufenform stufenform) {
        this.stufenform = stufenform;
    }

    @Override
    public double getDeterminante(Matrix m) {
        Matrix matrix = new Matrix(m);
        Matrix dreiecksMatrix = stufenform.formeMatrixInStufenformUm(matrix);
        double[][] stufenMatrixArray = dreiecksMatrix.getMatrix();

        double ergebnis = 1;
        for (int i = 0; i < dreiecksMatrix.getAnzahlZeilen(); i++) {
            ergebnis *= stufenMatrixArray[i][i];
        }

        return ergebnis;
    }
}
