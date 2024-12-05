package LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen;

import LehmannCode.Matrizen.IMatrix;
import LehmannCode.Vektor.IVektor;

public interface LGSLoeser {

    IVektor loeseGleichungssystem(IMatrix m, IVektor v);
}
