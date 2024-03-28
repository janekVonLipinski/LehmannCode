package TorusProjekt.Matrix.MatrizenVerfahren;

import TorusProjekt.Matrix.MatrixUtil.MatrixHilfsfunktionen;

public class DeterminantenRechner {

    protected double getDeterminante(Matrix m) {
        int anzahlZeilen = m.getAnzahlZeilen();
        int anzahlSpalten = m.getAnzahlSpalten();
        double[][] matrix = m.getMatrix();
        double ergebnis = 0;

        if (anzahlZeilen != anzahlSpalten) {
            throw new IllegalArgumentException("Matrix mus n x n sein");
        }

        if (anzahlZeilen == 2) {
            return (matrix[0][0] * matrix[1][1]) - (matrix[1][0] * matrix[0][1]);
        }

        for (int i = 0; i < anzahlSpalten; i++) {
            Matrix verkleinerteMatrix = new MatrixHilfsfunktionen().getMatrixMitGestrichenerErstenZeileUndUebergebenerSpalte(m, i);
            double zunahme = Math.pow(-1, i) * matrix[0][i] * getDeterminante(verkleinerteMatrix);
            ergebnis += zunahme;
        }

        return ergebnis;
    }
}
