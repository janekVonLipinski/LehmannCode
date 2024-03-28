package LehmannCode.Vektor;

import LehmannCode.Matrix.MatrizenVerfahren.Matrix;

import java.util.Arrays;

public class Vektor {

    private final double[] vektorWerte;

    public Vektor(double[] werte) {
        this.vektorWerte = werte;
    }

    public Vektor(Vektor v) {
        this.vektorWerte = Arrays.copyOf(v.vektorWerte, v.vektorWerte.length);
    }

    public double[] getVektorWerte() {
        return vektorWerte;
    }

    public Vektor multipliziere(Matrix matrix) {

        double[][] vektorAlsArray = new double[vektorWerte.length][1];
        for (int i = 0; i < vektorWerte.length; i++) {
            vektorAlsArray[i][0] = vektorWerte[i];
        }

        Matrix vektorAlsMatrix = new Matrix(vektorAlsArray);
        Matrix neueMatrix = matrix.multipliziere(vektorAlsMatrix);

        double[][] array = neueMatrix.getMatrix();

        double[] vektor = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            vektor[i] = array[i][0];
        }

        return new Vektor(vektor);
    }

    public Vektor subtrahiere(Vektor v2) {
        double[] werteVonV2 = v2.getVektorWerte();
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
