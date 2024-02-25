package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.AnimalsMedicineDAO;
import lk.ijse.entity.AnimalsFood;
import lk.ijse.entity.AnimalsMedi;
import lk.ijse.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AnimalsMedicineDAOImpl implements AnimalsMedicineDAO {
    @Override
    public ArrayList<AnimalsMedi> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(AnimalsMedi entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "INSERT INTO AnimalMedicine VALUES(?, ?, ?, ?, ?)",
                entity.getAnimalTg(),
                entity.getMediId(),
                entity.getDate(),
                entity.getTime(),
                entity.getQty());
    }

    @Override
    public boolean update(AnimalsMedi entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "UPDATE AnimalMedicine SET MediId = ?, Date = ?, Time = ?, Qty = ? WHERE AnimalTg = ?",
                entity.getAnimalTg(),
                entity.getMediId(),
                entity.getDate(),
                entity.getTime(),
                entity.getQty());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM AnimalMedicine WHERE AnimalTg = ?");
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM AnimalMedicine WHERE AnimalTg = ?" ,id);
        return resultSet.next();
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public AnimalsMedi search(String newValue) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM AnimalMedicine WHERE AnimalTg=?", newValue);

        AnimalsMedi entity = null;
        if(rst.next()) {
            entity = new AnimalsMedi(
                    newValue + "",
                    rst.getString(1),
                    rst.getDate(2),
                    rst.getTime(3),
                    rst.getInt(4));
        }

        return entity;
    }
}
