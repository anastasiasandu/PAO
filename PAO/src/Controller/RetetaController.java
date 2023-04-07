package Controller;

import Model.Pacient;
import Model.Reteta;
import Service.PacientService;
import Service.RetetaService;

import java.util.ArrayList;

public class RetetaController {
    private RetetaService retetaService;

    public RetetaController() {
        this.retetaService = new RetetaService();
    }

    public Reteta findById(int idReteta) {
        return retetaService.findById(idReteta);
    }

    public boolean addReteta(int idReteta, int idConsult, int idPacient, int nrMedicamente, ArrayList<String> listaMedicamente) {
        return retetaService.addReteta(idReteta, idConsult, idPacient, nrMedicamente, listaMedicamente);
    }

    public boolean updateReteta(int id, int idReteta, int idConsult, int idPacient, int nrMedicamente, ArrayList<String> listaMedicamente) {
        return retetaService.updateReteta(id, idReteta, idConsult, idPacient, nrMedicamente, listaMedicamente);
    }

    public Reteta[] getallReteta() {
        return retetaService.getallReteta();
    }

    public boolean deleteReteta(int idReteta) {
        return retetaService.deleteReteta(idReteta);
    }


    public Reteta checkReteta(int idPacient, ArrayList<String> listaAlergii) {
        return retetaService.checkReteta(idPacient, listaAlergii);
    }

    public int getIdConsult(int idPacient) {
        return retetaService.getIdConsult(idPacient);
    }
}
