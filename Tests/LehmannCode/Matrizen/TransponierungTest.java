package LehmannCode.Matrizen;

import LehmannCode.Matrizen.MatrixImplementierung.Matrix;
import LehmannCode.Matrizen.MatrixImplementierung.MatrixTransponierung.Transponierung;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransponierungTest {

    private final Transponierung transponierung = new Transponierung();

    @Test
    void Given_Drei_kreuz_drei_Matrix_Then_wurde_korrekt_transponiert() {
        double[][] array = {{1,2, 3}, {4,5, 6}, {7, 8, 9}};
        double[][] ziel = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};

        IMatrix m = new Matrix(array);
        IMatrix transponierteMatrix = transponierung.transponiere(m);

        assertArrayEquals(ziel, transponierteMatrix.getMatrix());
    }

}