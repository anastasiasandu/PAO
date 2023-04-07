package Service;

import Model.Consult;
import Model.Reteta;
import Repository.ConsultRepository;
import Repository.RetetaRepository;

import java.util.ArrayList;

public class ConsultService {
    private ConsultRepository consultRepository;

    public ConsultService() {
        consultRepository = new ConsultRepository();
    }

    public boolean addConsult(int idConsult, int pret, String dataConsult){
        Consult con= new Consult(idConsult, pret, dataConsult);
        return this.consultRepository.add(con);
    }

    public boolean updateConsult(int id, int idConsult, int pret, String dataConsult){
        return this.consultRepository.updateConsult(id, idConsult, pret, dataConsult);
    }

    public Consult[] getallConsult() {
        return this.consultRepository.getallConsult();
    }

    public Consult findById(int idConsult) {
        return this.consultRepository.findById(idConsult);
    }

    public boolean deleteConsult(int idConsult) {
        return this.consultRepository.deleteConsult(idConsult);
    }


    public int getPret(int idConsult) {
        return this.consultRepository.getPret(idConsult);
    }
}
