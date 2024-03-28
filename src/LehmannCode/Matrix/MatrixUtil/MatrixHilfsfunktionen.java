package LehmannCode.Matrix.MatrixUtil;

import LehmannCode.Matrix.MatrizenVerfahren.Matrix;

public class MatrixHilfsfunktionen {

    public Matrix getMatrixMitGestrichenerErstenZeileUndUebergebenerSpalte(Matrix m, int spalte) {
        return getMatrixMitGestrichenerErstenZeileUndUebergebenerSpalte(m, 0, spalte);
    }

    public Matrix getMatrixMitGestrichenerErstenZeileUndUebergebenerSpalte(Matrix m, int zeile, int spalte) {
        int anzahlSpalten = m.getAnzahlSpalten();
        int anzahlZeilen = m.getAnzahlZeilen();
        double[][] matrix = m.getMatrix();

        double[][] neueMatrix = new double[anzahlZeilen - 1][anzahlSpalten - 1];
        double[][] alteMatrix = matrix.clone();

        int l = 0;
        for (int zeilenIndex = 0; zeilenIndex < neueMatrix.length; zeilenIndex++) {
            if (zeilenIndex == zeile) {
                l++;
            }
            int k = 0;
            for (int spaltenIndex = 0; spaltenIndex < neueMatrix.length; spaltenIndex++) {
                if (k == spalte) {
                    k++;
                }
                neueMatrix[zeilenIndex][spaltenIndex] = alteMatrix[l][k];
                k++;
            }
            l++;
        }
        return new Matrix(neueMatrix);
    }

    public Matrix transponiere(Matrix m) {
        Matrix matrixKopie = new Matrix(m);
        double[][] matrix = matrixKopie.getMatrix();

        for (int zeilenIndex = 0; zeilenIndex < matrix.length; zeilenIndex++) {
            for (int spaltenIndex = zeilenIndex; spaltenIndex < matrix.length; spaltenIndex++) {
                if (zeilenIndex == spaltenIndex) {
                    continue;
                }
                double temp = matrix[zeilenIndex][spaltenIndex];
                matrix[zeilenIndex][spaltenIndex] = matrix[spaltenIndex][zeilenIndex];
                matrix[spaltenIndex][zeilenIndex] = temp;
            }
        }

        return matrixKopie;
    }

    public double getKofaktor(Matrix m, int zeile, int spalte) {

        Matrix verkleinerteMatrix = getMatrixMitGestrichenerErstenZeileUndUebergebenerSpalte(m, zeile, spalte);
        return Math.pow(-1, zeile + spalte) * verkleinerteMatrix.getDeterminante();
    }
}
