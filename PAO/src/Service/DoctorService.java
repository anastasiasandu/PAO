package Service;

import Model.Doctor;
import Model.Pacient;
import Repository.DoctorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

public class DoctorService {
    private DoctorRepository doctorRepository;

    public DoctorService() {
        doctorRepository = new DoctorRepository();
    }

    public boolean addDoctor(int idPersoana, String nume, String prenume, int varsta, int sex, String nrTelefon, int idSpital, int salariu, String specializare, SortedMap<String, List<Integer>> programDoctor) {
        Doctor doctor = new Doctor(idPersoana, nume, prenume, varsta, sex, nrTelefon, idSpital, salariu, specializare, programDoctor);
        return this.doctorRepository.add(doctor);
    }

    public Doctor[] getAllDoctors() {
        return this.doctorRepository.getAll();
    }

    public Doctor findById(int idDoctor) {
        return doctorRepository.findById(idDoctor);
    }
    public boolean updateDoctor(int id, int idPersoana, String nume, String prenume, int varsta, int sex, String nrTelefon, int idSpital, int salariu, String specializare, SortedMap<String, List<Integer>> programDoctor) {
        //Pacient pac = new Pacient(idPersoana, nume, prenume, varsta, sex, nrTelefon, idSpital, nrAlergii, listaAlergii);
        return this.doctorRepository.updateDoctor(id, idPersoana, nume, prenume, varsta, sex, nrTelefon, idSpital, salariu, specializare, programDoctor);
    }


    public boolean deleteDoctor(int idDoctor) {
        return this.doctorRepository.deleteDoctor(idDoctor);
    }

    public Doctor checkDoctor(String probPacient, String zi, int ora) {
        return this.doctorRepository.checkDoctor(probPacient, zi, ora);
    }

    public Doctor[] getSortedDoctors() {
        return this.doctorRepository.getSortedDoctors();
    }

    public Doctor getDoctor() {
        return this.doctorRepository.getDoctor();
    }
}
