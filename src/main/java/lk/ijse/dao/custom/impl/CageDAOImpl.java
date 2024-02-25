package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.CageDAO;
import lk.ijse.entity.Cage;
import lk.ijse.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CageDAOImpl implements CageDAO {
    @Override
    public ArrayList<Cage> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Cages");

        ArrayList<Cage> allCages = new ArrayList<>();
        while (resultSet.next()) {
            Cage entity = new Cage(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3)
            );
            allCages.add(entity);
        }
        return allCages;
    }

    @Override
    public boolean save(Cage entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "INSERT INTO Cages VALUES(?, ?, ?)",
                entity.getCageId(),
                entity.getType(),
                entity.getNoOfANimals());
    }

    @Override
    public boolean update(Cage dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "DELETE FROM Cages WHERE CageId = ?",id);
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Cages WHERE CageId = ?" , id);
        return resultSet.next();
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT CageId FROM Cages ORDER BY CageId DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("CageId");
            int newCageId = Integer.parseInt(id.replace("C00", "")) + 1;
            return String.format("C00", newCageId);
        } else {
            return "C001";
        }
    }

    @Override
    public Cage search(String newValue) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Cages WHERE CageId=?", newValue);

        Cage entity = null;
        if(rst.next()) {
            entity = new Cage(
                    newValue + "",
                    rst.getString(1),
                    rst.getInt(2));
        }
        return entity;
    }
}
