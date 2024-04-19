package LehmannCode.Matrix;

import LehmannCode.Matrix.MatrizenVerfahren.DeterminantenBerechnung.Algorithmen.DeterminantenRechnerNachGauss;
import LehmannCode.Matrix.MatrizenVerfahren.DeterminantenBerechnung.Algorithmen.DeterminantenRechnerNachLaplace;
import LehmannCode.Matrix.MatrizenVerfahren.Matrix;
import LehmannCode.Matrix.MatrizenVerfahren.MatrixMultiplikator;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.GaussAlgorithmus.GaussHilfsFunktionen;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.GaussAlgorithmus.Stufenform;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MatrixTest {

    @Test
    void Given_3_kreuz_3_matrix_And_Algorithmus_ist_Laplace_Then_determinante_ist_7() {
        double[][] matrix = {{3,1,2}, {4, 1, 3}, {-1,1,5}};
        Matrix alteMatrix = new Matrix(matrix, new DeterminantenRechnerNachLaplace(), new MatrixMultiplikator());

        double erwartetesErgebnis = -7;
        double determinante = alteMatrix.getDeterminante();

        assertEquals(erwartetesErgebnis, determinante);
    }

    @Test
    void Given_3_kreuz_3_matrix_And_Algorithmus_ist_Gauss_Then_determinante_ist_7() {
        double[][] matrix = {{3,1,2}, {4, 1, 3}, {-1,1,5}};
        Matrix alteMatrix = new Matrix(matrix,
                new DeterminantenRechnerNachGauss(new Stufenform(new GaussHilfsFunktionen())),
                new MatrixMultiplikator());

        double determinante = alteMatrix.getDeterminante();
        double erwartetesErgebnis = -7;

        assertTrue(erwartetesErgebnis - determinante < 0.01);
    }

    @Test
    void Given_3_kreuz_3_matrix_And_element_auf_Diagonale_ist_null_And_Algorithmus_ist_Gauss_Then_determinante_ist_Minus_13() {
        double[][] matrix = {{0,1,2}, {4, 1, 3}, {-1,1,5}};
        Matrix alteMatrix = new Matrix(matrix,
                new DeterminantenRechnerNachGauss(new Stufenform(new GaussHilfsFunktionen())),
                new MatrixMultiplikator());

        double determinante = alteMatrix.getDeterminante();
        double erwartetesErgebnis = -13;

        assertTrue(erwartetesErgebnis - determinante < 0.01);
    }

}