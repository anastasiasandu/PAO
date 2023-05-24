package Service;

import Model.Consult;
import Model.Reteta;
import Repository.CRUDRepository;
import Repository.JDBC.JdbcConsultRepository;
import Repository.JDBC.JdbcRetetaRepository;
import Repository.List.RetetaRepository;
import Util.RegNumberSingleton;
import config.DatabaseConfiguration;

import java.util.ArrayList;

public class RetetaService {


    private CRUDRepository<Reteta> retetaRepository;

    private RegNumberSingleton regNumberSingleton = RegNumberSingleton.getInstance();

    public RetetaService(DatabaseConfiguration conection) {
        retetaRepository = new JdbcRetetaRepository(conection);
    }

    public boolean add(int idReteta, int idConsult, int idPacient, int nrMedicamente, ArrayList<String> listaMedicamente){
        Reteta ret = new Reteta( idReteta, idConsult, idPacient, nrMedicamente,  listaMedicamente);
        ret.setRegistrationNo(regNumberSingleton.getNextCode());
        return this.retetaRepository.add(ret);
    }

    public boolean update(int idReteta, int idConsult, int idPacient, int nrMedicamente, ArrayList<String> listaMedicamente) {
        Reteta ret= new Reteta( idReteta, idConsult, idPacient,nrMedicamente, listaMedicamente);
        return this.retetaRepository.update(ret);
    }

    public Reteta[] getall() {
        return this.retetaRepository.getAll();
    }

    public Reteta findById(int idReteta) {
        return this.retetaRepository.findById(idReteta);
    }

    public boolean delete(int idReteta) {
        return this.retetaRepository.delete(idReteta);
    }


    public Reteta checkReteta(int idPacient, ArrayList<String> listaAlergii) {
        return this.retetaRepository.checkReteta(idPacient, listaAlergii);
    }

    public int getIdConsult(int idPacient) {
        return this.retetaRepository.getIdConsult(idPacient);
    }
}
