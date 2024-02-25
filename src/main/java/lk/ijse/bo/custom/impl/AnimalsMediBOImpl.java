package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.AnimalsFoodBO;
import lk.ijse.bo.custom.AnimalsMediBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.AnimalsFoodDAO;
import lk.ijse.dao.custom.AnimalsMedicineDAO;
import lk.ijse.dto.AnimalsFoodDto;
import lk.ijse.dto.AnimalsMediDto;
import lk.ijse.entity.AnimalsFood;
import lk.ijse.entity.AnimalsMedi;

import java.sql.SQLException;

public class AnimalsMediBOImpl implements AnimalsMediBO {
    AnimalsMedicineDAO animalsMedicineDAO = (AnimalsMedicineDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ANIMALSMEDICINE);
    @Override
    public boolean saveAnimalsMedi(AnimalsMediDto dto) throws SQLException, ClassNotFoundException {
        return animalsMedicineDAO.save(new AnimalsMedi(dto.getAnimalTg(),dto.getMediId(),dto.getDate(),dto.getTime(),dto.getQty()));
    }

    @Override
    public boolean updateAnimalsMedi(AnimalsMediDto dto) throws SQLException, ClassNotFoundException {
        return animalsMedicineDAO.update(new AnimalsMedi(dto.getAnimalTg(),dto.getMediId(),dto.getDate(),dto.getTime(),dto.getQty()));
    }

    @Override
    public boolean deleteAnimalsMedi(String id) throws SQLException, ClassNotFoundException {
        return animalsMedicineDAO.delete(id);
    }

    @Override
    public boolean existAnimalsMedi(String id) throws SQLException, ClassNotFoundException {
        return animalsMedicineDAO.exist(id);
    }

    @Override
    public AnimalsMediDto searchAnimalsMedi(String newValue) throws SQLException, ClassNotFoundException {
        AnimalsMedi animalsMedi = animalsMedicineDAO.search(newValue);
        AnimalsMediDto animalsMediDto = new AnimalsMediDto(animalsMedi.getAnimalTg(),animalsMedi.getMediId(),animalsMedi.getDate(),animalsMedi.getTime(),animalsMedi.getQty());
        return animalsMediDto;
    }
}
