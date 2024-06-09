package LehmannCode;

import LehmannCode.IO.Input;
import LehmannCode.IO.Model.LGS;
import LehmannCode.Matrizen.IMatrix;
import LehmannCode.Vektor.IVektor;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.Determinantenverfahren.DeterminatenVerfahren;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.ExceptionHandling.ExceptionHandler;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.GaussAlgorithmus.GaussAlgorithmus;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.InverseMatrixVerfahren.InverseMatrixVerfahren;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestMain {

    private static final String PATH =
            "C:\\Users\\Admin\\Desktop\\java\\Naeherungsverfahren\\LehmannCode\\txt_matrix\\";

    private static final String OUTPUT_PATH =
            "C:\\Users\\Admin\\Desktop\\java\\Naeherungsverfahren\\LehmannCode\\outputDirectory\\";

    public static void main(String[] args) {

        List<String> listOfFiles = listFilesUsingJavaIO(PATH);

        listOfFiles.sort(Comparator.comparingInt(TestMain::extractInt));

        for (int i = 0; i < listOfFiles.size(); i++) {

            System.out.println("currently performing operation " + i);

            String fileName = listOfFiles.get(i);
            String outputFileName = OUTPUT_PATH + "result_matrix_" + (i + 2) + "_kreuz_" + (i + 2) + ".txt";

            try {
                LGS lgs = new Input().readTxtFile(PATH + fileName);

                IMatrix m = lgs.matrix();
                IVektor v = lgs.vektor();

                String output = createOutputString(m, v);

                System.out.println(toWolframString(m, v));

                //new Output().writeToFile(outputFileName, output);

            } catch (IOException re) {
                System.out.println(re.getMessage());
            }

        }
    }

    public static String createOutputString(IMatrix m, IVektor v) {
        long timeBeforeGauss = System.currentTimeMillis();
        IVektor loesungMitGauss = new GaussAlgorithmus(new ExceptionHandler())
                .loeseGleichungssystem(m, v);
        long timeAfterGauss = System.currentTimeMillis();

        long timeBeforeInverser = System.currentTimeMillis();
        IVektor loesungMitInverser = new InverseMatrixVerfahren(new ExceptionHandler()).loeseGleichungssystem(m, v);
        long timeAfterInverser = System.currentTimeMillis();

        long timeBeforeDeterminante = System.currentTimeMillis();
        IVektor loesungMitDeterminante = new DeterminatenVerfahren(new ExceptionHandler()).loeseGleichungssystem(m, v);
        long timeAfterDeterminante = System.currentTimeMillis();

        long timeForGauss = timeAfterGauss - timeBeforeGauss;
        long timeForInverse = timeAfterInverser - timeBeforeInverser;
        long timeForDeterminante = timeAfterDeterminante - timeBeforeDeterminante;

        IMatrix inverse = m.getInverseMatrix();

        String outputGauss = "Lösung für Gauss\n" + loesungMitGauss.toString() + "\nZeit: " + timeForGauss + " ms";

        String outputInverse = "\n\nLösung für Inverse\n" + loesungMitInverser.toString() + "\nZeit: "
                + timeForInverse + " ms" +
                ((inverse.getAnzahlZeilen() <= 10) ? "\ninverse: \n" + inverse : "");

        String outputDeterminante = "\n\nLösung für Determinante\n" + loesungMitDeterminante.toString() + "\nZeit "
                + timeForDeterminante + " ms";

        return outputGauss + outputInverse + outputDeterminante;
    }

    public static String toWolframString(IMatrix m, IVektor v) {
        double[][] matrix = m.getMatrix();
        double[] vektor = v.getVektor();

        String wolfram = "{";

        for (int i = 0; i < m.getAnzahlZeilen(); i++) {
            wolfram += "{";
            for (int j = 0; j < m.getAnzahlSpalten(); j++) {
                wolfram += matrix[i][j];
                wolfram += ((j == m.getAnzahlSpalten() - 1) ? "" : ",");
            }
            wolfram += "}";
            wolfram += ((i == m.getAnzahlZeilen() - 1) ? "}" : ",");
        }

        wolfram += "*{";
        for (int i = 0; i < vektor.length; i++) {
            wolfram += "{";
            wolfram += ("x" + (i + 1));
            wolfram += "}";
            wolfram += ((i == vektor.length - 1) ? "}" : ",");
        }

        wolfram += "={";
        for (int i = 0; i < vektor.length; i++) {
            wolfram += "{";
            wolfram += vektor[i];
            wolfram += "}";
            wolfram += ((i == vektor.length - 1) ? "}" : ",");
        }
        return wolfram;
    }

    public static List<String> listFilesUsingJavaIO(String dir) {
        return Stream.of(new File(dir).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toList());
    }

    public static int extractInt(String s) {
        String num = s.replaceAll("\\D", "");
        return num.isEmpty() ? 0 : Integer.parseInt(num);
    }
}
