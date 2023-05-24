package Service;

import Model.Consult;
import Model.Pacient;
import Repository.CRUDRepository;
import Repository.JDBC.JdbcConsultRepository;
import Repository.JDBC.JdbcPacientRepository;
import Repository.List.ConsultRepository;
import Util.RegNumberSingleton;
import config.DatabaseConfiguration;

public class ConsultService {

    private CRUDRepository<Consult> consultRepository;

    private RegNumberSingleton regNumberSingleton = RegNumberSingleton.getInstance();

    public ConsultService(DatabaseConfiguration conection) {
        consultRepository = new JdbcConsultRepository(conection);
    }


    public boolean add(int idConsult,  int pret, String dataConsult){
        Consult con= new Consult(idConsult, pret, dataConsult);
        con.setRegistrationNo(regNumberSingleton.getNextCode());
        return this.consultRepository.add(con);
    }

    public boolean update(int idConsult, int pret, String dataConsult){
        Consult con= new Consult( idConsult,  pret, dataConsult);
        return this.consultRepository.update(con);
    }

    public Consult[] getall() {
        return this.consultRepository.getAll();
    }

    public Consult findById(int idConsult) {
        return this.consultRepository.findById(idConsult);
    }

    public boolean delete(int idConsult) {
        return this.consultRepository.delete(idConsult);
    }


    public int getPret(int idConsult) {
        return this.consultRepository.getPret(idConsult);
    }
}
