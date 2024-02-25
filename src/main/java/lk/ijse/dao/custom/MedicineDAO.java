package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Medicine;

import java.sql.SQLException;

public interface MedicineDAO extends CrudDAO<Medicine> {
    boolean updateStock(String mediId, int qty) throws SQLException;
}
