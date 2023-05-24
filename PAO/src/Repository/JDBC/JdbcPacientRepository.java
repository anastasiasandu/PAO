package Repository.JDBC;

import Model.Doctor;
import Model.Pacient;
import Model.Reteta;
import Repository.CRUDRepository;
import config.DatabaseConfiguration;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class JdbcPacientRepository implements CRUDRepository<Pacient> {

    private Connection conection;

    public JdbcPacientRepository(DatabaseConfiguration conection) {
        this.conection=conection.getConnection();
    }


    private String convertToString(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String item : list) {
            sb.append(item).append(",");
        }
        if (!list.isEmpty()) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public boolean add(Pacient pacient) {
        try {
            String listaAlergiiString = convertToString(pacient.getListaAlergii());

            PreparedStatement statement = conection.prepareStatement("INSERT INTO mysql.PACIENT (idPacient, nume, prenume, varsta, sex, nrTelefon, idSpital, tipProblema, nrAlergii, listaAlergii) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            statement.setInt(1, pacient.getIdPersoana());
            statement.setString(2, pacient.getNume());
            statement.setString(3, pacient.getPrenume());
            statement.setInt(4, pacient.getVarsta());
            statement.setInt(5, pacient.getSex());
            statement.setString(6, pacient.getNrTelefon());
            statement.setInt(7, pacient.getIdSpital());
            statement.setString(8, pacient.getTipProblema());
            statement.setInt(9, pacient.getNrAlergii());
            statement.setString(10, listaAlergiiString);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Pacient[] getAll() {
        try {
            Statement statement = conection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM mysql.PACIENT");
            List<Pacient> pacients = new ArrayList<>();
            while (resultSet.next()) {
                int idPersoana = resultSet.getInt("idPacient");
                int registrationNo = resultSet.getInt("registrationNo");
                String nume = resultSet.getString("nume");
                String prenume = resultSet.getString("prenume");
                int varsta = resultSet.getInt("varsta");
                int sex = resultSet.getInt("sex");
                String nrTelefon = resultSet.getString("nrTelefon");
                int idSpital = resultSet.getInt("idSpital");
                String tipProblema = resultSet.getString("tipProblema");
                int nrAlergii = resultSet.getInt("nrAlergii");
                String listaAlergiiString = resultSet.getString("listaAlergii");
                List<String> listaAlergiiList = convertToList(listaAlergiiString);
                Pacient pacient = new Pacient(idPersoana, registrationNo, nume, prenume, varsta, sex, nrTelefon, idSpital, tipProblema, nrAlergii, new ArrayList<>(listaAlergiiList));

                pacients.add(pacient);
            }
            return pacients.toArray(new Pacient[0]);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Pacient[0];
    }

    private List<String> convertToList(String str) {
        List<String> list = new ArrayList<>();
        if (str != null && !str.isEmpty()) {
            String[] items = str.split(",");
            list.addAll(Arrays.asList(items));
        }
        return list;
    }

    public boolean update(Pacient entity) {
        try {
            String query = "UPDATE mysql.PACIENT SET nume = ?, prenume = ?, varsta = ?, sex = ?, nrTelefon = ?, idSpital = ?, tipProblema = ?, nrAlergii = ?, listaAlergii = ? WHERE idPacient = ?";
            PreparedStatement preparedStatement = this.conection.prepareStatement(query);
            preparedStatement.setString(1, entity.getNume());
            preparedStatement.setString(2, entity.getPrenume());
            preparedStatement.setInt(3, entity.getVarsta());
            preparedStatement.setInt(4, entity.getSex());
            preparedStatement.setString(5, entity.getNrTelefon());
            preparedStatement.setInt(6, entity.getIdSpital());
            preparedStatement.setString(7, entity.getTipProblema());
            preparedStatement.setInt(8, entity.getNrAlergii());
            preparedStatement.setString(9, convertToString(entity.getListaAlergii()));
            preparedStatement.setInt(10, entity.getIdPersoana());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return false;
    }




    @Override
    public Pacient findById(int id) {
        try {
            String query = "SELECT * FROM mysql.PACIENT WHERE idPacient = ?";
            PreparedStatement preparedStatement = this.conection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int idPersoana = resultSet.getInt("idPacient");
                int registrationNo = resultSet.getInt("registrationNo");
                String nume = resultSet.getString("nume");
                String prenume = resultSet.getString("prenume");
                int varsta = resultSet.getInt("varsta");
                int sex = resultSet.getInt("sex");
                String nrTelefon = resultSet.getString("nrTelefon");
                int idSpital = resultSet.getInt("idSpital");
                String tipProblema = resultSet.getString("tipProblema");
                int nrAlergii = resultSet.getInt("nrAlergii");
                String listaAlergiiString = resultSet.getString("listaAlergii");
                List<String> listaAlergiiList = convertToList(listaAlergiiString);
                return new Pacient(idPersoana,registrationNo, nume, prenume, varsta, sex, nrTelefon, idSpital, tipProblema, nrAlergii, new ArrayList<>(listaAlergiiList));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getPret(int idConsult) {
        return 0;
    }

    @Override
    public boolean delete(int idPersoana) {
        try {
            String query = "DELETE FROM mysql.PACIENT WHERE idPacient = ?";
            PreparedStatement preparedStatement = this.conection.prepareStatement(query);
            preparedStatement.setInt(1, idPersoana);
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public String getProbPacient(int idPacient) {
        try {
            PreparedStatement statement = conection.prepareStatement("SELECT tipProblema FROM mysql.PACIENT WHERE idPacient = ?");
            statement.setInt(1, idPacient);

            ResultSet resultSet = statement.executeQuery();

            String tipProblema = null;
            if (resultSet.next()) {
                tipProblema = resultSet.getString("tipProblema");
            }

            return tipProblema;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
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
    public ArrayList<String> getLista(int idPacient) {
        ArrayList<String> listaAlergii = new ArrayList<>();

        try {
            PreparedStatement statement = conection.prepareStatement("SELECT listaAlergii FROM mysql.PACIENT WHERE idPacient = ?");
            statement.setInt(1, idPacient);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String listaAlergiiString = resultSet.getString("listaAlergii");

                if (listaAlergiiString != null && !listaAlergiiString.isEmpty()) {
                    String[] alergiiArray = listaAlergiiString.split(",");
                    listaAlergii = new ArrayList<>(Arrays.asList(alergiiArray));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
