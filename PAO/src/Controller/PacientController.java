package Controller;

import Model.Pacient;
import Service.PacientService;
import config.DatabaseConfiguration;

import java.util.ArrayList;

public class PacientController {
    private PacientService pacientService;

    public PacientController(DatabaseConfiguration conection) {
        this.pacientService = new PacientService(conection);
    }

    public Pacient findById(int idPacient) {
        return pacientService.findById(idPacient);
    }

    public boolean add(int idPersoana, String nume, String prenume, int varsta, int sex, String nrTelefon, int idSpital, String tipProblema,  int nrAlergii, ArrayList<String> listaAlergii) {
        nume = capitalizeString(nume);
        prenume = capitalizeString(prenume);
        return pacientService.add(idPersoana, nume, prenume, varsta, sex, nrTelefon, idSpital, tipProblema, nrAlergii, listaAlergii);
    }

    private String capitalizeString (String str) {
        return str.substring(0,1).toUpperCase() + str.substring(1).toLowerCase();
    }


    public boolean update(int idPersoana, String nume, String prenume, int varsta, int sex, String nrTelefon, int idSpital, String tipProblema, int nrAlergii, ArrayList<String> listaAlergii) {
        nume = capitalizeString(nume);
        prenume = capitalizeString(prenume);
        return pacientService.update(idPersoana, nume, prenume, varsta, sex, nrTelefon, idSpital, tipProblema, nrAlergii, listaAlergii);
    }

    public Pacient[] getall() {
        return pacientService.getall();
    }

    public boolean delete(int idPacient) {
        return pacientService.delete(idPacient);
    }

    public String getProbPacient(int idPacient) {
        return pacientService.checkPacient(idPacient);
    }

    public ArrayList<String> getLista(int idPacient) {
        return pacientService.getLista(idPacient);
    }
}
