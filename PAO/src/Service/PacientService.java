package Service;

import Model.Pacient;
import Repository.CRUDRepository;
import Repository.JDBC.JdbcPacientRepository;
import Repository.List.PacientRepository;
import Util.RegNumberSingleton;
import config.DatabaseConfiguration;

import java.util.ArrayList;

public class PacientService {
    private CRUDRepository<Pacient> pacientRepository;

    private RegNumberSingleton regNumberSingleton = RegNumberSingleton.getInstance();

    public PacientService(DatabaseConfiguration conection) {
        pacientRepository = new JdbcPacientRepository(conection);
    }

    public boolean add(int idPersoana, String nume, String prenume, int varsta, int sex, String nrTelefon, int idSpital, String tipProblema, int nrAlergii, ArrayList<String> listaAlergii) {
        Pacient pac = new Pacient(idPersoana, nume, prenume, varsta, sex, nrTelefon, idSpital, tipProblema, nrAlergii, listaAlergii);
        pac.setRegistrationNo(regNumberSingleton.getNextCode());
        return this.pacientRepository.add(pac);
    }

    public boolean update(int idPersoana, String nume, String prenume, int varsta, int sex, String nrTelefon, int idSpital, String tipProblema,  int nrAlergii, ArrayList<String> listaAlergii) {
        Pacient p=new Pacient(idPersoana, nume, prenume, varsta, sex, nrTelefon, idSpital, tipProblema, nrAlergii, listaAlergii);
        return this.pacientRepository.update(p);
    }

    public Pacient[] getall() {
        return this.pacientRepository.getAll();
    }

    public Pacient findById(int idPacient) {
        return this.pacientRepository.findById(idPacient);
    }

    public boolean delete(int idPacient) {
        return this.pacientRepository.delete(idPacient);
    }

    public String checkPacient(int idPacient) {
        return this.pacientRepository.getProbPacient(idPacient);
    }


    public ArrayList<String> getLista(int idPacient) {
        return this.pacientRepository.getLista(idPacient);
    }

}
