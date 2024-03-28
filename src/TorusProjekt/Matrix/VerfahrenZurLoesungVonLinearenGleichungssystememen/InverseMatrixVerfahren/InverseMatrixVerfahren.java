package TorusProjekt.Matrix.VerfahrenZurLoesungVonLinearenGleichungssystememen.InverseMatrixVerfahren;

import TorusProjekt.Matrix.MatrixUtil.MatrixHilfsfunktionen;
import TorusProjekt.Matrix.MatrizenVerfahren.Matrix;
import TorusProjekt.Matrix.VerfahrenZurLoesungVonLinearenGleichungssystememen.LGSLoeser;
import TorusProjekt.Vektor.Vektor;

public class InverseMatrixVerfahren implements LGSLoeser {

    private final MatrixHilfsfunktionen matrixHilfsfunktionen = new MatrixHilfsfunktionen();

    @Override
    public Vektor loeseGleichungssystem(Matrix m, Vektor v) {

        if (m.getAnzahlZeilen() != m.getAnzahlSpalten()) {
            throw new IllegalArgumentException("Matrix muss n x n sein");
        }

        if (m.getAnzahlZeilen() != v.getVektorWerte().length) {
            throw new IllegalArgumentException("Dimensionsfehler");
        }

        if (m.getDeterminante() == 0) {
            throw new IllegalArgumentException("Gleichung ist nicht eindeutig lösbar");
        }

        if (m.getAnzahlSpalten() == 2) {
            double[][] matrix = m.getMatrix();
            double[][] inverseBeiZweiKreuzZweiOhneMultiplikationMitEinsDurchD =
                    {{matrix[1][1], -matrix[0][1]}, {-matrix[1][0], matrix[0][0]}};
            double determinante = m.getDeterminante();
            Matrix inverseMatrix = new Matrix(inverseBeiZweiKreuzZweiOhneMultiplikationMitEinsDurchD)
                    .multipliziere(1 / determinante);
            return v.multipliziere(inverseMatrix);
        }

        Matrix inverseMatrix = getInverseMatrix(m);
        return v.multipliziere(inverseMatrix);
    }

    private Matrix getInverseMatrix(Matrix m) {
        double determinanteVonM = m.getDeterminante();
        double kehrWertVonDeterminante = 1 / determinanteVonM;
        Matrix adjunkteVonM = getAdjunkte(m);
        return adjunkteVonM.multipliziere(kehrWertVonDeterminante);
    }

    private Matrix getAdjunkte(Matrix m) {
        Matrix kofaktorenMatrix = getKofaktorenMatrix(m);
        return matrixHilfsfunktionen.transponiere(kofaktorenMatrix);
    }

    private Matrix getKofaktorenMatrix(Matrix m) {
        double[][] matrix = m.getMatrix();
        double[][] neueMatrix = new double[matrix.length][matrix.length];

        for (int zeilenIndex = 0; zeilenIndex < matrix.length; zeilenIndex++) {
            for (int spaltenIndex = 0; spaltenIndex < matrix.length; spaltenIndex++) {
                neueMatrix[zeilenIndex][spaltenIndex] = matrixHilfsfunktionen.getKofaktor(m, zeilenIndex, spaltenIndex);
            }
        }

        return new Matrix(neueMatrix);
    }
}
