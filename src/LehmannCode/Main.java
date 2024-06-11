package LehmannCode;

import LehmannCode.IO.Input;
import LehmannCode.IO.Model.LGS;
import LehmannCode.IO.Output;
import LehmannCode.Matrizen.IMatrix;
import LehmannCode.Vektor.IVektor;

import java.io.IOException;

import static LehmannCode.OtherStuff.TestMain.createOutputString;

public class Main {

    public static void main(String[] args) {
        String inputFile = args[0];
        String outputFile = args[1];

        try {
            LGS lgs = new Input().readTxtFile(inputFile);

            IMatrix m = lgs.matrix();
            IVektor v = lgs.vektor();

            String output = createOutputString(m, v);

            new Output().writeToFile(outputFile, output);

        } catch (IOException re) {
            System.out.println(re.getMessage());
        }
    }
}
