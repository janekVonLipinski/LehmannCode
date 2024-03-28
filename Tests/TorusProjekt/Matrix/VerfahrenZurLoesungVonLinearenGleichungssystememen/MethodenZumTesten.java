package TorusProjekt.Matrix.VerfahrenZurLoesungVonLinearenGleichungssystememen;

import TorusProjekt.Matrix.MatrizenVerfahren.Matrix;
import TorusProjekt.Vektor.Vektor;

import java.util.Arrays;
import java.util.Random;

public class MethodenZumTesten {

    public static boolean sindWerteKleinerAlsDiffernz(Vektor v1, Vektor v2) {
        int zeilenAnzahl = v1.getVektorWerte().length;
        double[] differenz = v1.subtrahiere(v2).getVektorWerte();
        double[] abweichung = getAbweichungsArray(zeilenAnzahl);

        for (int i = 0; i < zeilenAnzahl; i++) {
            if (Math.abs(differenz[i]) > abweichung[i]) {
                return false;
            }
        }
        return true;
    }

    public static double[] getAbweichungsArray(int zeilenAnzahl) {
        double[] abweichungsVektor = new double[zeilenAnzahl];
        Arrays.fill(abweichungsVektor, 0.1);
        return abweichungsVektor;
    }

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
        } while (new Matrix(testMatrix).getDeterminante() == 0);

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