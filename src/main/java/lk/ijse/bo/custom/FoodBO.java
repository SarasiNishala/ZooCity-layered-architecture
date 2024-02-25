package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.FoodDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FoodBO extends SuperBO {
    ArrayList<FoodDto> getAllAnimalFood() throws SQLException, ClassNotFoundException;
    boolean saveFood(FoodDto dto) throws SQLException, ClassNotFoundException;
    boolean updateFood(FoodDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteFood(String id) throws SQLException, ClassNotFoundException;
    boolean existFood(String id) throws SQLException, ClassNotFoundException;
    String generateNewFoodId() throws SQLException, ClassNotFoundException;
    FoodDto searchFood(String newValue) throws SQLException, ClassNotFoundException;
    boolean updateStock(String foodId, int qty) throws SQLException;
}
