package LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.GaussAlgorithmus;

import LehmannCode.Matrix.MatrizenVerfahren.Matrix;
import LehmannCode.Vektor.Vektor;

public class Stufenform {

    private final GaussHilfsFunktionen gaussHilfsFunktionen;

    public Stufenform(GaussHilfsFunktionen gaussHilfsFunktionen) {
        this.gaussHilfsFunktionen = gaussHilfsFunktionen;
    }

    protected Matrix formeMatrixInStufenformUm(Matrix matrix) {
        double[][] matrixArray = matrix.getMatrix();

        for (int spaltenIndex = 0; spaltenIndex < matrixArray.length; spaltenIndex++) {

            tauscheSolangeZeilenBisWertAufDiagonaleNichtNullIst(matrixArray, spaltenIndex);
            eliminiereElementeUnterDiagonalElement(spaltenIndex, matrixArray);
        }

        return new Matrix(matrixArray);
    }

    private void eliminiereElementeUnterDiagonalElement(int spaltenIndex, double[][] matrixArray) {

        for (int naechsterZeilenIndex = 1;
             spaltenIndex + naechsterZeilenIndex < matrixArray.length;
             naechsterZeilenIndex++) {

            int indexVonNaechstemElementDasEliminiertWird = spaltenIndex + naechsterZeilenIndex;

            double[] zeileInDerEliminiertWird = matrixArray[indexVonNaechstemElementDasEliminiertWird];
            double[] zeileNachDerEntwickeltWird = matrixArray[spaltenIndex];

            double koeffizient = gaussHilfsFunktionen.getKoeffizient(
                    zeileNachDerEntwickeltWird[spaltenIndex],
                    zeileInDerEliminiertWird[spaltenIndex]
            );

            eliminiereNaechstesElementInMatrix(
                    matrixArray, indexVonNaechstemElementDasEliminiertWird,
                    zeileNachDerEntwickeltWird, zeileInDerEliminiertWird,  koeffizient
            );
        }
    }

    private void eliminiereNaechstesElementInMatrix(
            double[][] matrixArray, int indexVonNaechstemElementDasEliminiertWird,
            double[] zeileNachDerEntwickeltWird, double[] zeileInDerEliminiertWird, double koeffizient) {

        matrixArray[indexVonNaechstemElementDasEliminiertWird] = gaussHilfsFunktionen.subtrahiereZeile(zeileNachDerEntwickeltWird,
                zeileInDerEliminiertWird, koeffizient);
    }

    private void tauscheSolangeZeilenBisWertAufDiagonaleNichtNullIst(double[][] matrixArray, int zeileNachDerEntwickeltWird) {
        int andereZeile = 1;
        while (gaussHilfsFunktionen.istWertAufDiagonaleNull(matrixArray, zeileNachDerEntwickeltWird)) {
            gaussHilfsFunktionen.tauscheZeileVonMatrix(matrixArray, zeileNachDerEntwickeltWird, andereZeile);
            andereZeile++;
        }
    }
}
