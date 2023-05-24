package Repository.List;

import Model.Consult;
import Model.Doctor;
import Model.Pacient;
import Model.Reteta;
import Repository.CRUDRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PacientRepository implements CRUDRepository<Pacient> {
    private Pacient[] storedPacienti = new Pacient[0];
    public boolean add(Pacient pac) {
        try {
            List<Pacient> arrlist = new ArrayList<>(
                    Arrays.asList(storedPacienti)
            );
            arrlist.add(pac);
            storedPacienti = arrlist.toArray(storedPacienti);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    public Pacient[] getAll() {
        return storedPacienti;
    }

    @Override
    public boolean update(Pacient entity) {
        return false;
    }


    public Pacient findById(int idPacient) {
        int n=storedPacienti.length;
        for(int i=0; i<n; i++)
        {
            int id=storedPacienti[i].getIdPersoana();
            if(id==idPacient)
                return storedPacienti[i];
        }
        System.out.println("Nu exista pacient cu id-ul cautat:");
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

//    void update(Pacient pacient){
//        int n=storedPacienti.length;
//        for(int i=0; i<n; i++)
//        {
//            int id=storedPacienti[i].getIdPersoana();
//            if(id==idPacient)
//                return storedPacienti[i];
//        }
//        System.out.println("Nu exista pacient cu id-ul cautat:");
//        return null;
//    }


    public boolean update(int idPersoana, String nume, String prenume, int varsta, int sex, String nrTelefon, int idSpital, String tipProblema, int nrAlergii, ArrayList<String> listaAlergii) {
        try {
            int n = storedPacienti.length;
            for (int i = 0; i < n; i++) {
                int id = storedPacienti[i].getIdPersoana();
                if (id == idPersoana) {
                    storedPacienti[i].setNume(nume);
                    storedPacienti[i].setPrenume(prenume);
                    storedPacienti[i].setVarsta(varsta);
                    storedPacienti[i].setSex(sex);
                    storedPacienti[i].setNrTelefon(nrTelefon);
                    storedPacienti[i].setIdSpital(idSpital);
                    storedPacienti[i].setTipProblema(tipProblema);
                    storedPacienti[i].setNrAlergii(nrAlergii);
                    storedPacienti[i].setListaAlergii(listaAlergii);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }


    public boolean delete(int idPacient) {
        try {
            int n = storedPacienti.length;
            for (int i = 0; i < n; i++) {
                int id = storedPacienti[i].getIdPersoana();
                if (id == idPacient) {
                    Pacient[] nouStoredPacienti = new Pacient[storedPacienti.length - 1];
                    int j = 0;
                    for (int k = 0; k < storedPacienti.length; k++) {
                        if (k != i) {
                            nouStoredPacienti[j] = storedPacienti[k];
                            j++;
                        }
                    }
                    storedPacienti = nouStoredPacienti;
                    n = storedPacienti.length;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public String getProbPacient(int idPacient) {
        int n = storedPacienti.length;
        String tipProblema = null;
        for (int i = 0; i < n; i++) {
            int id = storedPacienti[i].getIdPersoana();
            if (id == idPacient) {
                tipProblema=storedPacienti[i].getTipProblema();
            }
        }
        return tipProblema;
    }

    public ArrayList<String> getLista(int idPacient) {
        int n = storedPacienti.length;
        ArrayList<String> listaAlergii =new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int id = storedPacienti[i].getIdPersoana();
            if (id == idPacient) {
                listaAlergii = storedPacienti[i].getListaAlergii();
            }
        }
        return listaAlergii;
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
