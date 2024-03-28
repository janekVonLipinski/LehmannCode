package LehmannCode.Matrix;

import LehmannCode.Matrix.MatrixUtil.MatrixHilfsfunktionen;
import LehmannCode.Matrix.MatrizenVerfahren.Matrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixHilfsfunktionenTest {

    private final MatrixHilfsfunktionen matrixHilfsfunktionen = new MatrixHilfsfunktionen();

    @Test
    void Given_Drei_kreuz_drei_Matrix_Then_wurde_korrekt_transponiert() {
        double[][] array = {{1,2, 3}, {4,5, 6}, {7, 8, 9}};
        double[][] ziel = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};

        Matrix m = new Matrix(array);
        Matrix transponierteMatrix = matrixHilfsfunktionen.transponiere(m);

        assertArrayEquals(ziel, transponierteMatrix.getMatrix());
    }

    @Test
    void Given_Drei_kreuz_drei_Matrix_und_0_0_Then_Kofaktor_wurde_korrekt_berechnet() {
        double[][] array = {{1,2, 3}, {4,5, 6}, {7, 8, 9}};
        Matrix matrix = new Matrix(array);
        double erwartetesErgebnis = -3;
        double ergebnis = matrixHilfsfunktionen.getKofaktor(matrix, 0,0);
        assertEquals(erwartetesErgebnis, ergebnis);
    }

}