package Repository;

import Model.Pacient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import Model.Reteta;

public class RetetaRepository {
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

    public Reteta[] getallReteta() {
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

    public boolean updateReteta(int id, int idReteta, int idConsult, int idPacient, int nrMedicamente, ArrayList<String> listaMedicamente) {
        try {
            int n = storedReteta.length;
            for (int i = 0; i < n; i++) {
                int id1 = storedReteta[i].getIdReteta();
                if (id1 == id) {
                    storedReteta[i].setIdReteta(idReteta);
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


    public boolean deleteReteta(int idReteta) {
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
