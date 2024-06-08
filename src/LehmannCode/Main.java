package LehmannCode;

import LehmannCode.IO.Input;
import LehmannCode.IO.Model.LGS;
import LehmannCode.Matrizen.IMatrix;
import LehmannCode.Vektor.IVektor;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.Determinantenverfahren.DeterminatenVerfahren;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.ExceptionHandling.ExceptionHandler;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.GaussAlgorithmus.GaussAlgorithmus;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.GaussAlgorithmus.GaussHilfsFunktionen;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.InverseMatrixVerfahren.InverseMatrixVerfahren;

import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    private static final String PATH =
            "C:\\Users\\Admin\\Desktop\\java\\Naeherungsverfahren\\LehmannCode\\txt_matrix\\matrix.txt";

    private static final String kack = "kack.txt";

    public static void main(String[] args) {

        try {
            LGS lgs = new Input().readTxtFile(PATH);

            IMatrix m = lgs.matrix();
            IVektor v = lgs.vektor();

            String output = createOutputString(m, v);

            System.out.println(output);

            FileOutputStream fileOutputStream = new FileOutputStream(kack);
            fileOutputStream.write(output.getBytes());
            fileOutputStream.close();

        } catch (IOException re) {
            re.getCause();
        }
    }

    private static String createOutputString(IMatrix m, IVektor v) {
        long timeBeforeGauss = System.currentTimeMillis();
        IVektor loesungMitGauss = new GaussAlgorithmus(new ExceptionHandler(), new GaussHilfsFunktionen())
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
}
