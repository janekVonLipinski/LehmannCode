package LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.ExceptionHandling;

import LehmannCode.Matrizen.IMatrix;
import LehmannCode.Vektor.IVektor;

import java.util.Arrays;

public class ExceptionHandler {

    public void istGleichungssystemValide(IMatrix matrix, IVektor vektor) {
        if (matrix.getAnzahlSpalten() != matrix.getAnzahlZeilen()) {
            throw new IllegalArgumentException("Matrix muss quadratisch sein");
        }

        if (vektor.getVektor().length != matrix.getAnzahlZeilen()) {
            throw new IllegalArgumentException("Matrix und Vektor müssen selbe Dimension haben");
        }
    }

    public void istGleichungssystemEindeutigLoesbar(IMatrix matrix) {
        if (matrix.getDeterminante() == 0) {
            throw new IllegalArgumentException("Determinante ist 0, Gleichungssystem ist nicht eindeutig lösbar");
        }
    }

    public void hatMatrixNullZeile(IMatrix m) {
        double[][] matrix = m.getMatrix();
        double[][] testMatrix = new double[m.getAnzahlZeilen()][m.getAnzahlSpalten() - 1];

        for (int zeile = 0; zeile < m.getAnzahlZeilen(); zeile++) {
            if (m.getAnzahlSpalten() >= 0) {
                System.arraycopy(matrix[zeile], 0, testMatrix[zeile], 0, m.getAnzahlSpalten() - 2);
            }
        }

        System.out.println(Arrays.deepToString(testMatrix));

        for (double[] zeile : matrix) {
            long anzahlNullen = Arrays
                    .stream(zeile)
                    .filter(i -> i == 0)
                    .count();

            if (anzahlNullen == m.getAnzahlSpalten()) {
                throw new IllegalArgumentException("LGS ist nicht eindeutig lösbar");
            }
        }
    }
}
