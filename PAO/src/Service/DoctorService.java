package Service;

import Model.Doctor;
import Model.Pacient;
import Repository.CRUDRepository;
import Repository.JDBC.JdbcDoctorRepository;
import Repository.JDBC.JdbcPacientRepository;
import Repository.List.DoctorRepository;
import Util.RegNumberSingleton;
import config.DatabaseConfiguration;

import java.util.List;
import java.util.SortedMap;

public class DoctorService {
    private CRUDRepository<Doctor> doctorRepository;

    private RegNumberSingleton regNumberSingleton = RegNumberSingleton.getInstance();

    public DoctorService(DatabaseConfiguration conection) {
        doctorRepository = new JdbcDoctorRepository(conection);
    }


    public boolean addDoctor(int idPersoana, String nume, String prenume, int varsta, int sex, String nrTelefon, int idSpital, int salariu, String specializare, SortedMap<String, List<Integer>> programDoctor) {
        Doctor doctor = new Doctor(idPersoana, nume, prenume, varsta, sex, nrTelefon, idSpital, salariu, specializare, programDoctor);
        doctor.setRegistrationNo(regNumberSingleton.getNextCode());
        return this.doctorRepository.add(doctor);
    }

    public Doctor[] getAll() {
        return this.doctorRepository.getAll();
    }

    public Doctor findById(int idDoctor) {
        return doctorRepository.findById(idDoctor);
    }
    public boolean update(int idPersoana, String nume, String prenume, int varsta, int sex, String nrTelefon, int idSpital, int salariu, String specializare, SortedMap<String, List<Integer>> programDoctor) {
        //Pacient pac = new Pacient(idPersoana, nume, prenume, varsta, sex, nrTelefon, idSpital, nrAlergii, listaAlergii);
        Doctor doc= new Doctor(idPersoana, nume, prenume, varsta, sex, nrTelefon, idSpital, salariu, specializare, programDoctor);
        return this.doctorRepository.update(doc);
    }


    public boolean delete(int idDoctor) {
        return this.doctorRepository.delete(idDoctor);
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
