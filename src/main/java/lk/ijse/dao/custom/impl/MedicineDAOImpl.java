package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.MedicineDAO;
import lk.ijse.entity.Food;
import lk.ijse.entity.Medicine;
import lk.ijse.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicineDAOImpl implements MedicineDAO {
    @Override
    public ArrayList<Medicine> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Medicine");

        ArrayList<Medicine> allAnimalMedicine = new ArrayList<>();
        while (resultSet.next()) {
            Medicine entity = new Medicine(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4)
            );
            allAnimalMedicine.add(entity);
        }
        return allAnimalMedicine;
    }

    @Override
    public boolean save(Medicine entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "INSERT INTO Medicine VALUES(?, ?, ?, ?)",
            entity.getMediId(),
            entity.getName(),
            entity.getPrice(),
            entity.getQty());
    }

    @Override
    public boolean update(Medicine entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(  "UPDATE Medicine SET Name = ?, Price = ?, QTY = ?, StockStatus = ? WHERE MediId = ?",
                entity.getMediId(),
                entity.getName(),
                entity.getPrice(),
                entity.getQty());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Medicine WHERE MediId = ?");

    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Medicine WHERE MediId = ?" , id);
        return resultSet.next();
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT MediId FROM Medicine ORDER BY MediId DESC LIMIT 1;");
        if (rst.next()){
            String id = rst.getString("MediId");;
            int newMedicine = Integer.parseInt(id.replace("M00","")) + 1;
            return String.format("M00",newMedicine);
        }else {
            return "M001";
        }
    }

    @Override
    public Medicine search(String newValue) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Medicine WHERE MediId=?", newValue);

        Medicine entity = null;
        if(rst.next()) {
            entity = new Medicine(
                    newValue + "",
                    rst.getString(1),
                    rst.getDouble(2),
                    rst.getInt(3));
        }

        return entity;
    }

    @Override
    public boolean updateStock(String mediId, int qty) throws SQLException {
        return false;
    }
}
