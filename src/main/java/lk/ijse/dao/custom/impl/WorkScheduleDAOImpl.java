package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.WorkScheduleDAO;
import lk.ijse.entity.WorkSchedule;
import lk.ijse.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WorkScheduleDAOImpl implements WorkScheduleDAO {
    @Override
    public ArrayList<WorkSchedule> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute(  "SELECT * FROM Schedule");

        ArrayList<WorkSchedule> allSchedule = new ArrayList<>();
        while (resultSet.next()) {
            WorkSchedule entity = new WorkSchedule(
                    resultSet.getString(1),
                    resultSet.getInt(2),
                    resultSet.getDate(3).toLocalDate()
            );
            allSchedule.add(entity);
        }
        return allSchedule;
    }

    @Override
    public boolean save(WorkSchedule entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Schedule VALUES(?, ?, ?)",
                entity.getScheduleId(),
                entity.getHours(),
                entity.getDate());
    }

    @Override
    public boolean update(WorkSchedule entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Schedule SET Hours = ?, Date = ? WHERE ScheduleId = ?",
                entity.getScheduleId(),
                entity.getHours(),
                entity.getDate());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Schedule WHERE ScheduleId = ?");
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Schedule WHERE ScheduleId = ?" , id);
        return resultSet.next();
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT ScheduleId FROM Schedule ORDER BY ScheduleId DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("ScheduleId");
            int newScheduleId = Integer.parseInt(id.replace("S00", "")) + 1;
            return String.format("S00d", newScheduleId);
        } else {
            return "S001";
        }
    }

    @Override
    public WorkSchedule search(String newValue) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Schedule WHERE ScheduleId=?", newValue);

        WorkSchedule entity = null;
        if(rst.next()) {
            entity = new WorkSchedule(
                    newValue + "",
                    rst.getInt(1),
                    rst.getDate(2).toLocalDate());
        }

        return entity;
    }
}
