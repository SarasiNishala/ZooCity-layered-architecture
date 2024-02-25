package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.AnimalsMediDto;

import java.sql.SQLException;

public interface AnimalsMediBO extends SuperBO {
    boolean saveAnimalsMedi(AnimalsMediDto dto) throws SQLException, ClassNotFoundException;
    boolean updateAnimalsMedi(AnimalsMediDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteAnimalsMedi(String id) throws SQLException, ClassNotFoundException;
    boolean existAnimalsMedi(String id) throws SQLException, ClassNotFoundException;
    AnimalsMediDto searchAnimalsMedi(String newValue) throws SQLException, ClassNotFoundException;

}
