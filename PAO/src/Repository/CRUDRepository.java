package Repository;

import Model.Consult;
import Model.Doctor;
import Model.Reteta;

import java.util.ArrayList;

public interface CRUDRepository<T> {
    boolean add(T entity);
    T[] getAll();
    boolean update(T entity);

    boolean delete(int a);

    public T findById(int id);
    public int getPret(int idConsult);
    public Doctor checkDoctor(String probPacient, String zi, int ora);
    public Doctor[] getSortedDoctors();
    public Doctor getDoctor();
    public String getProbPacient(int idPacient);
    public ArrayList<String> getLista(int idPacient);
    public Reteta checkReteta(int idPacient, ArrayList<String> listaAlergii);
    public int getIdConsult(int idPacient);

}