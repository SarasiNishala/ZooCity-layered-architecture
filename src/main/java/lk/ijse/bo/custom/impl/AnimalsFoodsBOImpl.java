package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.AnimalsFoodBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.AnimalsFoodDAO;
import lk.ijse.dto.AnimalsFoodDto;
import lk.ijse.entity.AnimalsFood;

import java.sql.SQLException;

public class AnimalsFoodsBOImpl implements AnimalsFoodBO {
    AnimalsFoodDAO animalsFoodDAO = (AnimalsFoodDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ANIMALSFOOD);

    @Override
    public boolean saveAnimalsFood(AnimalsFoodDto dto) throws SQLException, ClassNotFoundException {
        return animalsFoodDAO.save(new AnimalsFood(dto.getAnimalTg(),dto.getFoodId(),dto.getDate(),dto.getTime(),dto.getQty()));
    }

    @Override
    public boolean updateAnimalsFood(AnimalsFoodDto dto) throws SQLException, ClassNotFoundException {
        return animalsFoodDAO.update(new AnimalsFood(dto.getAnimalTg(),dto.getFoodId(),dto.getDate(),dto.getTime(),dto.getQty()));

    }

    @Override
    public boolean deleteAnimalsFood(String id) throws SQLException, ClassNotFoundException {
        return animalsFoodDAO.delete(id);
    }

    @Override
    public boolean existAnimalsFood(String id) throws SQLException, ClassNotFoundException {
        return animalsFoodDAO.exist(id);
    }

    @Override
    public AnimalsFoodDto searchAnimalsFood(String newValue) throws SQLException, ClassNotFoundException {
        AnimalsFood animalsFood = animalsFoodDAO.search(newValue);
        AnimalsFoodDto animalsFoodDto = new AnimalsFoodDto(animalsFood.getAnimalTg(),animalsFood.getFoodId(),animalsFood.getDate(),animalsFood.getTime(),animalsFood.getQty());
        return animalsFoodDto;
    }
}
