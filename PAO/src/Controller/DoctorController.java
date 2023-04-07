package Controller;

import Model.Doctor;
import Model.Pacient;
import Service.DoctorService;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

public class DoctorController {
    private DoctorService doctorService;

    public DoctorController() {
        this.doctorService = new DoctorService();
    }

    public Doctor checkDoctor(String probPacient, String zi, int ora) {
        return doctorService.checkDoctor(probPacient, zi, ora);
    }

    public Doctor getDoctor() {
        return doctorService.getDoctor();
    }

    public boolean addDoctor(int idPersoana, String nume, String prenume, int varsta, int sex, String nrTelefon, int idSpital, int salariu, String specializare, SortedMap<String, List<Integer>> programDoctor) {
        nume = capitalizeString(nume);
        prenume = capitalizeString(prenume);
        return doctorService.addDoctor(idPersoana, nume, prenume, varsta, sex, nrTelefon, idSpital, salariu, specializare, programDoctor);
    }

    public Doctor[] getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    private String capitalizeString (String str) {
        return str.substring(0,1).toUpperCase() + str.substring(1).toLowerCase();
    }
    public Doctor findById(int idDoctor) {
        return doctorService.findById(idDoctor);
    }

    public boolean updateDoctor(int id, int idPersoana, String nume, String prenume, int varsta, int sex, String nrTelefon, int idSpital, int salariu, String specializare, SortedMap<String, List<Integer>> programDoctor) {
        nume = capitalizeString(nume);
        prenume = capitalizeString(prenume);
        return doctorService.updateDoctor(id, idPersoana, nume, prenume, varsta, sex, nrTelefon, idSpital, salariu, specializare, programDoctor);
    }

    public boolean deleteDoctor(int idDoctor) {
        return doctorService.deleteDoctor(idDoctor);
    }

    public Doctor[] getSortedDoctors() {
        return doctorService.getSortedDoctors();
    }
}
