package Repository;

import Model.Consult;
import Model.Reteta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConsultRepository {
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

    public Consult[] getallConsult() {
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

    public boolean updateConsult(int id, int idConsult, int pret, String dataConsult) {
        try {
            int n = storedConsult.length;
            for (int i = 0; i < n; i++) {
                int id1 = storedConsult[i].getIdConsult();
                if (id1 == id) {
                    storedConsult[i].setIdConsult(idConsult);
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


    public boolean deleteConsult(int idConsult) {
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
}
