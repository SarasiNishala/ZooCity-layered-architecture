package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dto.CageManageFormDto;
import lk.ijse.entity.CageManageForm;

import java.sql.SQLException;
import java.util.List;

public interface CageManageFormDAO extends CrudDAO<CageManageForm> {
    List<CageManageForm> getAllEmployee() throws SQLException;
    List<CageManageForm> getAllCages() throws SQLException;
}
