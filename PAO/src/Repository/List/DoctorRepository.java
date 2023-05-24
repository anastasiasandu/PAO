package Repository.List;

import Model.Consult;
import Model.Doctor;
import Model.Pacient;
import Model.Reteta;
import Repository.CRUDRepository;

import java.util.*;

public class DoctorRepository implements CRUDRepository<Doctor> {
    private Doctor[] storedDoctors = new Doctor[0];
    public boolean add(Doctor doctor) {
        try {
            List<Doctor> arrlist = new ArrayList<>(
                    Arrays.asList(storedDoctors)
            );
            arrlist.add(doctor);
            storedDoctors = arrlist.toArray(storedDoctors);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }


    public Doctor[] getAll() {
        return storedDoctors;
    }

    @Override
    public boolean update(Doctor entity) {
        return false;
    }

    public Doctor findById(int idDoctor) {
        int n=storedDoctors.length;
        for(int i=0; i<n; i++)
        {
            int id=storedDoctors[i].getIdPersoana();
            if(id==idDoctor)
                return storedDoctors[i];
        }
        System.out.println("Nu exista doctor cu id-ul cautat:");
        return null;
    }

    @Override
    public int getPret(int idConsult) {
        return 0;
    }

    public boolean update(int idDoctor, String nume, String prenume, int varsta, int sex, String nrTelefon, int idSpital, int salariu, String specializare, SortedMap<String, List<Integer>> programDoctor) {
        try {
            int n = storedDoctors.length;
            for (int i = 0; i < n; i++) {
                int id = storedDoctors[i].getIdPersoana();
                if (id == idDoctor) {
                    storedDoctors[i].setNume(nume);
                    storedDoctors[i].setPrenume(prenume);
                    storedDoctors[i].setVarsta(varsta);
                    storedDoctors[i].setSex(sex);
                    storedDoctors[i].setNrTelefon(nrTelefon);
                    storedDoctors[i].setIdSpital(idSpital);
                    storedDoctors[i].setSalariu(salariu);
                    storedDoctors[i].setSpecializare(specializare);
                    storedDoctors[i].setProgramDoctor(programDoctor);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean delete(int idDoctor) {
        try {
            int n = storedDoctors.length;
            for (int i = 0; i < n; i++) {
                int id = storedDoctors[i].getIdPersoana();
                if (id == idDoctor) {
                    Doctor[] nouStoredDoctors = new Doctor[storedDoctors.length - 1];
                    int j = 0;
                    for (int k = 0; k < storedDoctors.length; k++) {
                        if (k != i) {
                            nouStoredDoctors[j] = storedDoctors[k];
                            j++;
                        }
                    }
                    storedDoctors = nouStoredDoctors;
                    n = storedDoctors.length;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public Doctor checkDoctor(String probPacient, String zi, int ora) {
        int n=storedDoctors.length;
        SortedMap<String, List<Integer> > programDoctor = new TreeMap<>();
        List <Integer> interval = new ArrayList<>();
        for(int i=0; i<n; i++)
        {
            String specializare=storedDoctors[i].getSpecializare();
            if(specializare.equals(probPacient)) {
                programDoctor=storedDoctors[i].getProgramDoctor();
                interval=programDoctor.get(zi);
                if (ora >= interval.get(0) && ora <= interval.get(1))
                    return storedDoctors[i];
            }
        }
        System.out.println("Nu exista doctor disponibil pentru pacient la data respectiva");
        return null;
    }

    public Doctor[] getSortedDoctors() {
        int n=storedDoctors.length;
        for(int i=0; i<n-1; i++)
            for(int j=i+1; j<n; j++)
                if (storedDoctors[i].getSalariu()<storedDoctors[j].getSalariu())
                {
                    Doctor aux;
                    aux=storedDoctors[i];
                    storedDoctors[i]=storedDoctors[j];
                    storedDoctors[j]=aux;
                }
        return storedDoctors;
    }

    public Doctor getDoctor() {
        int n=storedDoctors.length;
        int maxOre=0, ore=0, index=0;
        SortedMap<String, List<Integer> > programDoctor = new TreeMap<>();
        List <Integer> interval = new ArrayList<>();
        for(int i=0; i<n; i++)
        {
            ore=0;
            programDoctor=storedDoctors[i].getProgramDoctor();
            for(String key : programDoctor.keySet()) {
                interval=programDoctor.get(key);
                ore+=interval.get(1)-interval.get(0);
            }
            if(ore>maxOre)
            {
                maxOre=ore;
                index=i;
            }
        }
        double salariu = storedDoctors[index].getSalariu() + (0.15 * storedDoctors[index].getSalariu());
        storedDoctors[index].setSalariu((int) salariu);
        return storedDoctors[index];
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
