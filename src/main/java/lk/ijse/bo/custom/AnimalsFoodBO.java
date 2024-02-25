package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.AnimalsFoodDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AnimalsFoodBO extends SuperBO {
    boolean saveAnimalsFood(AnimalsFoodDto dto) throws SQLException, ClassNotFoundException;
    boolean updateAnimalsFood(AnimalsFoodDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteAnimalsFood(String id) throws SQLException, ClassNotFoundException;
    boolean existAnimalsFood(String id) throws SQLException, ClassNotFoundException;
    AnimalsFoodDto searchAnimalsFood(String newValue) throws SQLException, ClassNotFoundException;
}
