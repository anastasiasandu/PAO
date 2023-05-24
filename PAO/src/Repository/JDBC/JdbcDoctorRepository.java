package Repository.JDBC;

import Model.Doctor;
import Model.Pacient;
import Model.Reteta;
import Repository.CRUDRepository;
import config.DatabaseConfiguration;

import java.sql.*;
import java.util.*;


public class JdbcDoctorRepository implements CRUDRepository<Doctor> {

    private Connection conection;

    public JdbcDoctorRepository(DatabaseConfiguration conection) {
        this.conection=conection.getConnection();
    }




//    public String convertToString(SortedMap<String, List<Integer>> map) {
//        StringBuilder sb = new StringBuilder();
//
//        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
//            String key = entry.getKey();
//            List<Integer> values = entry.getValue();
//
//            sb.append(key).append(": ");
//
//            for (int i = 0; i < values.size(); i++) {
//                sb.append(values.get(i));
//
//                if (i < values.size() - 1) {
//                    sb.append(", ");
//                }
//            }
//
//            sb.append("\n");
//        }
//
//        return sb.toString();
//    }
//
//    private SortedMap<String, List<Integer>> convertToProgramDoctor(String programDoctorString) {
//        SortedMap<String, List<Integer>> programDoctor = new TreeMap<>();
//
//        if (programDoctorString != null && !programDoctorString.isEmpty()) {
//            String[] lines = programDoctorString.split("\n");
//
//            for (String line : lines) {
//                String[] keyValue = line.split(":");
//                if (keyValue.length == 2) {
//                    String key = keyValue[0].trim();
//                    String[] values = keyValue[1].trim().split(",");
//
//                    List<Integer> valueList = new ArrayList<>();
//                    for (String value : values) {
//                        try {
//                            int intValue = Integer.parseInt(value.trim());
//                            valueList.add(intValue);
//                        } catch (NumberFormatException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    programDoctor.put(key, valueList);
//                }
//            }
//        }
//
//        return programDoctor;
//    }
//




        private String convertToString(SortedMap<String, List<Integer>> programDoctor) {
            StringBuilder sb = new StringBuilder();

            for (Map.Entry<String, List<Integer>> entry : programDoctor.entrySet()) {
                String day = entry.getKey();
                List<Integer> interval = entry.getValue();

                sb.append(day).append(" lucreaza intre orele:\n");
                sb.append(interval.get(0)).append("\n");
                sb.append(interval.get(1)).append("\n");
            }

            return sb.toString();
        }
    private SortedMap<String, List<Integer>> convertToProgramDoctor(String programDoctorString) {
        SortedMap<String, List<Integer>> programDoctor = new TreeMap<>();

        if (programDoctorString.isEmpty()) {
            return programDoctor;
        }

        String[] days = {"Luni", "Marti", "Miercuri", "Joi", "Vineri", "Sambata", "Duminica"};
        String[] lines = programDoctorString.split("\n");

        for (int i = 0; i < lines.length; i += 3) {
            if (i + 2 >= lines.length) {
                break;
            }

            String day = days[i / 3];
            int start = parseInteger(lines[i]);
            int end = parseInteger(lines[i + 1]);

            List<Integer> interval = new ArrayList<>();
            interval.add(start);
            interval.add(end);

            programDoctor.put(day, interval);
        }

        return programDoctor;
    }


    private int parseInteger(String value) {
        if (value == null || value.trim().isEmpty() || !value.matches("\\d+")) {
            return 0;
        }

        return Integer.parseInt(value);
    }




    public boolean add(Doctor doctor) {
        try {
            String programDoctorString = convertToString(doctor.getProgramDoctor());

            PreparedStatement statement = conection.prepareStatement("INSERT INTO mysql.DOCTOR (idDoctor, nume, prenume, varsta, sex, nrTelefon, idSpital, salariu, specializare, programDoctor) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            statement.setInt(1, doctor.getIdPersoana());
            statement.setString(2, doctor.getNume());
            statement.setString(3, doctor.getPrenume());
            statement.setInt(4, doctor.getVarsta());
            statement.setInt(5, doctor.getSex());
            statement.setString(6, doctor.getNrTelefon());
            statement.setInt(7, doctor.getIdSpital());
            statement.setInt(8, doctor.getSalariu());
            statement.setString(9, doctor.getSpecializare());
            statement.setString(10, programDoctorString);

            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    public Doctor[] getAll() {
        try {
            Statement statement = conection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM mysql.DOCTOR");

            List<Doctor> doctors = new ArrayList<>();

            while (resultSet.next()) {
                int idPersoana = resultSet.getInt("idDoctor");
                int registrationNo = resultSet.getInt("registrationNo");
                String nume = resultSet.getString("nume");
                String prenume = resultSet.getString("prenume");
                int varsta = resultSet.getInt("varsta");
                int sex = resultSet.getInt("sex");
                String nrTelefon = resultSet.getString("nrTelefon");
                int idSpital = resultSet.getInt("idSpital");
                int salariu = resultSet.getInt("salariu");
                String specializare = resultSet.getString("specializare");
                SortedMap<String, List<Integer>> programDoctor = convertToProgramDoctor(resultSet.getString("programDoctor"));

                Doctor doctor = new Doctor(idPersoana, registrationNo, nume, prenume, varsta, sex, nrTelefon, idSpital, salariu, specializare, programDoctor);
                doctors.add(doctor);
            }

            return doctors.toArray(new Doctor[0]);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Doctor[0];
    }


    public boolean update(Doctor doctor) {
        try {
            PreparedStatement statement = conection.prepareStatement("UPDATE mysql.DOCTOR SET nume = ?, prenume = ?, varsta = ?, sex = ?, nrTelefon = ?, idSpital = ?, salariu = ?, specializare = ?, programDoctor = ? WHERE idDoctor = ?");

            statement.setString(1, doctor.getNume());
            statement.setString(2, doctor.getPrenume());
            statement.setInt(3, doctor.getVarsta());
            statement.setInt(4, doctor.getSex());
            statement.setString(5, doctor.getNrTelefon());
            statement.setInt(6, doctor.getIdSpital());
            statement.setInt(7, doctor.getSalariu());
            statement.setString(8, doctor.getSpecializare());
            statement.setString(9, convertToString(doctor.getProgramDoctor()));
            statement.setInt(10, doctor.getIdPersoana());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int idDoctor) {
        try {
            PreparedStatement statement = conection.prepareStatement("DELETE FROM mysql.DOCTOR WHERE idDoctor = ?");
            statement.setInt(1, idDoctor);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Doctor findById(int idDoctor) {
        try {
            PreparedStatement statement = conection.prepareStatement("SELECT * FROM mysql.DOCTOR WHERE idDoctor = ?");
            statement.setInt(1, idDoctor);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int registrationNo = resultSet.getInt("registrationNo");
                String nume = resultSet.getString("nume");
                String prenume = resultSet.getString("prenume");
                int varsta = resultSet.getInt("varsta");
                int sex = resultSet.getInt("sex");
                String nrTelefon = resultSet.getString("nrTelefon");
                int idSpital = resultSet.getInt("idSpital");
                int salariu = resultSet.getInt("salariu");
                String specializare = resultSet.getString("specializare");
                SortedMap<String, List<Integer>> programDoctor = convertToProgramDoctor(resultSet.getString("programDoctor"));

                return new Doctor(idDoctor, registrationNo, nume, prenume, varsta, sex, nrTelefon, idSpital, salariu, specializare, programDoctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public Doctor[] getAll() {
//        try {
//            Statement statement = conection.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM mysql.DOCTOR"); // Corrected query
//
//            List<Doctor> doctors = new ArrayList<>();
//
//            while (resultSet.next()) {
//                int idPersoana = resultSet.getInt("idDoctor");
//                int registrationNo = resultSet.getInt("registrationNo");
//                String nume = resultSet.getString("nume");
//                String prenume = resultSet.getString("prenume");
//                int varsta = resultSet.getInt("varsta");
//                int sex = resultSet.getInt("sex");
//                String nrTelefon = resultSet.getString("nrTelefon");
//                int idSpital = resultSet.getInt("idSpital");
//                int salariu = resultSet.getInt("salariu");
//                String specializare = resultSet.getString("specializare");
//                SortedMap<String, List<Integer>> programDoctor = convertToProgramDoctor(resultSet.getString("programDoctor"));
//
//                Doctor doctor = new Doctor(idPersoana, registrationNo, nume, prenume, varsta, sex, nrTelefon, idSpital, salariu, specializare, programDoctor);
//                doctors.add(doctor);
//            }
//
//            return doctors.toArray(new Doctor[0]);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return new Doctor[0];
//    }
//
//    private SortedMap<String, List<Integer>> convertToProgramDoctor(String programDoctorString) {
//        SortedMap<String, List<Integer>> programDoctor = new TreeMap<>();
//
//        if (programDoctorString != null && !programDoctorString.isEmpty()) {
//            String[] keyValuePairs = programDoctorString.split("\\s+");
//
//            for (String pair : keyValuePairs) {
//                String[] keyValue = pair.split("\\s+lucreaza\\s+intre\\s+orele:");
//
//                if (keyValue.length == 2) {
//                    String key = keyValue[0].trim();
//                    String[] values = keyValue[1].trim().split("\\s+");
//
//                    List<Integer> valueList = new ArrayList<>();
//                    for (String value : values) {
//                        try {
//                            int intValue = Integer.parseInt(value.trim());
//                            valueList.add(intValue);
//                        } catch (NumberFormatException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    programDoctor.put(key, valueList);
//                }
//            }
//        }
//
//        return programDoctor;
//    }
//

    @Override
    public int getPret(int idConsult) {
        return 0;
    }

    @Override
    public Doctor checkDoctor(String probPacient, String zi, int ora) {
        try {
            PreparedStatement statement = conection.prepareStatement("SELECT * FROM mysql.DOCTOR WHERE specializare = ?");
            statement.setString(1, probPacient);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idPersoana = resultSet.getInt("idDoctor");
                int registrationNo = resultSet.getInt("registrationNo");
                String nume = resultSet.getString("nume");
                String prenume = resultSet.getString("prenume");
                int varsta = resultSet.getInt("varsta");
                int sex = resultSet.getInt("sex");
                String nrTelefon = resultSet.getString("nrTelefon");
                int idSpital = resultSet.getInt("idSpital");
                int salariu = resultSet.getInt("salariu");
                SortedMap<String, List<Integer>> programDoctor = convertToProgramDoctor(resultSet.getString("programDoctor"));

                List<Integer> interval = programDoctor.get(zi);
                if (interval != null && ora >= interval.get(0) && ora <= interval.get(1)) {
                    return new Doctor(idPersoana, registrationNo, nume, prenume, varsta, sex, nrTelefon, idSpital, salariu, probPacient, programDoctor);
                }
            }

            System.out.println("Nu exista doctor disponibil pentru pacient la data respectiva");
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public Doctor[] getSortedDoctors() {
        try {
            Statement statement = conection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery("SELECT * FROM mysql.DOCTOR");

            List<Doctor> doctors = new ArrayList<>();

            while (resultSet.next()) {
                int idPersoana = resultSet.getInt("idDoctor");
                int registrationNo = resultSet.getInt("registrationNo");
                String nume = resultSet.getString("nume");
                String prenume = resultSet.getString("prenume");
                int varsta = resultSet.getInt("varsta");
                int sex = resultSet.getInt("sex");
                String nrTelefon = resultSet.getString("nrTelefon");
                int idSpital = resultSet.getInt("idSpital");
                int salariu = resultSet.getInt("salariu");
                String specializare = resultSet.getString("specializare");
                SortedMap<String, List<Integer>> programDoctor = convertToProgramDoctor(resultSet.getString("programDoctor"));

                Doctor doctor = new Doctor(idPersoana, registrationNo, nume, prenume, varsta, sex, nrTelefon, idSpital, salariu, specializare, programDoctor);
                doctors.add(doctor);
            }

            doctors.sort(Comparator.comparingInt(Doctor::getSalariu).reversed());

            resultSet.beforeFirst();
            for (int i = 0; i < doctors.size(); i++) {
                Doctor doctor = doctors.get(i);
                int newSalariu = doctor.getSalariu();
                int idDoctor = doctor.getIdPersoana();

                resultSet.next();
                resultSet.updateInt("salariu", newSalariu);
                resultSet.updateRow();
            }

            Doctor[] sortedDoctors = doctors.toArray(new Doctor[0]);
            return sortedDoctors;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new Doctor[0];
    }





    @Override
    public Doctor getDoctor() {
        try {
            Statement statement = conection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM mysql.DOCTOR");

            List<Doctor> doctors = new ArrayList<>();

            while (resultSet.next()) {
                int idPersoana = resultSet.getInt("idDoctor");
                int registrationNo = resultSet.getInt("registrationNo");
                String nume = resultSet.getString("nume");
                String prenume = resultSet.getString("prenume");
                int varsta = resultSet.getInt("varsta");
                int sex = resultSet.getInt("sex");
                String nrTelefon = resultSet.getString("nrTelefon");
                int idSpital = resultSet.getInt("idSpital");
                int salariu = resultSet.getInt("salariu");
                String specializare = resultSet.getString("specializare");
                SortedMap<String, List<Integer>> programDoctor = convertToProgramDoctor(resultSet.getString("programDoctor"));

                Doctor doctor = new Doctor(idPersoana, registrationNo, nume, prenume, varsta, sex, nrTelefon, idSpital, salariu, specializare, programDoctor);
                doctors.add(doctor);
            }

            int maxOre = 0;
            int index = -1;

            for (int i = 0; i < doctors.size(); i++) {
                int ore = 0;
                SortedMap<String, List<Integer>> programDoctor = doctors.get(i).getProgramDoctor();

                for (List<Integer> interval : programDoctor.values()) {
                    ore += interval.get(1) - interval.get(0);
                }

                if (ore > maxOre) {
                    maxOre = ore;
                    index = i;
                }
            }

            if (index != -1) {
                Doctor doctor = doctors.get(index);
                double salariu = doctor.getSalariu() + (0.15 * doctor.getSalariu());
                doctor.setSalariu((int) salariu);

                // Update the doctor's salary in the database
                PreparedStatement updateStatement = conection.prepareStatement("UPDATE mysql.DOCTOR SET salariu = ? WHERE idDoctor = ?");
                updateStatement.setInt(1, (int) salariu);
                updateStatement.setInt(2, doctor.getIdPersoana());
                updateStatement.executeUpdate();

                return doctor;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
