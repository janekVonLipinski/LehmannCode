package LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.GaussAlgorithmus;

import LehmannCode.Matrix.MatrizenVerfahren.Matrix;
import LehmannCode.Vektor.Vektor;

public class Stufenform {

    private final GaussHilfsFunktionen gaussHilfsFunktionen;

    public Stufenform(GaussHilfsFunktionen gaussHilfsFunktionen) {
        this.gaussHilfsFunktionen = gaussHilfsFunktionen;
    }

    protected Matrix formeMatrixInStufenformUm(Matrix matrix, Vektor vektor) {

        double[] vektorArray = vektor.getVektor();
        double[][] matrixArray = matrix.getMatrix();

        for (int spaltenIndex = 0; spaltenIndex < matrixArray.length; spaltenIndex++) {

            tauscheSolangeZeilenBisWertAufHauptDiagonaleNichtNullIst(matrixArray, spaltenIndex, vektorArray);
            eliminiereElementeUnterDiagonalElement(spaltenIndex, matrixArray, vektorArray);
        }

        return new Matrix(matrixArray);
    }

    private void eliminiereElementeUnterDiagonalElement(int spaltenIndex, double[][] matrixArray, double[] vektorArray) {

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

            eliminiereNaechstesElementInVektor(
                    spaltenIndex, indexVonNaechstemElementDasEliminiertWird, koeffizient, vektorArray
            );
        }
    }

    private void eliminiereNaechstesElementInMatrix(
            double[][] matrixArray, int indexVonNaechstemElementDasEliminiertWird,
            double[] zeileNachDerEntwickeltWird, double[] zeileInDerEliminiertWird, double koeffizient) {

        matrixArray[indexVonNaechstemElementDasEliminiertWird] = gaussHilfsFunktionen.subtrahiereZeile(zeileNachDerEntwickeltWird,
                zeileInDerEliminiertWird, koeffizient);
    }

    private void eliminiereNaechstesElementInVektor(int spaltenIndex, int indexVonNaechstemElementDasEliminiertWird, double koeffizient, double[] vektorArray) {
        vektorArray[indexVonNaechstemElementDasEliminiertWird] =
                vektorArray[indexVonNaechstemElementDasEliminiertWird] - koeffizient * vektorArray[spaltenIndex];
    }

    private void tauscheSolangeZeilenBisWertAufHauptDiagonaleNichtNullIst(double[][] matrixArray, int zeileNachDerEntwickeltWird, double[] vektorArray) {
        int andereZeile = 1;
        while (gaussHilfsFunktionen.istWertAufDiagonaleNull(matrixArray, zeileNachDerEntwickeltWird)) {
            gaussHilfsFunktionen.tauscheZeilenVonVektorUndMatrix(matrixArray, vektorArray, zeileNachDerEntwickeltWird, andereZeile);
            andereZeile++;
        }
    }
}
