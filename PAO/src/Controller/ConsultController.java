package Controller;

import Model.Consult;
import Model.Reteta;
import Service.ConsultService;
import Service.RetetaService;

import java.util.ArrayList;

public class ConsultController {
    private ConsultService consultService;

    public ConsultController() {
        this.consultService = new ConsultService();
    }

    public Consult findById(int idConsult) {
        return consultService.findById(idConsult);
    }

    public boolean addConsult(int idConsult, int pret, String dataConsult) {
        return consultService.addConsult(idConsult, pret, dataConsult);
    }

    public boolean updateConsult(int id, int idConsult, int pret, String dataConsult) {
        return consultService.updateConsult(id, idConsult, pret, dataConsult);
    }

    public Consult[] getallConsult() {
        return consultService.getallConsult();
    }

    public boolean deleteConsult(int idConsult) {
        return consultService.deleteConsult(idConsult);
    }

    public int getPret(int idConsult) {
        return consultService.getPret(idConsult);
    }
}
