package Model;

import java.util.*;

public class Doctor extends Persoana{
    private int idSpital;
    private int salariu;
    private String specializare;
    private SortedMap<String, List<Integer> > programDoctor = new TreeMap<>();
    public void setIdSpital(int idSpital) {
        this.idSpital = idSpital;
    }

    public void setSalariu(int salariu) {
        this.salariu = salariu;
    }

    public void setSpecializare(String specializare) {
        this.specializare = specializare;
    }

    public void setProgramDoctor(SortedMap<String, List<Integer>> programDoctor) {
        this.programDoctor = programDoctor;
    }

    public int getIdSpital() {
        return idSpital;
    }

    public int getSalariu() {
        return salariu;
    }

    public String getSpecializare() {
        return specializare;
    }

    public SortedMap<String, List<Integer>> getProgramDoctor() {
        return programDoctor;
    }

    public Doctor(int idPersoana, String nume, String prenume, int varsta, String specializare, SortedMap<String, List<Integer>> programDoctor) {
        super(idPersoana, nume, prenume, varsta);
        this.specializare = specializare;
        this.programDoctor = programDoctor;
    }

    public Doctor() {
        super();
        this.idSpital=0;
        this.salariu=0;
        this.specializare="";
        this.programDoctor=new TreeMap<>();
    }

    public Doctor(int idPersoana, String nume, String prenume, int varsta, int sex, String nrTelefon, int idSpital, int salariu, String specializare, SortedMap<String, List<Integer>> programDoctor) {
        super(idPersoana, nume, prenume, varsta, sex, nrTelefon);
        this.idSpital = idSpital;
        this.salariu = salariu;
        this.specializare = specializare;
        this.programDoctor = programDoctor;
    }

    public Doctor(int idPersoana, int registrationNo, String nume, String prenume, int varsta, int sex, String nrTelefon, int idSpital, int salariu, String specializare, SortedMap<String, List<Integer>> programDoctor) {
        super(idPersoana, registrationNo, nume, prenume, varsta, sex, nrTelefon);
        this.idSpital = idSpital;
        this.salariu = salariu;
        this.specializare = specializare;
        this.programDoctor = programDoctor;
    }

    public Doctor(int idSpital, int salariu, String specializare, SortedMap<String, List<Integer>> programDoctor) {
        this.idSpital = idSpital;
        this.salariu = salariu;
        this.specializare = specializare;
        this.programDoctor = programDoctor;
    }

    public Doctor(int idPersoana, String nume, String prenume, int varsta, int idSpital, int salariu, String specializare, SortedMap<String, List<Integer>> programDoctor) {
        super(idPersoana, nume, prenume, varsta);
        this.idSpital = idSpital;
        this.salariu = salariu;
        this.specializare = specializare;
        this.programDoctor = programDoctor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Doctor doctor = (Doctor) o;
        return idSpital == doctor.idSpital && salariu == doctor.salariu && specializare.equals(doctor.specializare) && programDoctor.equals(doctor.programDoctor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idSpital, salariu, specializare, programDoctor);
    }

    @Override
    public String toString() {
        return super.toString()+ "Doctor{" +
                "idSpital=" + idSpital +
                ", salariu=" + salariu +
                ", specializare='" + specializare + '\'' +
                ", programDoctor=" + programDoctor +
                '}';
    }
}
