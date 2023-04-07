package Model;

import java.util.ArrayList;
import java.util.Objects;

public class Pacient extends Persoana {
    private int idSpital;
    private String tipProblema;
    private int nrAlergii;
    private ArrayList<String> listaAlergii = new ArrayList<String>();
    public int getIdSpital() {
        return idSpital;
    }

    public ArrayList<String> getListaAlergii() {
        return listaAlergii;
    }
    public void setIdSpital(int idSpital) {
        this.idSpital = idSpital;
    }

    public int getNrAlergii() {
        return nrAlergii;
    }

    public String getTipProblema() {
        return tipProblema;
    }

    public void setTipProblema(String tipProblema) {
        this.tipProblema = tipProblema;
    }

    public void setNrAlergii(int nrAlergii) {
        this.nrAlergii = nrAlergii;
    }

    public void setListaAlergii(ArrayList<String> listaAlergii) {
        this.listaAlergii = listaAlergii;
    }

    public Pacient(int idPersoana, String nume, String prenume, int varsta, int sex, String nrTelefon, int idSpital, String tipProblema, int nrAlergii, ArrayList<String> listaAlergii) {
        super(idPersoana, nume, prenume, varsta, sex, nrTelefon);
        this.idSpital = idSpital;
        this.tipProblema = tipProblema;
        this.nrAlergii = nrAlergii;
        this.listaAlergii = listaAlergii;
    }

    public Pacient() {
        super();
        this.idSpital=0;
        this.tipProblema="";
        this.listaAlergii=new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Pacient pacient = (Pacient) o;
        return idSpital == pacient.idSpital && nrAlergii == pacient.nrAlergii && tipProblema.equals(pacient.tipProblema) && listaAlergii.equals(pacient.listaAlergii);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idSpital, tipProblema, nrAlergii, listaAlergii);
    }

    @Override
    public String toString() {
        return super.toString()+ "Pacient{" +
                "idSpital=" + idSpital +
                ", tipProblema='" + tipProblema + '\'' +
                ", nrAlergii=" + nrAlergii +
                ", listaAlergii=" + listaAlergii +
                '}';
    }
}
