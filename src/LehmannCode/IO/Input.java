package LehmannCode.IO;

import LehmannCode.IO.Model.LGS;
import LehmannCode.Matrizen.IMatrix;
import LehmannCode.Matrizen.MatrixImplementierung.Matrix;
import LehmannCode.Vektor.IVektor;
import LehmannCode.Vektor.Vektor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Input {

    private static final int INDEX_OF_VEKTOR_VALUES_AFTER_SPLIT = 1;
    private static final int INDEX_OF_MATRIX_VALUES_AFTER_SPLIT = 0;

    public List<String> readFile(String pathname) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(pathname));
        List<String> read = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null) {
            if (!line.isEmpty() && line.charAt(0) == '/') {
                continue;
            }
            read.add(line);
        }

        return read;
    }

    public LGS readTxtFile(String pathname) throws IOException {
        List<String> read = readFile(pathname);

        double[] vektor = new double[read.size()];
        double[][] matrix = new double[read.size()][read.size()];

        for (int i = 0; i < read.size(); i++) {
            String[] values = read.get(i).split(";");

            String vektorValue = values[INDEX_OF_VEKTOR_VALUES_AFTER_SPLIT];
            vektor[i] = Double.parseDouble(vektorValue);

            String[] matrixValues = values[INDEX_OF_MATRIX_VALUES_AFTER_SPLIT].split(" ");

            for (int j = 0; j < matrixValues.length; j++) {
                matrix[i][j] = Double.parseDouble(matrixValues[j]);
            }
        }

        IMatrix m = new Matrix(matrix);
        IVektor v = new Vektor(vektor);

        return new LGS(m, v);
    }
}
