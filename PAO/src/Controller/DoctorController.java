package Controller;

import Model.Doctor;
import Model.Pacient;
import Service.DoctorService;
import Service.PacientService;
import config.DatabaseConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

public class DoctorController {
    private DoctorService doctorService;

    public DoctorController(DatabaseConfiguration conection) {
        this.doctorService = new DoctorService(conection);
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

    public Doctor[] getAll() {
        return doctorService.getAll();
    }

    private String capitalizeString (String str) {
        return str.substring(0,1).toUpperCase() + str.substring(1).toLowerCase();
    }
    public Doctor findById(int idDoctor) {
        return doctorService.findById(idDoctor);
    }

    public boolean update(int idPersoana, String nume, String prenume, int varsta, int sex, String nrTelefon, int idSpital, int salariu, String specializare, SortedMap<String, List<Integer>> programDoctor) {
        nume = capitalizeString(nume);
        prenume = capitalizeString(prenume);
        return doctorService.update(idPersoana, nume, prenume, varsta, sex, nrTelefon, idSpital, salariu, specializare, programDoctor);
    }

    public boolean delete(int idDoctor) {
        return doctorService.delete(idDoctor);
    }

    public Doctor[] getSortedDoctors() {
        return doctorService.getSortedDoctors();
    }
}
