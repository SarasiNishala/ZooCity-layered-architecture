package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.EmployeeDAO;
import lk.ijse.entity.Employee;
import lk.ijse.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute( "SELECT * FROM Employee");

        ArrayList<Employee> allEmployee = new ArrayList<>();
        while (resultSet.next()) {
            Employee entity = new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            );
            allEmployee.add(entity);
        }
        return allEmployee;
    }

    @Override
    public boolean save(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Employee VALUES(?, ?, ?, ?,?,?,?)",
                entity.getEmpId(),
                entity.getEmpName(),
                entity.getEmpAddress(),
                entity.getEmpContact(),
                entity.getCategory(),
                entity.getShiftId(),
                entity.getAdminId());
    }

    @Override
    public boolean update(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Employee SET EmpName = ?, Address = ?, Contact = ?, Category = ?, ScheduleId = ?, AdminId = ? WHERE EmpId = ?",
                entity.getEmpId(),
                entity.getEmpName(),
                entity.getEmpAddress(),
                entity.getEmpContact(),
                entity.getCategory(),
                entity.getShiftId(),
                entity.getAdminId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Employee WHERE EmpId = ?",id);

    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Employee WHERE EmpId = ?" , id);
        return resultSet.next();
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Employee search(String newValue) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Employee WHERE EmpId=?", newValue);

        Employee entity = null;
        if(rst.next()) {
            entity = new Employee(
                    newValue + "",
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6));
        }

        return entity;
    }

    @Override
    public String checkCategory(String empId) throws SQLException {
        try {
            ResultSet resultSet = SQLUtil.execute("SELECT Category FROM Employee WHERE EmpId = ?");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
