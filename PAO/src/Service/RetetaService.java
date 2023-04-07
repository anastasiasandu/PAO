package Service;

import Model.Pacient;
import Model.Reteta;
import Repository.PacientRepository;
import Repository.RetetaRepository;

import java.util.ArrayList;

public class RetetaService {
    private RetetaRepository retetaRepository;

    public RetetaService() {
        retetaRepository = new RetetaRepository();
    }

    public boolean addReteta(int idReteta, int idConsult, int idPacient, int nrMedicamente, ArrayList<String> listaMedicamente){
        Reteta ret = new Reteta(idReteta, idConsult, idPacient, nrMedicamente,  listaMedicamente);
        return this.retetaRepository.add(ret);
    }

    public boolean updateReteta (int id, int idReteta, int idConsult, int idPacient, int nrMedicamente, ArrayList<String> listaMedicamente) {
        return this.retetaRepository.updateReteta(id, idReteta, idConsult, idPacient,nrMedicamente, listaMedicamente);
    }

    public Reteta[] getallReteta() {
        return this.retetaRepository.getallReteta();
    }

    public Reteta findById(int idReteta) {
        return this.retetaRepository.findById(idReteta);
    }

    public boolean deleteReteta(int idReteta) {
        return this.retetaRepository.deleteReteta(idReteta);
    }


    public Reteta checkReteta(int idPacient, ArrayList<String> listaAlergii) {
        return this.retetaRepository.checkReteta(idPacient, listaAlergii);
    }

    public int getIdConsult(int idPacient) {
        return this.retetaRepository.getIdConsult(idPacient);
    }
}
