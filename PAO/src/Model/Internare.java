package Model;

import java.util.Objects;

public class Internare {
    private int idInternare;
    private boolean status;
    private String dataInternare;
    private String dataExternare;

    public int getIdInternare() {
        return idInternare;
    }

    public boolean isStatus() {
        return status;
    }

    public String getDataInternare() {
        return dataInternare;
    }

    public String getDataExternare() {
        return dataExternare;
    }

    public void setIdInternare(int idInternare) {
        this.idInternare = idInternare;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setDataInternare(String dataInternare) {
        this.dataInternare = dataInternare;
    }

    public void setDataExternare(String dataExternare) {
        this.dataExternare = dataExternare;
    }

    public Internare() {
        this(0,0,false,"","");
    }

    public Internare(int idInternare, int idPacient, boolean status, String dataInternare, String dataExternare) {
        this.idInternare = idInternare;
        this.status = status;
        this.dataInternare = dataInternare;
        this.dataExternare = dataExternare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Internare internare = (Internare) o;
        return idInternare == internare.idInternare && status == internare.status && dataInternare.equals(internare.dataInternare) && dataExternare.equals(internare.dataExternare);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idInternare, status, dataInternare, dataExternare);
    }
}
