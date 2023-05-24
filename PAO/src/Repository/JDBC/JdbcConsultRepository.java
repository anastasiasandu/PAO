package Repository.JDBC;

import Model.Consult;
import Model.Doctor;
import Model.Pacient;
import Model.Reteta;
import Repository.CRUDRepository;
import config.DatabaseConfiguration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcConsultRepository implements CRUDRepository<Consult> {

    private Connection conection;

    public JdbcConsultRepository(DatabaseConfiguration conection) {
        this.conection = conection.getConnection();
    }

//    public boolean add(Consult consult) {
//        try {
//            PreparedStatement statement = conection.prepareStatement("INSERT INTO mysql.CONSULT (idConsult, pret, dataConsult) VALUES (?, ?, ?)");
//
//            statement.setInt(1, consult.getIdConsult());
//            statement.setInt(3, consult.getPret());
//            statement.setString(4, consult.getDataConsult());
//
//            statement.executeUpdate();
//            return true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }

    public boolean add(Consult consult) {
        try {
            PreparedStatement statement = conection.prepareStatement("INSERT INTO mysql.CONSULT (idConsult, pret, dataConsult) VALUES (?, ?, ?)");
            statement.setInt(1, consult.getIdConsult());
            statement.setInt(2, consult.getPret());
            statement.setString(3, consult.getDataConsult());

            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int idConsult) {
        try {
            PreparedStatement statement = conection.prepareStatement("DELETE FROM mysql.CONSULT WHERE idConsult = ?");
            statement.setInt(1, idConsult);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Consult consult) {
        try {
            PreparedStatement statement = conection.prepareStatement("UPDATE mysql.CONSULT SET pret = ?, dataConsult = ? WHERE idConsult = ?");

            statement.setInt(1, consult.getPret());
            statement.setString(2, consult.getDataConsult());
            statement.setInt(3, consult.getIdConsult());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Consult findById(int idConsult) {
        try {
            PreparedStatement statement = conection.prepareStatement("SELECT * FROM mysql.CONSULT WHERE idConsult = ?");
            statement.setInt(1, idConsult);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int registrationNo = resultSet.getInt("registrationNo");
                int pret = resultSet.getInt("pret");
                String dataConsult = resultSet.getString("dataConsult");

                return new Consult(registrationNo, idConsult, pret, dataConsult);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Consult[] getAll() {
        try {
            Statement statement = conection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM mysql.CONSULT");

            List<Consult> consults = new ArrayList<>();
            while (resultSet.next()) {
                int idConsult = resultSet.getInt("idConsult");
                int registrationNo = resultSet.getInt("registrationNo");
                int pret = resultSet.getInt("pret");
                String dataConsult = resultSet.getString("dataConsult");

                Consult consult = new Consult(idConsult,registrationNo, pret, dataConsult);
                consults.add(consult);
            }

            return consults.toArray(new Consult[0]);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Consult[0];
    }

    @Override
    public int getPret(int idConsult) {
        try {
            PreparedStatement selectStatement = conection.prepareStatement("SELECT pret FROM mysql.CONSULT WHERE idConsult = ?");
            selectStatement.setInt(1, idConsult);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("pret");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Nu s-a gasit consult cu id-ul respectiv");
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

