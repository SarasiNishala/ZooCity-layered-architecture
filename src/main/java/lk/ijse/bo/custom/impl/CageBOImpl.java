package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.CageBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CageDAO;
import lk.ijse.dto.CageDto;
import lk.ijse.entity.Cage;

import java.sql.SQLException;
import java.util.ArrayList;

public class CageBOImpl implements CageBO {
    CageDAO cageDAO = (CageDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CAGE);
    @Override
    public ArrayList<CageDto> getAllCages() throws SQLException, ClassNotFoundException {
        ArrayList<Cage> cages = cageDAO.getAll();
        ArrayList<CageDto> cageDTOS = new ArrayList<>();

        for (Cage cage :cages ){
            cageDTOS.add(new CageDto( cage.getCageId(),cage.getType(),cage.getNoOfANimals()));
        }
        return cageDTOS;
    }

    @Override
    public boolean saveCage(CageDto dto) throws SQLException, ClassNotFoundException {
        return cageDAO.save(new Cage(dto.getCageId(),dto.getType(),dto.getNoOfANimals()));
    }

    @Override
    public boolean updateCage(CageDto dto) throws SQLException, ClassNotFoundException {
        return cageDAO.update(new Cage(dto.getCageId(),dto.getType(),dto.getNoOfANimals()));
    }

    @Override
    public boolean deleteCage(String id) throws SQLException, ClassNotFoundException {
        return cageDAO.delete(id);
    }

    @Override
    public boolean existCage(String id) throws SQLException, ClassNotFoundException {
        return cageDAO.exist(id);
    }

    @Override
    public String generateNewCageId() throws SQLException, ClassNotFoundException {
        return cageDAO.generateNewId();
    }

    @Override
    public CageDto searchCage(String newValue) throws SQLException, ClassNotFoundException {
        Cage cage = cageDAO.search(newValue);
        CageDto cageDto = new CageDto(cage.getCageId(),cage.getType(),cage.getNoOfANimals());
        return cageDto;
    }
}
