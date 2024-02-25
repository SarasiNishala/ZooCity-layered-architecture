package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.FoodDAO;
import lk.ijse.entity.Animal;
import lk.ijse.entity.Food;
import lk.ijse.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FoodDAOImpl implements FoodDAO {
    @Override
    public ArrayList<Food> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Food");

        ArrayList<Food> allAnimalFood = new ArrayList<>();
        while (resultSet.next()) {
            Food entity = new Food(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4)
            );
            allAnimalFood.add(entity);
        }
        return allAnimalFood;
    }

    @Override
    public boolean save(Food entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Food VALUES(?, ?, ?, ?)",
                entity.getFoodId(),
                entity.getName(),
                entity.getPrice(),
                entity.getQty());
    }

    @Override
    public boolean update(Food entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Food SET Name = ?, Price = ?, QTY = ? WHERE FoodId = ?",
                entity.getFoodId(),
                entity.getName(),
                entity.getPrice(),
                entity.getQty());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "DELETE FROM Food WHERE FoodId = ?",id);
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Food WHERE FoodId = ?" , id);
        return resultSet.next();
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT FoodId FROM Food ORDER BY FoodId DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("FoodId");
            int newFoodId = Integer.parseInt(id.replace("F00", "")) + 1;
            return String.format("F00", newFoodId);
        } else {
            return "F001";
        }
    }

    @Override
    public Food search(String newValue) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Food WHERE FoodId=?", newValue);

        Food entity = null;
        if(rst.next()) {
            entity = new Food(
                    newValue + "",
                    rst.getString(1),
                    rst.getDouble(2),
                    rst.getInt(3));
        }

        return entity;
    }

    @Override
    public boolean updateStock(String foodId, int qty) throws SQLException {
        return false;
    }
}
