package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.MedicineDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MedicineBO extends SuperBO {
    ArrayList<MedicineDto> getAllAnimalMedicine() throws SQLException, ClassNotFoundException;
     boolean saveMedicine(MedicineDto dto) throws SQLException, ClassNotFoundException;
    boolean updateMedicine(MedicineDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteMedicine(String id) throws SQLException, ClassNotFoundException;
    boolean existMedicine(String id) throws SQLException, ClassNotFoundException;
    String generateNewMedicineId() throws SQLException, ClassNotFoundException;
    MedicineDto searchMedicine(String newValue) throws SQLException, ClassNotFoundException;
    boolean updateStock(String foodId, int qty) throws SQLException;
}
