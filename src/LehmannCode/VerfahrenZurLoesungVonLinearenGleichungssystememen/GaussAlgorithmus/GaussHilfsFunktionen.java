package LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.GaussAlgorithmus;

import LehmannCode.Matrix.MatrizenVerfahren.Matrix;
import LehmannCode.Vektor.Vektor;

import java.util.Arrays;

public class GaussHilfsFunktionen {

    protected Matrix erzeugeErweiterteKoeffizientenMatrix(Matrix m, Vektor v) {
        double[][] matrix = new Matrix(m).getMatrix();
        double[] vektor = new Vektor(v).getVektor();

        double[][] erweiterteKoeffizientenMatrix = new double[m.getAnzahlZeilen()][m.getAnzahlSpalten() + 1];

        for (int zeile = 0; zeile < m.getAnzahlZeilen(); zeile++) {
            for (int spalte = 0; spalte < m.getAnzahlSpalten(); spalte++) {
                erweiterteKoeffizientenMatrix[zeile][spalte] = matrix[zeile][spalte];
            }
            erweiterteKoeffizientenMatrix[zeile][m.getAnzahlSpalten()] = vektor[zeile];
        }

        return new Matrix(erweiterteKoeffizientenMatrix);
    }

    protected double[] subtrahiereZeile(double[] ersteZeile, double[] zweiteZeile,
                                      double koeffiezient) {

        double[] multiplizierteZeile = multipliziereZeile(ersteZeile, koeffiezient);

        double[] returnArray = new double[ersteZeile.length];
        for (int i = 0; i < ersteZeile.length; i++) {
            returnArray[i] = zweiteZeile[i] - multiplizierteZeile[i];
        }

        return returnArray;
    }

    protected boolean istWertAufDiagonaleNull(double[][] matrix, int j) {
        return matrix[j][j] == 0;
    }

    private double[] multipliziereZeile(double[] zeile, double koeffizient)  {
        double[] copy = Arrays.copyOf(zeile, zeile.length);
        return Arrays.stream(copy)
                .map(i -> i * koeffizient)
                .toArray();
    }

    protected void tauscheZeileVonMatrix(double[][] matrixArray, int indexErsteZeile, int indexZweiteZeile) {

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
