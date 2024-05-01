package LehmannCode.Vektor;

import LehmannCode.Matrix.Matrix;

import java.util.Arrays;

public class Vektor {

    private final double[] vektorWerte;

    public Vektor(double[] werte) {
        this.vektorWerte = werte;
    }

    public Vektor(Vektor v) {
        this.vektorWerte = Arrays.copyOf(v.vektorWerte, v.vektorWerte.length);
    }

    public double[] getVektor() {
        return vektorWerte;
    }

    public Vektor multipliziere(Matrix matrix) {

        Matrix vektorAlsMatrix = transformiereVektorInMatrix();
        Matrix neueMatrix = matrix.multipliziere(vektorAlsMatrix);

        return transfromiereMatrixInVektor(neueMatrix);
    }

    private Vektor transfromiereMatrixInVektor(Matrix neueMatrix) {
        double[][] array = neueMatrix.getMatrix();
        double[] vektor = new double[array.length];

        for (int i = 0; i < array.length; i++) {
            vektor[i] = array[i][0];
        }

        return new Vektor(vektor);
    }

    private Matrix transformiereVektorInMatrix() {
        double[][] vektorAlsArray = new double[vektorWerte.length][1];

        for (int i = 0; i < vektorWerte.length; i++) {
            vektorAlsArray[i][0] = vektorWerte[i];
        }

        return new Matrix(vektorAlsArray);
    }

    public Vektor subtrahiere(Vektor v2) {
        double[] werteVonV2 = v2.getVektor();
        double[] differenz = new double[werteVonV2.length];

        for (int i = 0; i < werteVonV2.length; i++) {
            differenz[i] = werteVonV2[i] - vektorWerte[i];
        }

        return new Vektor(differenz);
    }

    @Override
    public String toString() {
        return Arrays.toString(vektorWerte);
    }
}
