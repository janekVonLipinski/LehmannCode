package LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.InverseMatrixVerfahren;

import LehmannCode.Matrizen.IMatrix;
import LehmannCode.Vektor.IVektor;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.ExceptionHandling.ExceptionHandler;
import LehmannCode.VerfahrenZurLoesungVonLinearenGleichungssystememen.LGSLoeser;

public class InverseMatrixVerfahren implements LGSLoeser {

    private final ExceptionHandler exceptionHandler;

    public InverseMatrixVerfahren(ExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    @Override
    public IVektor loeseGleichungssystem(IMatrix m, IVektor v) {

        exceptionHandler.istGleichungssystemValide(m, v);
        exceptionHandler.istGleichungssystemEindeutigLoesbar(m);

        IMatrix inverseMatrix = m.getInverseMatrix();

        return v.multipliziere(inverseMatrix);
    }
}
