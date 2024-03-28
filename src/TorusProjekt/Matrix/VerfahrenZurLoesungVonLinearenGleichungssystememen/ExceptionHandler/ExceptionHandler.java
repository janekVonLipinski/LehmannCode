package TorusProjekt.Matrix.VerfahrenZurLoesungVonLinearenGleichungssystememen.ExceptionHandler;

import TorusProjekt.Matrix.MatrizenVerfahren.Matrix;
import TorusProjekt.Vektor.Vektor;

public class ExceptionHandler {


    public boolean istValide(Matrix matrix, Vektor vektor) {
        if (matrix.getAnzahlSpalten() != matrix.getAnzahlZeilen()) {
            throw new IllegalArgumentException("Matrix muss quadratisch sein");
        }

        if (vektor.getVektorWerte().length != matrix.getAnzahlZeilen()) {
            throw new IllegalArgumentException("Matrix und Vektor müssen selbe Dimension haben");
        }

        if (matrix.getDeterminante() == 0) {
            throw new IllegalArgumentException("Gleichung ist nicht eindeutig lösbar");
        }

        return true;
    }
}
