package LehmannCode.Matrix.MatrizenVerfahren;

import LehmannCode.Matrix.MatrixUtil.MatrixHilfsfunktionen;

public class DeterminantenRechner {

    protected double getDeterminante(Matrix m) {
        return getDeterminante(m, 1);
    }

    private double getDeterminante(Matrix m, double faktor) {
        int anzahlZeilen = m.getAnzahlZeilen();
        int anzahlSpalten = m.getAnzahlSpalten();
        double[][] matrix = m.getMatrix();
        double ergebnis = 0;

        if (anzahlZeilen != anzahlSpalten) {
            throw new IllegalArgumentException("Matrix mus n x n sein");
        }

        if (faktor == 0) {
            return 0;
        }

        if (anzahlZeilen == 2) {
            return getDeterminanteVonZWeiKreuzZweiMatrix(matrix);
        }

        for (int i = 0; i < anzahlSpalten; i++) {

            Matrix verkleinerteMatrix = new MatrixHilfsfunktionen().getMatrixMitGestrichenerErstenZeileUndUebergebenerSpalte(m, i);
            double zunahme = Math.pow(-1, i) * matrix[0][i] * getDeterminante(verkleinerteMatrix, matrix[0][i]);
            ergebnis += zunahme;
        }

        return ergebnis;
    }

    private double getDeterminanteVonZWeiKreuzZweiMatrix(double[][] matrix) {
        return (matrix[0][0] * matrix[1][1]) - (matrix[1][0] * matrix[0][1]);
    }
}
