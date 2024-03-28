package TorusProjekt.Matrix.VerfahrenZurLoesungVonLinearenGleichungssystememen.GaussAlgorithmus;

import java.util.Arrays;

public class GaussHilfsFunktionen {

    protected double[] subtrahiereZeile(double[] ersteZeile, double[] zweiteZeile,
                                      double koeffiezient) {

        double[] multiplizierteZeile = getMultiplizierteZeile(ersteZeile, koeffiezient);

        double[] returnArray = new double[ersteZeile.length];
        for (int i = 0; i < ersteZeile.length; i++) {
            returnArray[i] = zweiteZeile[i] - multiplizierteZeile[i];
        }

        return returnArray;
    }

    protected void tausche(double[][] matrixArray, double[] vektorArray, int j, int i) {
        tauscheZeileVonMatrix(matrixArray, j, i);
        tauscheZeileVonVektor(vektorArray, j, i);
    }

    protected double getKoeffizient(double ersterWert, double zweiterWert) {
        if (ersterWert == 0) {
            throw new ArithmeticException("Shit man, you have devided by 0");
        }
        return zweiterWert / ersterWert;
    }

    protected boolean istWertAufDiagonale0(double[][] matrix, int j) {
        return matrix[j][j] == 0;
    }

    private double[] getMultiplizierteZeile(double[] zeile, double koeffizient)  {
        double[] copy = Arrays.copyOf(zeile, zeile.length);
        return Arrays.stream(copy)
                .map(i -> i * koeffizient)
                .toArray();
    }

    private void tauscheZeileVonMatrix(double[][] matrixArray, int indexErsteZeile, int indexZweiteZeile) {

        double[] ersteZeile = matrixArray[indexErsteZeile];
        double[] zweiteZeile = matrixArray[indexZweiteZeile];

        matrixArray[indexErsteZeile] = zweiteZeile;
        matrixArray[indexZweiteZeile] = ersteZeile;
    }

    private void tauscheZeileVonVektor(double[] vektor, int ersterIndex, int zweiterIndex) {
        double temp = vektor[ersterIndex];
        vektor[ersterIndex] = vektor[zweiterIndex];
        vektor[zweiterIndex] = temp;
    }
}
