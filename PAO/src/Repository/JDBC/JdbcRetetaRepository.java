package Repository.JDBC;

import Model.Doctor;
import Model.Reteta;
import Repository.CRUDRepository;
import config.DatabaseConfiguration;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JdbcRetetaRepository implements CRUDRepository<Reteta> {

    private Connection conection;

    public JdbcRetetaRepository(DatabaseConfiguration conection) {
        this.conection = conection.getConnection();
    }

    public boolean add(Reteta reteta) {
        try {
            String listaMedicamenteString = convertToString(reteta.getListaMedicamente());

            PreparedStatement statement = conection.prepareStatement("INSERT INTO mysql.RETETA (idReteta,registrationNo, idConsult, idPacient, nrMedicamente, listaMedicamente) VALUES (?, ?, ?, ?, ?, ?)");

            statement.setInt(1, reteta.getIdReteta());
            statement.setInt(2, reteta.getRegistrationNo());
            statement.setInt(3, reteta.getIdConsult());
            statement.setInt(4, reteta.getIdPacient());
            statement.setInt(5, reteta.getNrMedicamente());
            statement.setString(6, listaMedicamenteString);

            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Reteta[] getAll() {
        try {
            Statement statement = conection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM mysql.RETETA");
            List<Reteta> retete = new ArrayList<>();
            while (resultSet.next()) {
                int idReteta = resultSet.getInt("idReteta");
                int registrationNo = resultSet.getInt("registrationNo");
                int idConsult = resultSet.getInt("idConsult");
                int idPacient = resultSet.getInt("idPacient");
                int nrMedicamente = resultSet.getInt("nrMedicamente");
                String listaMedicamenteString = resultSet.getString("listaMedicamente");
                List<String> listaMedicamente = convertToList(listaMedicamenteString);

                Reteta reteta = new Reteta( idReteta,registrationNo, idConsult, idPacient, nrMedicamente, new ArrayList<>(listaMedicamente));
                retete.add(reteta);
            }
            return retete.toArray(new Reteta[0]);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Reteta[0];
    }


    private String convertToString(List<String> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String item : list) {
            stringBuilder.append(item).append(",");
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.setLength(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

    private List<String> convertToList(String string) {
        List<String> list = new ArrayList<>();
        String[] items = string.split(",");
        for (String item : items) {
            list.add(item);
        }
        return list;
    }

    public boolean delete(int idReteta) {
        try {
            PreparedStatement statement = conection.prepareStatement("DELETE FROM mysql.RETETA WHERE idReteta = ?");
            statement.setInt(1, idReteta);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Reteta reteta) {
        try {
            PreparedStatement statement = conection.prepareStatement("UPDATE mysql.RETETA SET idConsult = ?, idPacient = ?, nrMedicamente = ?, listaMedicamente = ? WHERE idReteta = ?");

            statement.setInt(1, reteta.getIdConsult());
            statement.setInt(2, reteta.getIdPacient());
            statement.setInt(3, reteta.getNrMedicamente());
            statement.setString(4, convertToString(reteta.getListaMedicamente()));
            statement.setInt(5, reteta.getIdReteta());

            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Reteta findById(int idReteta) {
        try {
            PreparedStatement statement = conection.prepareStatement("SELECT * FROM mysql.RETETA WHERE idReteta = ?");
            statement.setInt(1, idReteta);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int registrationNo = resultSet.getInt("registrationNo");
                int idConsult = resultSet.getInt("idConsult");
                int idPacient = resultSet.getInt("idPacient");
                int nrMedicamente = resultSet.getInt("nrMedicamente");
                String listaMedicamenteString = resultSet.getString("listaMedicamente");
                List<String> listaMedicamente = convertToList(listaMedicamenteString);

                return new Reteta(registrationNo, idReteta, idConsult, idPacient, nrMedicamente, (ArrayList<String>) listaMedicamente);
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
        try {
            PreparedStatement selectStatement = conection.prepareStatement("SELECT * FROM mysql.RETETA WHERE idPacient = ?");
            selectStatement.setInt(1, idPacient);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                int retetaId = resultSet.getInt("idReteta");
                ArrayList<String> listaMedicamente = new ArrayList<>(Arrays.asList(resultSet.getString("listaMedicamente").split(",")));

                int ok = 0;
                for (int i = 0; i < listaMedicamente.size(); i++) {
                    String medicament = listaMedicamente.get(i);
                    if (listaAlergii.contains(medicament)) {
                        listaMedicamente.remove(i);
                        i--;
                        ok = 1;
                    }
                }

                if (ok == 1) {
                    System.out.println("S-au gasit medicamente in reteta pacientului la care avea alergie si s-au eliminat:");
                } else {
                    System.out.println("Nu s-au gasit medicamente in reteta pacientului la care are alergie:");
                }

                String updatedListaMedicamente = String.join(",", listaMedicamente);

                PreparedStatement updateStatement = conection.prepareStatement("UPDATE mysql.RETETA SET listaMedicamente = ? WHERE idReteta = ?");
                updateStatement.setString(1, updatedListaMedicamente);
                updateStatement.setInt(2, retetaId);
                updateStatement.executeUpdate();

                return new Reteta(retetaId, idPacient, listaMedicamente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Nu s-a gasit reteta pentru pacientul respectiv:");
        return null;
    }


    @Override
    public int getIdConsult(int idPacient) {
        try {
            PreparedStatement selectStatement = conection.prepareStatement("SELECT idConsult FROM mysql.RETETA WHERE idPacient = ?");
            selectStatement.setInt(1, idPacient);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("idConsult");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

}
