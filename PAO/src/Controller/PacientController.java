package Controller;

import Model.Pacient;
import Service.PacientService;

import java.util.ArrayList;

public class PacientController {
    private PacientService pacientService;

    public PacientController() {
        this.pacientService = new PacientService();
    }

    public Pacient findById(int idPacient) {
        return pacientService.findById(idPacient);
    }

    public boolean addPacient(int idPersoana, String nume, String prenume, int varsta, int sex, String nrTelefon, int idSpital, String tipProblema,  int nrAlergii, ArrayList<String> listaAlergii) {
        nume = capitalizeString(nume);
        prenume = capitalizeString(prenume);
        return pacientService.addPacient(idPersoana, nume, prenume, varsta, sex, nrTelefon, idSpital, tipProblema, nrAlergii, listaAlergii);
    }

    private String capitalizeString (String str) {
        return str.substring(0,1).toUpperCase() + str.substring(1).toLowerCase();
    }


    public boolean updatePacient(int id, int idPersoana, String nume, String prenume, int varsta, int sex, String nrTelefon, int idSpital, String tipProblema, int nrAlergii, ArrayList<String> listaAlergii) {
        nume = capitalizeString(nume);
        prenume = capitalizeString(prenume);
        return pacientService.updatePacient(id, idPersoana, nume, prenume, varsta, sex, nrTelefon, idSpital, tipProblema, nrAlergii, listaAlergii);
    }

    public Pacient[] getallPacient() {
        return pacientService.getallPacient();
    }

    public boolean deletePacient(int idPacient) {
        return pacientService.deletePacient(idPacient);
    }

    public String getProbPacient(int idPacient) {
        return pacientService.checkPacient(idPacient);
    }

    public ArrayList<String> getLista(int idPacient) {
        return pacientService.getLista(idPacient);
    }
}
