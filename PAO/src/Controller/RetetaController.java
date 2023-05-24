package Controller;

import Model.Reteta;
import Service.RetetaService;
import config.DatabaseConfiguration;

import java.util.ArrayList;

public class RetetaController {
    private RetetaService retetaService;

    public RetetaController(DatabaseConfiguration conection) {
        this.retetaService = new RetetaService(conection);
    }

    public Reteta findById(int idReteta) {
        return retetaService.findById(idReteta);
    }

    public boolean add(int idReteta, int idConsult, int idPacient, int nrMedicamente, ArrayList<String> listaMedicamente) {
        return retetaService.add(idReteta, idConsult, idPacient, nrMedicamente, listaMedicamente);
    }

    public boolean update(int idReteta, int idConsult, int idPacient, int nrMedicamente, ArrayList<String> listaMedicamente) {
        return retetaService.update(idReteta, idConsult, idPacient, nrMedicamente, listaMedicamente);
    }

    public Reteta[] getall() {
        return retetaService.getall();
    }

    public boolean delete(int idReteta) {
        return retetaService.delete(idReteta);
    }


    public Reteta checkReteta(int idPacient, ArrayList<String> listaAlergii) {
        return retetaService.checkReteta(idPacient, listaAlergii);
    }

    public int getIdConsult(int idPacient) {
        return retetaService.getIdConsult(idPacient);
    }
}
