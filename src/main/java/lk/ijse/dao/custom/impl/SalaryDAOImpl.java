package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.SalaryDAO;
import lk.ijse.entity.Salary;
import lk.ijse.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryDAOImpl implements SalaryDAO {
    @Override
    public ArrayList<Salary> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Salary");

        ArrayList<Salary> allSalary = new ArrayList<>();
        while (resultSet.next()) {
            Salary entity = new Salary(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getDate(4)
            );
            allSalary.add(entity);
        }
        return allSalary;
    }

    @Override
    public boolean save(Salary entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "INSERT INTO Salary VALUES(?, ?, ?, ?)",
            entity.getSalaryId(),
            entity.getEmpId(),
            entity.getPayment(),
            entity.getDate());
    }

    @Override
    public boolean update(Salary dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "DELETE FROM Salary WHERE SalaryId = ?");
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Salary WHERE SalaryId = ?");
        return resultSet.next();
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT SalaryId FROM Salary ORDER BY SalaryId DESC LIMIT 1;");
        if (rst.next()){
            String id = rst.getString("SalaryId");;
            int newMedicine = Integer.parseInt(id.replace("S00","")) + 1;
            return String.format("S00",newMedicine);
        }else {
            return "S001";
        }
    }

    @Override
    public Salary search(String newValue) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Salary WHERE SalaryId=?", newValue);

        Salary entity = null;
        if(rst.next()) {
            entity = new Salary(
                    newValue + "",
                    rst.getString(1),
                    rst.getDouble(2),
                    rst.getDate(3));
        }

        return entity;
    }

    @Override
    public String checkCategory(String empId) throws SQLException {
        return null;
    }
}
