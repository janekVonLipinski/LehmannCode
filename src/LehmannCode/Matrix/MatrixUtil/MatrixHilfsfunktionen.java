package LehmannCode.Matrix.MatrixUtil;

import LehmannCode.Matrix.MatrizenVerfahren.Matrix;

public class MatrixHilfsfunktionen {

    public Matrix streicheErsteZeileUndUebergebeneSpalte(Matrix m, int spalte) {
        return streicheUebergebeneZeileUndSpalte(m, 0, spalte);
    }

    public Matrix streicheUebergebeneZeileUndSpalte(Matrix m, int gestricheneZeile, int gestricheneSpalteIndex) {
        int anzahlSpalten = m.getAnzahlSpalten();
        int anzahlZeilen = m.getAnzahlZeilen();

        double[][] matrix = m.getMatrix();
        double[][] neueMatrix = new double[anzahlZeilen - 1][anzahlSpalten - 1];
        double[][] alteMatrix = matrix.clone();

        int alteMatrixZeilenIndex = 0;

        for (int zeilenIndex = 0; zeilenIndex < neueMatrix.length; zeilenIndex++) {

            if (istZeileUebersprungeneZeile(gestricheneZeile, zeilenIndex)) {
                alteMatrixZeilenIndex = ueberspringeZeile(alteMatrixZeilenIndex);
            }

            ersetzeSpalte(gestricheneSpalteIndex, zeilenIndex, alteMatrixZeilenIndex, neueMatrix, alteMatrix);
            alteMatrixZeilenIndex++;
        }

        return new Matrix(neueMatrix);
    }

    private void ersetzeSpalte(int gestricheneSpalteIndex, int zeilenIndex, int alteMatrixZeilenIndex,
                               double[][] neueMatrix, double[][] alteMatrix) {

        int alteMatrixSpalteIndex = 0;

        for (int spaltenIndex = 0; spaltenIndex < neueMatrix.length; spaltenIndex++) {

            if (istUebersprungeneSpalte(gestricheneSpalteIndex, alteMatrixSpalteIndex)) {
                alteMatrixSpalteIndex = uebersrpingeSpalte(alteMatrixSpalteIndex);
            }

            ersetzteElementInSpalte(alteMatrixSpalteIndex, spaltenIndex,
                    neueMatrix[zeilenIndex], alteMatrix[alteMatrixZeilenIndex]);

            alteMatrixSpalteIndex++;
        }
    }

    private void ersetzteElementInSpalte(int alteMatrixSpaltenIndex, int spaltenIndex,
                                         double[] neueMatrix, double[] alteMatrix) {
        neueMatrix[spaltenIndex] = alteMatrix[alteMatrixSpaltenIndex];
    }

    private static int uebersrpingeSpalte(int alteMatrixSpalte) {
        alteMatrixSpalte++;
        return alteMatrixSpalte;
    }

    private static boolean istUebersprungeneSpalte(int gestricheneSpalte, int alteMatrixSpalte) {
        return alteMatrixSpalte == gestricheneSpalte;
    }

    private boolean istZeileUebersprungeneZeile(int zeile, int zeilenIndex) {
        return zeilenIndex == zeile;
    }

    private int ueberspringeZeile(int alteMatrixZeile) {
        alteMatrixZeile++;
        return alteMatrixZeile;
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

        Matrix verkleinerteMatrix = streicheUebergebeneZeileUndSpalte(m, zeile, spalte);
        return Math.pow(-1, zeile + spalte) * verkleinerteMatrix.getDeterminante();
    }
}
