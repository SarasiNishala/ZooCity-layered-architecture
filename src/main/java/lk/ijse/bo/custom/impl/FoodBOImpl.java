package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.FoodBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.FoodDAO;
import lk.ijse.dto.FoodDto;
import lk.ijse.entity.Food;

import java.sql.SQLException;
import java.util.ArrayList;

public class FoodBOImpl implements FoodBO {
    FoodDAO foodDAO = (FoodDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.FOOD);

    @Override
    public ArrayList<FoodDto> getAllAnimalFood() throws SQLException, ClassNotFoundException {
        ArrayList<Food> foods = foodDAO.getAll();
        ArrayList<FoodDto> foodDtos = new ArrayList<>();

        for (Food food :foods ){
            foodDtos.add(new FoodDto(food.getFoodId(),food.getName(),food.getPrice(),food.getQty()));
        }
        return foodDtos;
    }

    @Override
    public boolean saveFood(FoodDto dto) throws SQLException, ClassNotFoundException {
        return foodDAO.save(new Food(dto.getFoodId(),dto.getName(),dto.getPrice(),dto.getQty()));
    }

    @Override
    public boolean updateFood(FoodDto dto) throws SQLException, ClassNotFoundException {
        return foodDAO.update(new Food(dto.getFoodId(),dto.getName(),dto.getPrice(),dto.getQty()));
    }

    @Override
    public boolean deleteFood(String id) throws SQLException, ClassNotFoundException {
        return foodDAO.delete(id);
    }

    @Override
    public boolean existFood(String id) throws SQLException, ClassNotFoundException {
        return foodDAO.exist(id);
    }

    @Override
    public String generateNewFoodId() throws SQLException, ClassNotFoundException {
        return foodDAO.generateNewId();
    }

    @Override
    public FoodDto searchFood(String newValue) throws SQLException, ClassNotFoundException {
        Food food = foodDAO.search(newValue);
        FoodDto foodDto = new FoodDto(food.getFoodId(),food.getName(),food.getPrice(),food.getQty());
        return foodDto;
    }

    @Override
    public boolean updateStock(String foodId, int qty) throws SQLException {
        return false;
    }
}
