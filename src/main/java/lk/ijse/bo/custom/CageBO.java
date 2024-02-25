package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.CageDto;
import lk.ijse.entity.Cage;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CageBO extends SuperBO {
    ArrayList<CageDto> getAllCages() throws SQLException, ClassNotFoundException;
    boolean saveCage(CageDto dto) throws SQLException, ClassNotFoundException;
    boolean updateCage(CageDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteCage(String id) throws SQLException, ClassNotFoundException;
    boolean existCage(String id) throws SQLException, ClassNotFoundException;
    String generateNewCageId() throws SQLException, ClassNotFoundException;
    CageDto searchCage(String newValue) throws SQLException, ClassNotFoundException;
}
