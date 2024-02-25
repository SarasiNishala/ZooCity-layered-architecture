package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dao.SuperDAO;
import lk.ijse.dto.WorkScheduleDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface WorkScheduleBO extends SuperBO {
    ArrayList<WorkScheduleDto> getAllSchedule() throws SQLException, ClassNotFoundException;
    boolean saveSchedule(WorkScheduleDto dto) throws SQLException, ClassNotFoundException;
    boolean updateSchedule(WorkScheduleDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteSchedule(String id) throws SQLException, ClassNotFoundException;
    boolean existSchedule(String id) throws SQLException, ClassNotFoundException;
    String generateNewScheduleId() throws SQLException, ClassNotFoundException;
    WorkScheduleDto searchSchedule(String newValue) throws SQLException, ClassNotFoundException;
}
