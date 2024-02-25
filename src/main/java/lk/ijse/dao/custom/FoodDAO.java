package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Food;

import java.sql.SQLException;

public interface FoodDAO extends CrudDAO<Food> {
    boolean updateStock(String foodId, int qty) throws SQLException;
}
