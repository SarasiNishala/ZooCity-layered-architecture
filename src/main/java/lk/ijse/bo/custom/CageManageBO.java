package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.CageManageFormDto;
import lk.ijse.entity.CageManageForm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CageManageBO extends SuperBO {
    ArrayList<CageManageFormDto> getAllCageManageForm() throws SQLException, ClassNotFoundException;
    boolean saveCageManageForm(CageManageFormDto dto) throws SQLException, ClassNotFoundException;
    boolean updateCageManageForm(CageManageFormDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteCageManageForm(String id) throws SQLException, ClassNotFoundException;
    boolean existCageManageForm(String id) throws SQLException, ClassNotFoundException;
    String generateNewCageManageFoemId() throws SQLException, ClassNotFoundException;
    CageManageForm searchCageManageForm(String newValue) throws SQLException, ClassNotFoundException;
    List<CageManageFormDto> getAllEmployee() throws SQLException;
    List<CageManageFormDto> getAllCages() throws SQLException;

}
