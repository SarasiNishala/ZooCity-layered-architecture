package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.SalaryDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SalaryBO extends SuperBO {
    ArrayList<SalaryDto> getAllSalary() throws SQLException, ClassNotFoundException;
    boolean saveSalary(SalaryDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteSalary(String id) throws SQLException, ClassNotFoundException;
    boolean existSalary(String id) throws SQLException, ClassNotFoundException;
    String generateNewSalaryId() throws SQLException, ClassNotFoundException;
    SalaryDto searchSalary(String newValue) throws SQLException, ClassNotFoundException;
    String checkCategory(String empId) throws SQLException;
}
