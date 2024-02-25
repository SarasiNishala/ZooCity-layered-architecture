package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.AnimalBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.AnimalDAO;
import lk.ijse.dto.AnimalDto;
import lk.ijse.entity.Animal;

import java.sql.SQLException;
import java.util.ArrayList;

public class AnimalBOImpl implements AnimalBO {
    AnimalDAO animalDAO = (AnimalDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ANIMAL);
    @Override
    public ArrayList<AnimalDto> getAllAnimals() throws SQLException, ClassNotFoundException {
        ArrayList<Animal> animals = animalDAO.getAll();
        ArrayList<AnimalDto> animalDTOS = new ArrayList<>();

        for (Animal animal :animals ){
            animalDTOS.add(new AnimalDto( animal.getAnimalTg(),animal.getCategory(),animal.getAnimalType(),animal.getCageId(),animal.getAdminId()));
        }
        return animalDTOS;
    }

    @Override
    public boolean saveAnimal(AnimalDto dto) throws SQLException, ClassNotFoundException {
        return animalDAO.save(new Animal(dto.getAnimalTg(),dto.getCategory(),dto.getAnimalType(),dto.getCageId(),dto.getAdminId()));
    }

    @Override
    public boolean updateAnimal(AnimalDto dto) throws SQLException, ClassNotFoundException {
        return animalDAO.update(new Animal(dto.getAnimalTg(),dto.getCategory(),dto.getAnimalType(),dto.getCageId(),dto.getAdminId()));
    }

    @Override
    public boolean deleteAnimal(String id) throws SQLException, ClassNotFoundException {
        return animalDAO.delete(id);
    }

    @Override
    public boolean existAnimal(String id) throws SQLException, ClassNotFoundException {
        return animalDAO.exist(id);
    }

    @Override
    public String generateNewAnimalId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public AnimalDto searchAnimal(String newValue) throws SQLException, ClassNotFoundException {
        Animal animal = animalDAO.search(newValue);
        AnimalDto animalDto = new AnimalDto(animal.getAnimalTg(),animal.getCategory(),animal.getAnimalType(),animal.getCageId(),animal.getAdminId());
        return animalDto;
    }
}
