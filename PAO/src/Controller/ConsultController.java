package Controller;

import Model.Consult;
import Model.Reteta;
import Service.ConsultService;
import Service.PacientService;
import Service.RetetaService;
import config.DatabaseConfiguration;

import java.util.ArrayList;

public class ConsultController {
    private ConsultService consultService;

    public ConsultController(DatabaseConfiguration conection) {
        this.consultService = new ConsultService(conection);
    }

    public Consult findById(int idConsult) {
        return consultService.findById(idConsult);
    }

    public boolean add(int idConsult, int pret, String dataConsult) {
        return consultService.add( idConsult, pret, dataConsult);
    }

    public boolean update(int idConsult, int pret, String dataConsult) {
        return consultService.update( idConsult, pret, dataConsult);
    }

    public Consult[] getall() {
        return consultService.getall();
    }

    public boolean delete(int idConsult) {
        return consultService.delete(idConsult);
    }

    public int getPret(int idConsult) {
        return consultService.getPret(idConsult);
    }
}
