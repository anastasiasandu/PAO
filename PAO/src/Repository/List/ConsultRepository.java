package Repository.List;

import Model.Consult;
import Model.Doctor;
import Model.Reteta;
import Repository.CRUDRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConsultRepository implements CRUDRepository<Consult> {
    private Consult[] storedConsult = new Consult[0];
    public boolean add(Consult con) {
        try {
            List<Consult> arrlist = new ArrayList<>(
                    Arrays.asList(storedConsult)
            );
            arrlist.add(con);
            storedConsult = arrlist.toArray(storedConsult);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }


    @Override
    public Consult[] getAll() {
        return new Consult[0];
    }

    @Override
    public boolean update(Consult entity) {
        return false;
    }

    public Consult[] getall() {
        return storedConsult;
    }

    public Consult findById(int idConsult) {
        int n=storedConsult.length;
        for(int i=0; i<n; i++)
        {
            int id=storedConsult[i].getIdConsult();
            if(id==idConsult)
                return storedConsult[i];
        }
        System.out.println("Nu exista consult cu id-ul cautat:");
        return null;
    }

    public boolean update(int idConsult, int pret, String dataConsult) {
        try {
            int n = storedConsult.length;
            for (int i = 0; i < n; i++) {
                int id1 = storedConsult[i].getIdConsult();
                if (id1 == idConsult) {
                    storedConsult[i].setPret(pret);
                    storedConsult[i].setDataConsult(dataConsult);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }


    public boolean delete(int idConsult) {
        try {
            int n = storedConsult.length;
            for (int i = 0; i < n; i++) {
                int id = storedConsult[i].getIdConsult();
                if (id == idConsult) {
                    Consult[] nouStoredConsult = new Consult[storedConsult.length - 1];
                    int j = 0;
                    for (int k = 0; k < storedConsult.length; k++) {
                        if (k != i) {
                            nouStoredConsult[j] = storedConsult[k];
                            j++;
                        }
                    }
                    storedConsult = nouStoredConsult;
                    n = storedConsult.length;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public int getPret(int idConsult) {
        int n=storedConsult.length;
        int pret=0;
        for(int i=0; i<n; i++)
        {
            if(idConsult==storedConsult[i].getIdConsult())
            {
                pret=storedConsult[i].getPret();
                return pret;
            }
        }
        System.out.println(" Nu s-a gasit consult cu id-ul respectiv");
        return -1;
    }

    @Override
    public Doctor checkDoctor(String probPacient, String zi, int ora) {
        return null;
    }

    @Override
    public Doctor[] getSortedDoctors() {
        return new Doctor[0];
    }

    @Override
    public Doctor getDoctor() {
        return null;
    }

    @Override
    public String getProbPacient(int idPacient) {
        return null;
    }

    @Override
    public ArrayList<String> getLista(int idPacient) {
        return null;
    }

    @Override
    public Reteta checkReteta(int idPacient, ArrayList<String> listaAlergii) {
        return null;
    }

    @Override
    public int getIdConsult(int idPacient) {
        return 0;
    }
}
