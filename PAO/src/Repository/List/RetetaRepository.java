package Repository.List;

import Model.Consult;
import Model.Doctor;
import Model.Pacient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import Model.Reteta;
import Repository.CRUDRepository;

public class RetetaRepository implements CRUDRepository<Reteta> {
    private Reteta[] storedReteta = new Reteta[0];
    public boolean add(Reteta ret) {
        try {
            List<Reteta> arrlist = new ArrayList<>(
                    Arrays.asList(storedReteta)
            );
            arrlist.add(ret);
            storedReteta = arrlist.toArray(storedReteta);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Reteta[] getAll() {
        return new Reteta[0];
    }

    @Override
    public boolean update(Reteta entity) {
        return false;
    }


    public Reteta[] getall() {
        return storedReteta;
    }

    public Reteta findById(int idReteta) {
        int n=storedReteta.length;
        for(int i=0; i<n; i++)
        {
            int id=storedReteta[i].getIdReteta();
            if(id==idReteta)
                return storedReteta[i];
        }
        System.out.println("Nu exista reteta cu id-ul cautat:");
        return null;
    }

    @Override
    public int getPret(int idConsult) {
        return 0;
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

    public boolean update(int idReteta,int idConsult, int idPacient, int nrMedicamente, ArrayList<String> listaMedicamente) {
        try {
            int n = storedReteta.length;
            for (int i = 0; i < n; i++) {
                int id1 = storedReteta[i].getIdReteta();
                if (id1 == idReteta) {
                    storedReteta[i].setIdConsult(idConsult);
                    storedReteta[i].setIdPacient(idPacient);
                    storedReteta[i].setNrMedicamente(nrMedicamente);
                    storedReteta[i].setListaMedicamente(listaMedicamente);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }


    public boolean delete(int idReteta) {
        try {
            int n = storedReteta.length;
            for (int i = 0; i < n; i++) {
                int id = storedReteta[i].getIdReteta();
                if (id == idReteta) {
                    Reteta[] nouStoredReteta = new Reteta[storedReteta.length - 1];
                    int j = 0;
                    for (int k = 0; k < storedReteta.length; k++) {
                        if (k != i) {
                            nouStoredReteta[j] = storedReteta[k];
                            j++;
                        }
                    }
                    storedReteta = nouStoredReteta;
                    n = storedReteta.length;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public Reteta checkReteta(int idPacient, ArrayList<String> listaAlergii) {
        int ok=0;
        for (int i = 0; i < storedReteta.length; i++) {
            Reteta reteta = storedReteta[i];
            if (reteta.getIdPacient() == idPacient) {
                ArrayList<String> listaMedicamente = reteta.getListaMedicamente();
                for (int j = 0; j < listaMedicamente.size(); j++) {
                    String medicament = listaMedicamente.get(j);
                    if (listaAlergii.contains(medicament)) {
                        listaMedicamente.remove(j);
                        j--;
                        ok=1;
                    }
                }
                if (ok==1)
                    System.out.println("S-au gasit medicamente in reteta pacientului la care avea alergie si s-au eliminat:");
                else
                    System.out.println("Nu s-au gasit medicamente in reteta pacientului la care are alergie:");
                reteta.setListaMedicamente(listaMedicamente);
                return reteta;
            }
        }
        System.out.println("Nu s-a gasit reteta pentru pacientul respectiv:");
        return null;
    }

    public int getIdConsult(int idPacient) {
        int n=storedReteta.length;
        int idConsult=0;
        for(int i=0; i<n; i++){
            if(storedReteta[i].getIdPacient()==idPacient) {
                idConsult = storedReteta[i].getIdConsult();
                return idConsult;
            }
        }
        System.out.println("Nu s-a gasit consult pentru pacientul respectiv:");
        return -1;
    }
}
