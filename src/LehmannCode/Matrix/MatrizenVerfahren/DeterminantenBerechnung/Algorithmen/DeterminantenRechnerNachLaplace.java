package LehmannCode.Matrix.MatrizenVerfahren.DeterminantenBerechnung.Algorithmen;

import LehmannCode.Matrix.MatrizenVerfahren.DeterminantenBerechnung.Determinante;
import LehmannCode.Matrix.Matrix;
import LehmannCode.Util.Zeile;

public class DeterminantenRechnerNachLaplace implements Determinante {

    public double getDeterminante(Matrix m) {
        return getDeterminante(m, 1);
    }

    private double getDeterminante(Matrix m, double faktor) {
        int anzahlZeilen = m.getAnzahlZeilen();
        int anzahlSpalten = m.getAnzahlSpalten();
        double[][] matrix = m.getMatrix();

        if (anzahlZeilen != anzahlSpalten) {
            throw new IllegalArgumentException("Matrix mus n x n sein");
        }

        if (faktor == 0) {
            return 0;
        }

        if (anzahlZeilen == 2) {
            return getDeterminanteVonZWeiKreuzZweiMatrix(matrix);
        }

        double ergebnis = 0;

        for (int spaltenIndex = 0; spaltenIndex < anzahlSpalten; spaltenIndex++) {

            Matrix verkleinerteMatrix = streicheErsteZeileUndUebergebeneSpalte(m, spaltenIndex);

            double zunahme =
                    Math.pow(-1, spaltenIndex)
                    * matrix[0][spaltenIndex]
                    * getDeterminante(verkleinerteMatrix, matrix[0][spaltenIndex]);

            ergebnis += zunahme;
        }

        return ergebnis;
    }

    private double getDeterminanteVonZWeiKreuzZweiMatrix(double[][] matrix) {
        return (matrix[0][0] * matrix[1][1]) - (matrix[1][0] * matrix[0][1]);
    }

    private Matrix streicheErsteZeileUndUebergebeneSpalte(Matrix m, int spalte) {
        return new Zeile().streicheUebergebeneZeileUndSpalte(m, 0, spalte);
    }
}
