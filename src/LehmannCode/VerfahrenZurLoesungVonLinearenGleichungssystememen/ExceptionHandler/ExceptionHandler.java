package LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.ExceptionHandler;

import LehmannCode.Matrix.MatrizenVerfahren.Matrix;
import LehmannCode.Vektor.Vektor;

public class ExceptionHandler {


    public void istGleichungssystemValide(Matrix matrix, Vektor vektor) {
        if (matrix.getAnzahlSpalten() != matrix.getAnzahlZeilen()) {
            throw new IllegalArgumentException("Matrix muss quadratisch sein");
        }

        if (vektor.getVektor().length != matrix.getAnzahlZeilen()) {
            throw new IllegalArgumentException("Matrix und Vektor müssen selbe Dimension haben");
        }
    }

    public void istGleichungssystemEindeutigLoesbar(Matrix matrix) {
        if (matrix.getDeterminante() == 0) {
            throw new IllegalArgumentException("Determinante ist 0, Gleichungssystem ist nicht eindeutig lösbar");
        }
    }
}
