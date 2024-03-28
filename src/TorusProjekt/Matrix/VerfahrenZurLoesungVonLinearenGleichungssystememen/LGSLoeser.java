package TorusProjekt.Matrix.VerfahrenZurLoesungVonLinearenGleichungssystememen;

import TorusProjekt.Matrix.MatrizenVerfahren.Matrix;
import TorusProjekt.Vektor.Vektor;

public interface LGSLoeser {

    Vektor loeseGleichungssystem(Matrix m,Vektor v);
}
