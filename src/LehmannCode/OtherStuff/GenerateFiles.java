package LehmannCode.OtherStuff;

import LehmannCode.IO.Output;
import LehmannCode.Matrizen.IMatrix;
import LehmannCode.Vektor.IVektor;

import java.io.IOException;

public class GenerateFiles {

    private static final int ANZAHL_MATRIZEN = 1000;

    public static void main(String[] args) {
        for (int i = 2; i <= ANZAHL_MATRIZEN; i++) {
            System.out.println(i);

            String fileName = "C:\\Users\\Admin\\Desktop\\java\\Naeherungsverfahren\\LehmannCode\\txt_matrix\\" +
                    "matrix_"+ i + "_kreuz_" + i +".txt";
            String output = "";

            IMatrix matrix = MethodenZumTesten.generateQuadratischeTestMatrix(i);
            IVektor xVektor = MethodenZumTesten.generateXVektor(i);
            IVektor loesungsVektor = xVektor.multipliziere(matrix);

            double[][] matrixArray = matrix.getMatrix();
            double[] vektorArray = loesungsVektor.getVektor();

            for (int j = 0; j < matrix.getAnzahlZeilen(); j++) {

                for (int k = 0; k < matrix.getAnzahlSpalten(); k++) {
                    output += Double.toString(matrixArray[j][k]);
                    output += " ";
                }

                output = output.substring(0, output.length() - 1);
                output += ";";
                output += vektorArray[j];
                output += "\n";
            }
            try {
                new Output().writeToFile(fileName, output);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
