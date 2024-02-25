package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.CageManageFormDAO;
import lk.ijse.entity.CageManageForm;
import lk.ijse.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CageManageFormDAOImpl implements CageManageFormDAO {
    @Override
    public ArrayList<CageManageForm> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(CageManageForm entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "INSERT INTO CageControl VALUES(?, ?, ?, ?, ?)",
                entity.getEmpId(),
                entity.getCageId(),
                entity.getDate(),
                entity.getTime());
    }

    @Override
    public boolean update(CageManageForm entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "UPDATE CageControl SET CageId = ?, Date = ?, Time = ?, Status = ? WHERE EmpId = ?",
                entity.getEmpId(),
                entity.getCageId(),
                entity.getDate(),
                entity.getTime());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM CageControl WHERE EmpId = ?",id);
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM CageControl WHERE EmpId = ?" , id);
        return resultSet.next();
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public CageManageForm search(String newValue) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM CageControl WHERE EmpId=?", newValue);

        CageManageForm entity = null;
        if(rst.next()) {
            entity = new CageManageForm(
                    newValue + "",
                    rst.getString(1),
                    rst.getDate(2),
                    rst.getTime(3));
        }

        return entity;
    }

    @Override
    public List<CageManageForm> getAllEmployee() throws SQLException {
        ResultSet resultSet = null;
        try {
            resultSet = SQLUtil.execute( "SELECT * FROM CageControl");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        ArrayList<CageManageForm> allEmployee = new ArrayList<>();
        while (resultSet.next()) {
            CageManageForm entity = new CageManageForm(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDate(3),
                    resultSet.getTime(4)
            );
            allEmployee.add(entity);
        }
        return allEmployee;
    }

    @Override
    public List<CageManageForm> getAllCages() throws SQLException {
        ResultSet resultSet = null;
        try {
            resultSet = SQLUtil.execute( "SELECT * FROM CageControl");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        ArrayList<CageManageForm> allCages = new ArrayList<>();
        while (resultSet.next()) {
            CageManageForm entity = new CageManageForm(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDate(3),
                    resultSet.getTime(4)
            );
            allCages.add(entity);
        }
        return allCages;
    }
}
