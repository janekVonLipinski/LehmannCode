package LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.InverseMatrixVerfahren;

import LehmannCode.Matrix.MatrixTransponierung.MatrixHilfsfunktionen;
import LehmannCode.Matrix.Matrix;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.ExceptionHandling.ExceptionHandler;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.LGSLoeser;
import LehmannCode.Vektor.Vektor;
import LehmannCode.Util.Zeile;

public class InverseMatrixVerfahren implements LGSLoeser {

    private final ExceptionHandler exceptionHandler;
    private final MatrixHilfsfunktionen matrixHilfsfunktionen;
    private final Zeile zeilennnn;

    public InverseMatrixVerfahren(ExceptionHandler exceptionHandler, MatrixHilfsfunktionen matrixHilfsfunktionen, Zeile zeile) {
        this.exceptionHandler = exceptionHandler;
        this.matrixHilfsfunktionen = matrixHilfsfunktionen;
        this.zeilennnn = zeile;
    }

    @Override
    public Vektor loeseGleichungssystem(Matrix m, Vektor v) {

        exceptionHandler.istGleichungssystemValide(m, v);
        exceptionHandler.istGleichungssystemEindeutigLoesbar(m);

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
                neueMatrix[zeilenIndex][spaltenIndex] = getKofaktor(m, zeilenIndex, spaltenIndex);
            }
        }

        return new Matrix(neueMatrix);
    }

    private double getKofaktor(Matrix m, int zeile, int spalte) {

        Matrix verkleinerteMatrix = zeilennnn.streicheUebergebeneZeileUndSpalte(m, zeile, spalte);
        return Math.pow(-1, zeile + spalte) * verkleinerteMatrix.getDeterminante();
    }
}
