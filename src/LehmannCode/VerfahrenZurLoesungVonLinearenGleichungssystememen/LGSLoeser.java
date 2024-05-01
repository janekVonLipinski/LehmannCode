package LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen;

import LehmannCode.Matrix.Matrix;
import LehmannCode.Vektor.Vektor;

public interface LGSLoeser {

    Vektor loeseGleichungssystem(Matrix m,Vektor v);
}
