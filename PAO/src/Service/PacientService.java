package Service;

import Model.Pacient;
import Repository.PacientRepository;

import java.util.ArrayList;

public class PacientService {
    private PacientRepository pacientRepository;

    public PacientService() {
        pacientRepository = new PacientRepository();
    }

    public boolean addPacient(int idPersoana, String nume, String prenume, int varsta, int sex, String nrTelefon, int idSpital, String tipProblema, int nrAlergii, ArrayList<String> listaAlergii) {
        Pacient pac = new Pacient(idPersoana, nume, prenume, varsta, sex, nrTelefon, idSpital, tipProblema, nrAlergii, listaAlergii);
        return this.pacientRepository.add(pac);
    }

    public boolean updatePacient(int id, int idPersoana, String nume, String prenume, int varsta, int sex, String nrTelefon, int idSpital, String tipProblema,  int nrAlergii, ArrayList<String> listaAlergii) {
         return this.pacientRepository.updatePacient(id, idPersoana, nume, prenume, varsta, sex, nrTelefon, idSpital, tipProblema, nrAlergii, listaAlergii);
    }

    public Pacient[] getallPacient() {
        return this.pacientRepository.getAll();
    }

    public Pacient findById(int idPacient) {
        return this.pacientRepository.findById(idPacient);
    }

    public boolean deletePacient(int idPacient) {
        return this.pacientRepository.deletePacient(idPacient);
    }

    public String checkPacient(int idPacient) {
        return this.pacientRepository.getProbPacient(idPacient);
    }


    public ArrayList<String> getLista(int idPacient) {
        return this.pacientRepository.getLista(idPacient);
    }

}
