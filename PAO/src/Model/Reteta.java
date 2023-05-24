package Model;

import java.util.ArrayList;
import java.util.Objects;

public class Reteta {
    private int idReteta;

    int registrationNo;
    private int idConsult;
    private int idPacient;

    private int nrMedicamente;
    private ArrayList<String> listaMedicamente = new ArrayList<String>();



    public int getRegistrationNo() {
        return registrationNo;
    }

    public int getIdReteta() {
        return idReteta;
    }

    public int getIdConsult() {
        return idConsult;
    }

    public int getIdPacient() {
        return idPacient;
    }

    public int getNrMedicamente() {
        return nrMedicamente;
    }

    public void setRegistrationNo(int registrationNo) {
        this.registrationNo = registrationNo;
    }

    public void setNrMedicamente(int nrMedicamente) {
        this.nrMedicamente = nrMedicamente;
    }

    public ArrayList<String> getListaMedicamente() {
        return listaMedicamente;
    }

    public void setIdPacient(int idPacient) {
        this.idPacient = idPacient;
    }

    public void setIdReteta(int idReteta) {
        this.idReteta = idReteta;
    }

    public void setIdConsult(int idConsult) {
        this.idConsult = idConsult;
    }

    public void setListaMedicamente(ArrayList<String> listaMedicamente) {
        this.listaMedicamente = listaMedicamente;
    }

    public Reteta(int idReteta, int registrationNo, int idConsult, int idPacient, int nrMedicamente, ArrayList<String> listaMedicamente) {
        this.idReteta = idReteta;
        this.registrationNo = registrationNo;
        this.idConsult = idConsult;
        this.idPacient = idPacient;
        this.nrMedicamente = nrMedicamente;
        this.listaMedicamente = listaMedicamente;
    }

    public Reteta(int idReteta, int idPacient, ArrayList<String> listaMedicamente) {
        this.idReteta = idReteta;
        this.idPacient = idPacient;
        this.listaMedicamente = listaMedicamente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reteta reteta = (Reteta) o;
        return idReteta == reteta.idReteta && registrationNo==reteta.registrationNo && idConsult == reteta.idConsult && idPacient == reteta.idPacient && nrMedicamente == reteta.nrMedicamente && listaMedicamente.equals(reteta.listaMedicamente);
    }

    public Reteta(int idReteta, int idConsult, int idPacient, int nrMedicamente, ArrayList<String> listaMedicamente) {
        this.idReteta = idReteta;
        this.idConsult = idConsult;
        this.idPacient = idPacient;
        this.nrMedicamente = nrMedicamente;
        this.listaMedicamente = listaMedicamente;
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNo, idReteta, idConsult, idPacient, nrMedicamente, listaMedicamente);
    }

    @Override
    public String toString() {
        return "Reteta{" +
                " idReteta=" + idReteta +
                ", registrationNo=" + registrationNo +
                ", idConsult=" + idConsult +
                ", idPacient=" + idPacient +
                ", nrMedicamente=" + nrMedicamente +
                ", listaMedicamente=" + listaMedicamente +
                '}';
    }
}
