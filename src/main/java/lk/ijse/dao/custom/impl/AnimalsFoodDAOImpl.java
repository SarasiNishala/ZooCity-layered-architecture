package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.AnimalsFoodDAO;
import lk.ijse.entity.AnimalsFood;
import lk.ijse.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AnimalsFoodDAOImpl implements AnimalsFoodDAO {
    @Override
    public ArrayList<AnimalsFood> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(AnimalsFood entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "INSERT INTO AnimalFoods VALUES(?, ?, ?, ?, ?)",
                entity.getAnimalTg(),
                entity.getFoodId(),
                entity.getDate(),
                entity.getTime(),
                entity.getQty());
    }

    @Override
    public boolean update(AnimalsFood entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "UPDATE AnimalFood SET FoodId = ?, Date = ?, Time = ?, Qty = ? WHERE AnimalTg = ?",
                entity.getAnimalTg(),
                entity.getFoodId(),
                entity.getDate(),
                entity.getTime(),
                entity.getQty());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM AnimalFood WHERE AnimalTg = ?",id);

    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM AnimalFood WHERE AnimalTg = ?" , id);
        return resultSet.next();
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public AnimalsFood search(String newValue) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM AnimalFood WHERE AnimalTg=?", newValue);

        AnimalsFood entity = null;
        if(rst.next()) {
            entity = new AnimalsFood(
                    newValue + "",
                    rst.getString(1),
                    rst.getDate(2),
                    rst.getTime(3),
                    rst.getInt(4));
        }

        return entity;
    }
}
