package TorusProjekt.Matrix.MatrizenVerfahren;

import java.util.Arrays;

public class Matrix {

    private final double[][] matrix;
    private final int anzahlZeilen;
    private final int anzahlSpalten;
    private final DeterminantenRechner determinantenRechner = new DeterminantenRechner();

    public Matrix(double[][] matrix) {
        this.matrix = matrix;
        anzahlSpalten = matrix[0].length;
        anzahlZeilen = matrix.length;
    }

    public Matrix(Matrix m) {
        this.matrix = Arrays.stream(m.matrix).map(double[]::clone).toArray(double[][]::new);
        this.anzahlSpalten = m.anzahlSpalten;
        this.anzahlZeilen = m.anzahlZeilen;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public int getAnzahlSpalten() {
        return anzahlSpalten;
    }

    public int getAnzahlZeilen() {
        return anzahlZeilen;
    }

    public Matrix multipliziere(Matrix m) {
        return new MatrixMultiplikator().multipliziere(this, m);
    }

    public Matrix multipliziere(double skalar) {
        return new MatrixMultiplikator().multipliziere(this, skalar);
    }

    public double getDeterminante() {
        return determinantenRechner.getDeterminante(this);
    }



    @Override
    public String toString() {
        return Arrays.deepToString(matrix);
    }
}
