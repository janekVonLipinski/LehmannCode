package LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.GaussAlgorithmus;

import LehmannCode.Matrizen.IMatrix;
import LehmannCode.Matrizen.MatrixImplementierung.Matrix;
import LehmannCode.Vektor.IVektor;
import LehmannCode.Vektor.Vektor;

public class GaussHilfsFunktionen {

    protected IMatrix erzeugeErweiterteKoeffizientenMatrix(IMatrix m, IVektor v) {
        double[][] matrix = new Matrix((Matrix) m).getMatrix();
        double[] vektor = new Vektor((Vektor) v).getVektor();

        double[][] erweiterteKoeffizientenMatrix = new double[m.getAnzahlZeilen()][m.getAnzahlSpalten() + 1];

        for (int zeile = 0; zeile < m.getAnzahlZeilen(); zeile++) {
            for (int spalte = 0; spalte < m.getAnzahlSpalten(); spalte++) {
                erweiterteKoeffizientenMatrix[zeile][spalte] = matrix[zeile][spalte];
            }
            erweiterteKoeffizientenMatrix[zeile][m.getAnzahlSpalten()] = vektor[zeile];
        }

        return new Matrix(erweiterteKoeffizientenMatrix);
    }
}
