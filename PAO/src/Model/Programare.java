package Model;

import java.util.Objects;

public class Programare {
    private int idProgramare;
    private String dataProgramare;

    public int getIdProgramare() {
        return idProgramare;
    }
    public String getDataProgramare() {
        return dataProgramare;
    }

    public void setIdProgramare(int idProgramare) {
        this.idProgramare = idProgramare;
    }

    public void setDataProgramare(String dataProgramare) {
        this.dataProgramare = dataProgramare;
    }

    public Programare() {
        this(0,0,"");
    }

    public Programare(int idProgramare, int idPacient, String dataProgramare) {
        this.idProgramare = idProgramare;
        this.dataProgramare = dataProgramare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Programare that = (Programare) o;
        return idProgramare == that.idProgramare && dataProgramare.equals(that.dataProgramare);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProgramare, dataProgramare);
    }
}
