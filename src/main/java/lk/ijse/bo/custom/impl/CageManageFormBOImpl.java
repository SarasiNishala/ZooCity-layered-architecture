package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.CageManageBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.AnimalDAO;
import lk.ijse.dao.custom.CageManageFormDAO;
import lk.ijse.dto.CageManageFormDto;
import lk.ijse.entity.CageManageForm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CageManageFormBOImpl implements CageManageBO {
    CageManageFormDAO cageManageFormDAO = (CageManageFormDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CAGEMANAGEFORM);
    AnimalDAO animalDAO = (AnimalDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ANIMAL);

    @Override
    public ArrayList<CageManageFormDto> getAllCageManageForm() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveCageManageForm(CageManageFormDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateCageManageForm(CageManageFormDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteCageManageForm(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean existCageManageForm(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewCageManageFoemId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public CageManageForm searchCageManageForm(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<CageManageFormDto> getAllEmployee() throws SQLException {
        return null;
    }

    @Override
    public List<CageManageFormDto> getAllCages() throws SQLException {
        return null;
    }
}
