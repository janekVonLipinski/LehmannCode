package LehmannCode.Matrix.MatrixTransponierung;

import LehmannCode.Matrix.Matrix;

public class MatrixHilfsfunktionen {

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
}
