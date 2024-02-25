package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.AnimalDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AnimalBO extends SuperBO {
    ArrayList<AnimalDto> getAllAnimals() throws SQLException, ClassNotFoundException;
    boolean saveAnimal(AnimalDto dto) throws SQLException, ClassNotFoundException;
    boolean updateAnimal(AnimalDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteAnimal(String id) throws SQLException, ClassNotFoundException;
    boolean existAnimal(String id) throws SQLException, ClassNotFoundException;
    String generateNewAnimalId() throws SQLException, ClassNotFoundException;
    AnimalDto searchAnimal(String newValue) throws SQLException, ClassNotFoundException;
}
