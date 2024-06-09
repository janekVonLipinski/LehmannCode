package LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen;

import LehmannCode.Matrizen.MatrixImplementierung.DeterminantenBerechnung.Algorithmen.DeterminantenRechnerNachGauss;
import LehmannCode.Matrizen.MatrixImplementierung.Inverse.InverseMatrix;
import LehmannCode.Matrizen.MatrixImplementierung.Matrix;
import LehmannCode.Matrizen.MatrixImplementierung.MatrixMultiplikation.MatrixMultiplikator;
import LehmannCode.Matrizen.MatrixImplementierung.MatrixTransponierung.Transponierung;
import LehmannCode.Matrizen.MatrixImplementierung.MatrixUmformung.Diagonalform;
import LehmannCode.Matrizen.MatrixImplementierung.MatrixUmformung.Hilfe;
import LehmannCode.Matrizen.MatrixImplementierung.MatrixUmformung.Stufenform;
import LehmannCode.Vektor.Vektor;

import java.util.Random;

public class MethodenZumTesten {

    public static Matrix generateQuadratischeTestMatrix(int zeilenAnzahl) {
        double[][] testMatrix = new double[zeilenAnzahl][zeilenAnzahl];
        Random random = new Random();
        do {
            for (int i = 0; i < zeilenAnzahl; i++) {
                for (int j = 0; j < zeilenAnzahl; j++) {
                    if (random.nextInt(0, 10) == 1) {
                        testMatrix[i][j] = 0;
                        continue;
                    }
                    testMatrix[i][j] = random.nextDouble(1, 100000);
                }
            }
        } while (new Matrix(testMatrix, new DeterminantenRechnerNachGauss(),
                new MatrixMultiplikator(), new InverseMatrix(), new Stufenform(new Hilfe()),
                new Diagonalform(new Hilfe()), new Transponierung()).getDeterminante() < 0.01);

        return new Matrix(testMatrix);
    }

    public static Vektor generateXVektor(int zeilenAnzahl) {
        double[] testVektor = new double[zeilenAnzahl];
        Random random = new Random();
        for (int i = 0; i < zeilenAnzahl; i++) {
            if (random.nextInt(0, 10) == 1) {
                testVektor[i] = 0;
                continue;
            }
            testVektor[i] = random.nextDouble(1, 100000);
        }

        return new Vektor(testVektor);
    }
}