package LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.ExceptionHandling;

import LehmannCode.Matrizen.IMatrix;
import LehmannCode.Vektor.IVektor;

public class ExceptionHandler {

    public void istGleichungssystemValide(IMatrix matrix, IVektor vektor) {
        if (matrix.getAnzahlSpalten() != matrix.getAnzahlZeilen()) {
            throw new IllegalArgumentException("Matrix muss quadratisch sein");
        }

        if (vektor.getVektor().length != matrix.getAnzahlZeilen()) {
            throw new IllegalArgumentException("Matrix und Vektor müssen selbe Dimension haben");
        }
    }

    public void istGleichungssystemEindeutigLoesbar(IMatrix matrix) {
        if (matrix.getDeterminante() == 0) {
            throw new IllegalArgumentException("Determinante ist 0, Gleichungssystem ist nicht eindeutig lösbar");
        }
    }
}
