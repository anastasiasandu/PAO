import Controller.ConsultController;
import Controller.DoctorController;
import Controller.PacientController;
import Controller.RetetaController;
import Model.Consult;
import Model.Doctor;
import Model.Pacient;
import Model.Reteta;
import Service.AuditService;
import config.DatabaseConfiguration;

import java.io.IOException;
import java.util.*;

public class Main {
    public static <DataBaseConfiguration> void main(String[] args) throws IOException {

        DatabaseConfiguration conection=new DatabaseConfiguration();
        var jdbcPacientRepository= new PacientController(conection);

        PacientController pacientController = new PacientController(conection);
        DoctorController doctorController = new DoctorController(conection);
        RetetaController retetaController = new RetetaController(conection);
        ConsultController consultController = new ConsultController(conection);

        AuditService auditService = new AuditService();
//        try {
//
//            // Perform actions and log them
//            auditService.logAction("Action 1");
//            auditService.logAction("Action 2");
//            auditService.logAction("Action 3");
//
//        } catch (IOException e) {
//            System.out.println("Error: " + e.getMessage());
//        }


        System.out.println("HR Application ");
        Scanner sc = new Scanner(System.in);
        String option;
        do {
            System.out.println("\nInsert option: \"1\": Add Pacient; \"1.1\": Get Pacient by id; " +
                    "\"1.2\": Update Pacient by id; \"1.3\": Delete Pacient by id; \"1.4\":List all Pacients; \"1.5\": A verifica daca exista doctor pentru un pacient anume; \n " +
                    "\"2\": Add Doctor; \"2.1\": Get Doctor by id; " +
                    "\"2.2\": Update Doctor by id; \"2.3\": Delete Doctor by id;" +
                    "\"2.4\": List all Doctors; \"2.5\":Sortati doctorii descrescator dupa salariu \"2.6\": Cresteti salariul cu 15% doctorului care lucreaza cel mai mult \n" +
                    "\"3\": Add reteta; \"3.1\": Get reteta by id; \"3.2\": Update reteta by id; \"3.3\": Delete reteta by id; \"3.4\": List all Retete;  " +
                    "\"3.5\": Eliminati din reteta medicamentele la care pacientul are alergii\n" +
                    "\"4\"Add Consult; \"4.1\" Get Consult by id; \"4.2\"Update Consult by id; \"4.3\"Delete Consult by id; \"4.4\"List all Consults " +
                    "\"4.5\"Cauta pretul consultului unui pacient dat;  "  );
            option = sc.nextLine().trim();
            System.out.println("You inserted: " + option);
            switch (option) {
                case ("1") -> {
                    System.out.println("Id-ul pacientului:");
                    int idPacient= Integer.parseInt(sc.nextLine());
                    System.out.println("Numele pacientului:");
                    String numePacient=sc.nextLine();
                    System.out.println("Prenumele pacientului:");
                    String prenumePacient=sc.nextLine();
                    System.out.println("Varsta pacientului:");
                    int varstaPacient= Integer.parseInt(sc.nextLine());
                    System.out.println("Sexul pacientului, 1 pentru feminin si 0 pentru masculin:");
                    int sexPacient= Integer.parseInt(sc.nextLine());
                    System.out.println("Numarul de telefon al pacientului:");
                    String nrTelefonPacient =sc.nextLine();
                    System.out.println("Id-ul spitalului la care se duce pacientul:");
                    int idSpitalPacient= Integer.parseInt(sc.nextLine());
                    System.out.println("Tipul problemei pacientului:");
                    String tipProblema=sc.nextLine();
                    System.out.println("Numarul de alergii ale pacientului:");
                    int nrAlergii= Integer.parseInt(sc.nextLine());
                    ArrayList<String> listaAlergii=new ArrayList<>();
                    if(nrAlergii > 0){
                        System.out.println("Alergiile sunt:");
                        for (int i=0; i<nrAlergii; i++) {
                            String alergie = sc.nextLine();
                            listaAlergii.add(alergie);
                        }
                    }
                    boolean addedPacient = pacientController.add(idPacient, numePacient, prenumePacient, varstaPacient, sexPacient, nrTelefonPacient, idSpitalPacient, tipProblema, nrAlergii, listaAlergii);
                    System.out.println("Pacient added: " + addedPacient);
                    auditService.logAction("Adaugare pacient");
                }
                case ("1.1") -> {
                    System.out.println("Id-ul pacientului cautat:");
                    int idPacient= Integer.parseInt(sc.nextLine());
                    Pacient pacient = pacientController.findById(idPacient);
                    System.out.println(pacient);
                    auditService.logAction("Find by id");
                }
                case ("1.2") -> {
                    System.out.println("Id-ul pacientului care trebuie updated:");
                    int idPacient= Integer.parseInt(sc.nextLine());
                    Pacient pacient = pacientController.findById(idPacient);
                    if(pacient != null)
                    {
                        System.out.println("Numele nou al pacientului:");
                        String numePacient=sc.nextLine();
                        System.out.println("Prenumele nou al pacientului:");
                        String prenumePacient=sc.nextLine();
                        System.out.println("Varsta noua apacientului:");
                        int varstaPacient= Integer.parseInt(sc.nextLine());
                        System.out.println("Sexul nou al pacientului, 1 pentru feminin si 0 pentru masculin:");
                        int sexPacient= Integer.parseInt(sc.nextLine());
                        System.out.println("Numarul de telefon nou al pacientului:");
                        String nrTelefonPacient =sc.nextLine();
                        System.out.println("Id-ul spitalului nou la care se duce pacientul:");
                        int idSpitalPacient= Integer.parseInt(sc.nextLine());
                        System.out.println("Tipul nou al problemei pacientului:");
                        String tipProblema=sc.nextLine();
                        System.out.println("Numarul nou al de alergii ale pacientului:");
                        int nrAlergii= Integer.parseInt(sc.nextLine());
                        ArrayList<String> listaAlergii=new ArrayList<>();
                        if(nrAlergii > 0){
                            System.out.println("Alergiile noi sunt:");
                            for (int i=0; i<nrAlergii; i++) {
                                String alergie = sc.nextLine();
                                listaAlergii.add(alergie);
                            }
                        }
                        boolean updatedPacient = pacientController.update(idPacient, numePacient, prenumePacient, varstaPacient, sexPacient, nrTelefonPacient, idSpitalPacient,tipProblema, nrAlergii, listaAlergii);
                        System.out.println("Pacient updated: " + updatedPacient);
                        auditService.logAction("Update pacient");
                    }
                    else System.out.println("Nu exista pacient cu id-ul dat");
                }
                case ("1.3") -> {
                    System.out.println("Id-ul pacientului care trebuie sters:");
                    int idPacient= Integer.parseInt(sc.nextLine());
                    Pacient pacient = pacientController.findById(idPacient);
                    if(pacient != null)
                    {
                        boolean deletedPacient = pacientController.delete(idPacient);
                        System.out.println("Pacient deleted: " + deletedPacient);
                        auditService.logAction("Delete pacient");
                    }
                    else System.out.println("Nu exista pacient cu id-ul dat");
                }
                case ("1.4") -> {
                    Pacient[] allPacients = pacientController.getall();
                    System.out.println("Pacients: " + Arrays.toString(allPacients));
                    auditService.logAction("Get all pacients");
                }
                case ("1.5") -> {
                    System.out.println("Id-ul pacientului care trebuie verificat:");
                    int idPacient= Integer.parseInt(sc.nextLine());
                    Pacient pacient = pacientController.findById(idPacient);
                    if(pacient != null)
                    {
                        System.out.println("Ziua in care este liber pacientul:");
                        String zi=sc.nextLine();
                        System.out.println("Ora la care este liber pacientul:");
                        int ora= Integer.parseInt(sc.nextLine());
                        String ProbPacient = pacientController.getProbPacient(idPacient);
                        Doctor doctor = doctorController.checkDoctor(ProbPacient,zi,ora);
                        if (doctor != null)
                            System.out.println("Pacientul poate fi consultat de doctorul: " + doctor);
                        auditService.logAction("Check doctor for patient");
                    }
                    else System.out.println("Nu exista pacient cu id-ul dat");

                }
                case ("2") -> {
                    System.out.println("Id-ul doctorului:");
                    int idDoctor= Integer.parseInt(sc.nextLine());
                    System.out.println("Numele doctorului:");
                    String numeDoctor=sc.nextLine();
                    System.out.println("Prenumele doctorului:");
                    String prenumeDoctor=sc.nextLine();
                    System.out.println("Varsta doctorului:");
                    int varstaDoctor= Integer.parseInt(sc.nextLine());
                    System.out.println("Sexul doctorului, 1 pentru feminin si 0 pentru masculin:");
                    int sexDoctor= Integer.parseInt(sc.nextLine());
                    System.out.println("Numarul de telefon al doctorului:");
                    String nrTelefonDoctor =sc.nextLine();
                    System.out.println("Id-ul spitalului la care lucreaza doctorul:");
                    int idSpitalDoctor= Integer.parseInt(sc.nextLine());
                    System.out.println("Salariul doctorului:");
                    int salariuDoctor= Integer.parseInt(sc.nextLine());
                    System.out.println("Specializarea doctorului:");
                    String specializareDoctor=sc.nextLine();
                    SortedMap<String, List<Integer>> programDoctor=new TreeMap<>();
                    List <Integer> interval = new ArrayList<>();
//                    SortedMap<String, List<Integer>> programDoctor = new TreeMap<>();
//                    List<Integer> interval;

                    System.out.println("Programul doctorului:");
                    System.out.println("Luni lucreaza intre orele:");
                    int start = Integer.parseInt(sc.nextLine());
                    int end = Integer.parseInt(sc.nextLine());
                    interval = new ArrayList<>();
                    interval.add(start);
                    interval.add(end);
                    programDoctor.put("Luni", interval);

                    System.out.println("Marti lucreaza intre orele:");
                    start = Integer.parseInt(sc.nextLine());
                    end = Integer.parseInt(sc.nextLine());
                    interval = new ArrayList<>();
                    interval.add(start);
                    interval.add(end);
                    programDoctor.put("Marti", interval);

                    System.out.println("Miercuri lucreaza intre orele:");
                    start = Integer.parseInt(sc.nextLine());
                    end = Integer.parseInt(sc.nextLine());
                    interval = new ArrayList<>();
                    interval.add(start);
                    interval.add(end);
                    programDoctor.put("Miercuri", interval);

                    System.out.println("Joi lucreaza intre orele:");
                    start = Integer.parseInt(sc.nextLine());
                    end = Integer.parseInt(sc.nextLine());
                    interval = new ArrayList<>();
                    interval.add(start);
                    interval.add(end);
                    programDoctor.put("Joi", interval);

                    System.out.println("Vineri lucreaza intre orele:");
                    start = Integer.parseInt(sc.nextLine());
                    end = Integer.parseInt(sc.nextLine());
                    interval = new ArrayList<>();
                    interval.add(start);
                    interval.add(end);
                    programDoctor.put("Vineri", interval);

                    System.out.println("Sambata lucreaza intre orele:");
                    start = Integer.parseInt(sc.nextLine());
                    end = Integer.parseInt(sc.nextLine());
                    interval = new ArrayList<>();
                    interval.add(start);
                    interval.add(end);
                    programDoctor.put("Sambata", interval);

                    System.out.println("Duminica lucreaza intre orele:");
                    start = Integer.parseInt(sc.nextLine());
                    end = Integer.parseInt(sc.nextLine());
                    interval = new ArrayList<>();
                    interval.add(start);
                    interval.add(end);
                    programDoctor.put("Duminica", interval);

                   // interval = new ArrayList<>();
                    boolean addedDoctor = doctorController.addDoctor(idDoctor, numeDoctor, prenumeDoctor, varstaDoctor, sexDoctor, nrTelefonDoctor, idSpitalDoctor, salariuDoctor, specializareDoctor, programDoctor);
                    System.out.println("Doctor added: " + addedDoctor);
                    auditService.logAction("Add doctor");
                }
                case ("2.1") -> {
                    System.out.println("Id-ul doctorului cautat:");
                    int idDoctor= Integer.parseInt(sc.nextLine());
                    Doctor doctor = doctorController.findById(idDoctor);
                    System.out.println(doctor);
                    auditService.logAction("Find doctor by id");
                }
                case ("2.2") -> {
                    System.out.println("Id-ul doctorului care trebuie updated:");
                    int idDoctor= Integer.parseInt(sc.nextLine());
                    Doctor doctor = doctorController.findById(idDoctor);
                    if(doctor!=null)
                    {
                        System.out.println("Numele nou al doctorului:");
                        String numeDoctor=sc.nextLine();
                        System.out.println("Prenumele nou al doctorului:");
                        String prenumeDoctor=sc.nextLine();
                        System.out.println("Varsta noua a doctorului:");
                        int varstaDoctor= Integer.parseInt(sc.nextLine());
                        System.out.println("Sexul nou al doctorului, 1 pentru feminin si 0 pentru masculin:");
                        int sexDoctor= Integer.parseInt(sc.nextLine());
                        System.out.println("Numarul nou al de telefon al doctorului:");
                        String nrTelefonDoctor =sc.nextLine();
                        System.out.println("Id-ul nou al spitalului la care lucreaza doctorul:");
                        int idSpitalDoctor= Integer.parseInt(sc.nextLine());
                        System.out.println("Salariul nou al doctorului:");
                        int salariuDoctor= Integer.parseInt(sc.nextLine());
                        System.out.println("Specializarea noua al doctorului:");
                        String specializareDoctor=sc.nextLine();
                        SortedMap<String, List<Integer>> programDoctor=new TreeMap<>();
                        List <Integer> interval = new ArrayList<>();
                        System.out.println("Programul nou al doctorului:");
                        System.out.println("Luni lucreaza intre orele:");
                        int start= Integer.parseInt(sc.nextLine());
                        int end= Integer.parseInt(sc.nextLine());
                        interval.add(start);
                        interval.add(end);
                        programDoctor.put("Luni",interval);
                        interval = new ArrayList<>();
                        System.out.println("Marti lucreaza intre orele:");
                        start= Integer.parseInt(sc.nextLine());
                        end= Integer.parseInt(sc.nextLine());
                        interval.add(start);
                        interval.add(end);
                        programDoctor.put("Marti",interval);
                        interval = new ArrayList<>();
                        System.out.println("Miercuri lucreaza intre orele:");
                        start= Integer.parseInt(sc.nextLine());
                        end= Integer.parseInt(sc.nextLine());
                        interval.add(start);
                        interval.add(end);
                        programDoctor.put("Miercuri",interval);
                        interval = new ArrayList<>();
                        System.out.println("Joi lucreaza intre orele:");
                        start= Integer.parseInt(sc.nextLine());
                        end= Integer.parseInt(sc.nextLine());
                        interval.add(start);
                        interval.add(end);
                        programDoctor.put("Joi",interval);
                        interval = new ArrayList<>();
                        System.out.println("Vineri lucreaza intre orele:");
                        start= Integer.parseInt(sc.nextLine());
                        end= Integer.parseInt(sc.nextLine());
                        interval.add(start);
                        interval.add(end);
                        programDoctor.put("Vineri",interval);
                        interval = new ArrayList<>();
                        System.out.println("Sambata lucreaza intre orele:");
                        start= Integer.parseInt(sc.nextLine());
                        end= Integer.parseInt(sc.nextLine());
                        interval.add(start);
                        interval.add(end);
                        programDoctor.put("Sambata",interval);
                        interval = new ArrayList<>();
                        System.out.println("Duminica lucreaza intre orele:");
                        start= Integer.parseInt(sc.nextLine());
                        end= Integer.parseInt(sc.nextLine());
                        interval.add(start);
                        interval.add(end);
                        programDoctor.put("Duminica",interval);
                        interval = new ArrayList<>();
                        boolean updatedEmployee = doctorController.update(idDoctor, numeDoctor, prenumeDoctor, varstaDoctor, sexDoctor, nrTelefonDoctor, idSpitalDoctor, salariuDoctor, specializareDoctor, programDoctor);
                        System.out.println("Doctor updated: " + updatedEmployee);
                        auditService.logAction("Update doctor");
                    }
                    else System.out.println("Nu exista doctor cu id-ul dat");
                }
                case ("2.3") -> {
                    System.out.println("Id-ul doctorului care trebuie sters:");
                    int idDoctor= Integer.parseInt(sc.nextLine());
                    Doctor doctor = doctorController.findById(idDoctor);
                    if(doctor != null)
                    {
                        boolean deletedDoctor = doctorController.delete(idDoctor);
                        System.out.println("Doctor deleted: " + deletedDoctor);
                        auditService.logAction("Delete doctor");
                    }
                    else System.out.println("Nu exista doctor cu id-ul dat");
                }
                case ("2.4") -> {
                    Doctor[] allDoctors = doctorController.getAll();
                    System.out.println("Doctors: " + Arrays.toString(allDoctors));
                    auditService.logAction("Get all doctors");
                }
                case ("2.5") -> {
                    System.out.println("Doctorii inainte de a fi sortati:");
                    Doctor[] allDoctors = doctorController.getAll();
                    System.out.println("Doctors: " + Arrays.toString(allDoctors));
                    System.out.println("Doctorii dupa ce au fost sortati:");
                    Doctor[] sortedAllDoctors = doctorController.getSortedDoctors();
                    System.out.println("Doctors: " + Arrays.toString(sortedAllDoctors));
                    auditService.logAction("Sorted doctors");
                }
                case ("2.6") -> {
                    Doctor doctor = doctorController.getDoctor();
                    System.out.println("Doctorului care lucreaza cel mai mult i s-a marit salariul cu 15%. Asa arata:" + doctor);
                    auditService.logAction("Salary raised");
                }
                case ("3") ->{
                    System.out.println("Id-ul retetei:");
                    int idReteta= Integer.parseInt(sc.nextLine());
                    System.out.println("Id-ul consultului:");
                    int idConsult= Integer.parseInt(sc.nextLine());
                    System.out.println("Id-ul pacientului:");
                    int idPacient= Integer.parseInt(sc.nextLine());
                    System.out.println("Numarul de medicamente ale retetei:");
                    int nrMedicamente= Integer.parseInt(sc.nextLine());
                    ArrayList<String> listaMedicamente=new ArrayList<>();
                    if(nrMedicamente > 0){
                        System.out.println("Medicamentele sunt:");
                        for (int i=0; i<nrMedicamente; i++) {
                            String medicament = sc.nextLine();
                            listaMedicamente.add(medicament);
                        }
                    }
                    boolean addedReteta = retetaController.add( idReteta, idConsult, idPacient, nrMedicamente, listaMedicamente);
                    System.out.println("Reteta added: " + addedReteta);
                    auditService.logAction("Add recipe");
                }
                case ("3.1") -> {
                    System.out.println("Id-ul retetei cautate:");
                    int idReteta= Integer.parseInt(sc.nextLine());
                    Reteta reteta = retetaController.findById(idReteta);
                    System.out.println(reteta);
                    auditService.logAction("Find recipe by id");
                }
                case ("3.2") -> {
                    System.out.println("Id-ul retetei care trebuie updated:");
                    int idReteta= Integer.parseInt(sc.nextLine());
                    Reteta reteta = retetaController.findById(idReteta);
                    if(reteta != null)
                    {
                        System.out.println("Id-ul nou al consultului:");
                        int idConsult= Integer.parseInt(sc.nextLine());
                        System.out.println("Id-ul nou al pacientului:");
                        int idPacient= Integer.parseInt(sc.nextLine());
                        System.out.println("Numarul nou de medicamente ale retetei:");
                        int nrMedicamente= Integer.parseInt(sc.nextLine());
                        ArrayList<String> listaMedicamente=new ArrayList<>();
                        if(nrMedicamente > 0){
                            System.out.println("Medicamentele sunt:");
                            for (int i=0; i<nrMedicamente; i++) {
                                String medicament = sc.nextLine();
                                listaMedicamente.add(medicament);
                            }
                        }
                        boolean updatedReteta = retetaController.update(idReteta, idConsult, idPacient, nrMedicamente, listaMedicamente);
                        System.out.println("Reteta updated: " + updatedReteta);
                        auditService.logAction("Update recipe");
                    }
                    else System.out.println("Nu exista reteta cu id-ul dat");
                }
                case ("3.3") -> {
                    System.out.println("Id-ul retetei care trebuie sterse:");
                    int idReteta= Integer.parseInt(sc.nextLine());
                    Reteta reteta = retetaController.findById(idReteta);
                    if(reteta != null)
                    {
                        boolean deletedReteta = retetaController.delete(idReteta);
                        System.out.println("Reteta deleted: " + deletedReteta);
                        auditService.logAction("Delete recipe");
                    }
                    else System.out.println("Nu exista reteta cu id-ul dat");
                }
                case ("3.4") -> {
                    Reteta[] allReteta = retetaController.getall();
                    System.out.println("Retete: " + Arrays.toString(allReteta));
                    auditService.logAction("Get all recipes");
                }
                case ("3.5") -> {
                    System.out.println("Id-ul pacientului a carui reteta trebuie verificata:");
                    int idPacient= Integer.parseInt(sc.nextLine());
                    Pacient pacient = pacientController.findById(idPacient);
                    if(pacient != null){
                        ArrayList<String> listaAlergii =new ArrayList<>();
                        listaAlergii = pacientController.getLista(idPacient);
                        Reteta reteta = retetaController.checkReteta(idPacient, listaAlergii);
                        System.out.println("Reteta este " + reteta);
                        auditService.logAction("Check recipe");
                    }
                    else System.out.println("Nu exista pacient cu id-ul dat");
                }
                case ("4") -> {
                    System.out.println("Id-ul consultului:");
                    int idConsult= Integer.parseInt(sc.nextLine());
                    System.out.println("Pretul consultului:");
                    int pretConsult= Integer.parseInt(sc.nextLine());
                    System.out.println("Data consultului:");
                    String dataConsult=sc.nextLine();
                    boolean addedConsult = consultController.add( idConsult, pretConsult, dataConsult);
                    System.out.println("Consult added: " + addedConsult);
                    auditService.logAction("Add consult");
                }
                case ("4.1") -> {
                    System.out.println("Id-ul consultului cautat:");
                    int idConsult= Integer.parseInt(sc.nextLine());
                    Consult consult = consultController.findById(idConsult);
                    System.out.println(consult);
                    auditService.logAction("Get consult by id");
                }
                case ("4.2") -> {
                    System.out.println("Id-ul consultului care trebuie updated:");
                    int idConsult= Integer.parseInt(sc.nextLine());
                    Consult consult = consultController.findById(idConsult);
                    if(consult != null)
                    {
                        System.out.println("Pretul nou al consultului:");
                        int pretConsult= Integer.parseInt(sc.nextLine());
                        System.out.println("Data noua a consultului:");
                        String dataConsult=sc.nextLine();

                        boolean updatedConsult = consultController.update(idConsult, pretConsult, dataConsult);
                        System.out.println("Consult updated: " + updatedConsult);
                        auditService.logAction("Update consult");
                    }
                    else System.out.println("Nu exista consult cu id-ul dat");
                }
                case ("4.3") -> {
                    System.out.println("Id-ul consultului care trebuie sters:");
                    int idConsult= Integer.parseInt(sc.nextLine());
                    Consult consult = consultController.findById(idConsult);
                    if(consult != null)
                    {
                        boolean deletedConsult = consultController.delete(idConsult);
                        System.out.println("Consult deleted: " + deletedConsult);
                        auditService.logAction("Delete consult");
                    }
                    else System.out.println("Nu exista consult cu id-ul dat");
                }
                case ("4.4") -> {
                    Consult[] allConsult = consultController.getall();
                    System.out.println("Consults: " + Arrays.toString(allConsult));
                    auditService.logAction("Get all consults");
                }
                case ("4.5") -> {
                    System.out.println("Id-ul pacientului pentru care trebuie aflat pretul consultului:");
                    int idPacient = Integer.parseInt(sc.nextLine());
                    int idConsult = retetaController.getIdConsult(idPacient);
                    int pret = consultController.getPret(idConsult);
                    System.out.println("Pretul consultului este: " + pret);
                    auditService.logAction("Get the price of consult");
                }
            }
        } while (!option.equals("stop"));
        sc.close();
    }
}