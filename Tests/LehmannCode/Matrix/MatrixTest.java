package LehmannCode.Matrix;

import LehmannCode.Matrix.MatrizenVerfahren.Matrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    @Test
    void Given_3_kreuz_3_matrix_Then_determinante_ist_7() {
        double[][] matrix = {{3,1,2}, {4, 1, 3}, {-1,1,5}};
        Matrix alteMatrix = new Matrix(matrix);
        System.out.println(alteMatrix);

        assertEquals(-7, alteMatrix.getDeterminante());
    }

}