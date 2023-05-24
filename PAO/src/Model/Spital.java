package Model;

import java.util.Objects;

public class Spital {
    private int idSpital;
    private String lastName;
    private String adresa;
    private String oras;
    private String nrTelefon;
    private int anInfiintare;

    public int getIdSpital() {
        return idSpital;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getOras() {
        return oras;
    }

    public String getNrTelefon() {
        return nrTelefon;
    }

    public int getAnInfiintare() {
        return anInfiintare;
    }

    public void setIdSpital(int idSpital) {
        this.idSpital = idSpital;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setOras(String oras) {
        this.oras = oras;
    }

    public void setNrTelefon(String nrTelefon) {
        this.nrTelefon = nrTelefon;
    }

    public void setAnInfiintare(int anInfiintare) {
        this.anInfiintare = anInfiintare;
    }

    public Spital() {
        this(0,"","","","",0);
    }

    public Spital(int idSpital, String lastName, String adresa, String oras, String nrTelefon, int anInfiintare) {
        this.idSpital = idSpital;
        this.lastName = lastName;
        this.adresa = adresa;
        this.oras = oras;
        this.nrTelefon = nrTelefon;
        this.anInfiintare = anInfiintare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spital spital = (Spital) o;
        return idSpital == spital.idSpital && anInfiintare == spital.anInfiintare && lastName.equals(spital.lastName) && adresa.equals(spital.adresa) && oras.equals(spital.oras) && nrTelefon.equals(spital.nrTelefon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSpital, lastName, adresa, oras, nrTelefon, anInfiintare);
    }
}

