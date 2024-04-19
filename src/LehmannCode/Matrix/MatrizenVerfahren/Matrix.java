package LehmannCode.Matrix.MatrizenVerfahren;

import LehmannCode.Matrix.MatrizenVerfahren.DeterminantenBerechnung.Algorithmen.DeterminantenRechnerNachLaplace;
import LehmannCode.Matrix.MatrizenVerfahren.DeterminantenBerechnung.Determinante;

import java.util.Arrays;

public class Matrix {

    private final double[][] matrix;
    private final Determinante determinantenRechner;
    private final MatrixMultiplikator matrixMultiplikator;

    public Matrix(double[][] matrix, Determinante determinantenRechner, MatrixMultiplikator matrixMultiplikator) {
        this.matrix = matrix;
        this.determinantenRechner = determinantenRechner;
        this.matrixMultiplikator = matrixMultiplikator;
    }

    public Matrix(double[][] matrix) {
        this.matrix = matrix;
        this.determinantenRechner = new DeterminantenRechnerNachLaplace();
        this.matrixMultiplikator = new MatrixMultiplikator();
    }

    public Matrix(Matrix m) {
        this.matrix = Arrays.stream(m.matrix).map(double[]::clone).toArray(double[][]::new);
        this.determinantenRechner = m.determinantenRechner;
        this.matrixMultiplikator = m.matrixMultiplikator;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public int getAnzahlSpalten() {
        return matrix[0].length;
    }

    public int getAnzahlZeilen() {
        return matrix.length;
    }

    public Matrix multipliziere(Matrix m) {
        return matrixMultiplikator.multipliziere(this, m);
    }

    public Matrix multipliziere(double skalar) {
        return matrixMultiplikator.multipliziere(this, skalar);
    }

    public double getDeterminante() {
        return determinantenRechner.getDeterminante(this);
    }

    @Override
    public String toString() {
        return Arrays.deepToString(matrix);
    }
}
