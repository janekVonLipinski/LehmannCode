package LehmannCode.Matrizen;

import LehmannCode.Matrizen.MatrixImplementierung.DeterminantenBerechnung.Algorithmen.DeterminantenRechnerNachGauss;
import LehmannCode.Matrizen.MatrixImplementierung.DeterminantenBerechnung.Algorithmen.DeterminantenRechnerNachLaplace;
import LehmannCode.Matrizen.MatrixImplementierung.Inverse.InverseMatrix;
import LehmannCode.Matrizen.MatrixImplementierung.Matrix;
import LehmannCode.Matrizen.MatrixImplementierung.MatrixMultiplikation.MatrixMultiplikator;
import LehmannCode.Matrizen.MatrixImplementierung.MatrixTransponierung.Transponierung;
import LehmannCode.Matrizen.MatrixImplementierung.MatrixUmformung.Diagonalform;
import LehmannCode.Matrizen.MatrixImplementierung.MatrixUmformung.Hilfe;
import LehmannCode.Matrizen.MatrixImplementierung.MatrixUmformung.Stufenform;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MatrixTest {

    @Test
    void Given_3_kreuz_3_matrix_And_Algorithmus_ist_Laplace_Then_determinante_ist_7() {
        double[][] matrix = {{3,1,2}, {4, 1, 3}, {-1,1,5}};
        Matrix alteMatrix = new Matrix(matrix, new DeterminantenRechnerNachLaplace(), new MatrixMultiplikator(),
                new InverseMatrix(), new Stufenform(new Hilfe()), new Diagonalform(new Hilfe()), new Transponierung());

        double erwartetesErgebnis = -7;
        double determinante = alteMatrix.getDeterminante();

        assertEquals(erwartetesErgebnis, determinante);
    }

    @Test
    void Given_3_kreuz_3_matrix_And_Algorithmus_ist_Gauss_Then_determinante_ist_7() {
        double[][] matrix = {{3,1,2}, {4, 1, 3}, {-1,1,5}};
        Matrix alteMatrix = new Matrix(matrix,
                new DeterminantenRechnerNachGauss(),
                new MatrixMultiplikator(), new InverseMatrix(), new Stufenform(new Hilfe()),
                new Diagonalform(new Hilfe()), new Transponierung());

        double determinante = alteMatrix.getDeterminante();
        double erwartetesErgebnis = -7;

        assertTrue(erwartetesErgebnis - determinante < 0.01);
    }

    @Test
    void Given_3_kreuz_3_matrix_And_element_auf_Diagonale_ist_null_And_Algorithmus_ist_Gauss_Then_determinante_ist_Minus_13() {
        double[][] matrix = {{0,1,2}, {4, 1, 3}, {-1,1,5}};
        Matrix alteMatrix = new Matrix(matrix,
                new DeterminantenRechnerNachGauss(),
                new MatrixMultiplikator(), new InverseMatrix(), new Stufenform(new Hilfe()),
                new Diagonalform(new Hilfe()), new Transponierung());

        double determinante = alteMatrix.getDeterminante();
        double erwartetesErgebnis = -13;

        assertTrue(erwartetesErgebnis - determinante < 0.01);
    }

}