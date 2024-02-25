package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.AnimalDAO;
import lk.ijse.entity.Animal;
import lk.ijse.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AnimalDAOImpl implements AnimalDAO {
    @Override
    public ArrayList<Animal> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Animals");

        ArrayList<Animal> allAnimals = new ArrayList<>();
        while (resultSet.next()) {
            Animal entity = new Animal(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
            allAnimals.add(entity);
        }
        return allAnimals;
    }

    @Override
    public boolean save(Animal entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Animals VALUES(?, ?, ?, ?, ?)",
            entity.getAnimalTg(),
            entity.getCategory(),
            entity.getAnimalType(),
            entity.getCageId(),
            entity.getAdminId());
    }

    @Override
    public boolean update(Animal entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "UPDATE Animals SET Category = ?, AnimalType = ?, CageId = ?, AdminId = ? WHERE AnimalTg = ?",
            entity.getCategory(),
            entity.getAnimalType(),
            entity.getCageId(),
            entity.getAdminId(),
            entity.getAnimalTg());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Animals WHERE AnimalTg = ?",id);
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Animals WHERE AnimalTg = ?" , id);
        return resultSet.next();
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Animal search(String newValue) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Animals WHERE AnimalTg=?", newValue);

        Animal entity = null;
        if(rst.next()) {
            entity = new Animal(
                    newValue + "",
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4));
        }

        return entity;
    }
}
