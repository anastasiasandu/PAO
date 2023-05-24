package Model;

import java.util.Objects;

public class Persoana {
    private int idPersoana;

    int registrationNo;
    private String nume;
    private String prenume;
    private int varsta;
    private int sex;
    private String nrTelefon;

    public int getRegistrationNo() {
        return registrationNo;
    }

    public int getIdPersoana() {
        return idPersoana;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public int getVarsta() {
        return varsta;
    }

    public int getSex() {
        return sex;
    }

    public String getNrTelefon() {
        return nrTelefon;
    }

    public void setRegistrationNo(int registrationNo) {
        this.registrationNo = registrationNo;
    }

    public void setIdPersoana(int idPersoana) {
        this.idPersoana = idPersoana;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setNrTelefon(String nrTelefon) {
        this.nrTelefon = nrTelefon;
    }

    public Persoana() {
        this(0,"","",0,2,"");
    }

    public Persoana(int idPersoana, String nume, String prenume, int varsta) {
        this.idPersoana = idPersoana;
        this.nume = nume;
        this.prenume = prenume;
        this.varsta = varsta;
    }

    public Persoana(int idPersoana, String nume, String prenume, int varsta, int sex, String nrTelefon) {
        this.idPersoana = idPersoana;
        this.nume = nume;
        this.prenume = prenume;
        this.varsta = varsta;
        this.sex = sex;
        this.nrTelefon = nrTelefon;
    }

    public Persoana( int idPersoana, int registrationNo, String nume, String prenume, int varsta, int sex, String nrTelefon) {
        this.idPersoana = idPersoana;
        this.registrationNo = registrationNo;
        this.nume = nume;
        this.prenume = prenume;
        this.varsta = varsta;
        this.sex = sex;
        this.nrTelefon = nrTelefon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persoana persoana = (Persoana) o;
        return idPersoana == persoana.idPersoana && varsta == persoana.varsta && nume.equals(persoana.nume) && prenume.equals(persoana.prenume) && sex == persoana.sex && nrTelefon.equals(persoana.nrTelefon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPersoana, nume, prenume, varsta, sex, nrTelefon);
    }

    @Override
    public String toString() {
        return "Persoana{" +
                " idPersoana=" + idPersoana +
                ", registrationNo=" + registrationNo +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", varsta=" + varsta +
                ", sex=" + sex +
                ", nrTelefon='" + nrTelefon + '\'' +
                '}';
    }
}
