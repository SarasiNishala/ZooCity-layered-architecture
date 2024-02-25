package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.WorkScheduleBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.WorkScheduleDAO;
import lk.ijse.dto.WorkScheduleDto;
import lk.ijse.entity.WorkSchedule;

import java.sql.SQLException;
import java.util.ArrayList;

public class WorkScheduleBOImpl implements WorkScheduleBO {
    WorkScheduleDAO workScheduleDAO = (WorkScheduleDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.WORKSCHEDULE);
    @Override
    public ArrayList<WorkScheduleDto> getAllSchedule() throws SQLException, ClassNotFoundException {
        ArrayList<WorkSchedule> workSchedules = workScheduleDAO.getAll();
        ArrayList<WorkScheduleDto> workScheduleDtos = new ArrayList<>();

        for (WorkSchedule workSchedule :workSchedules ){
            workScheduleDtos.add(new WorkScheduleDto(workSchedule.getScheduleId(),workSchedule.getHours(),workSchedule.getDate()));
        }
        return workScheduleDtos;
    }

    @Override
    public boolean saveSchedule(WorkScheduleDto dto) throws SQLException, ClassNotFoundException {
        return workScheduleDAO.save(new WorkSchedule(dto.getScheduleId(),dto.getHours(),dto.getDate()));
    }

    @Override
    public boolean updateSchedule(WorkScheduleDto dto) throws SQLException, ClassNotFoundException {
        return workScheduleDAO.update(new WorkSchedule(dto.getScheduleId(),dto.getHours(),dto.getDate()));
    }

    @Override
    public boolean deleteSchedule(String id) throws SQLException, ClassNotFoundException {
        return workScheduleDAO.delete(id);
    }

    @Override
    public boolean existSchedule(String id) throws SQLException, ClassNotFoundException {
        return workScheduleDAO.exist(id);
    }

    @Override
    public String generateNewScheduleId() throws SQLException, ClassNotFoundException {
        return workScheduleDAO.generateNewId();
    }

    @Override
    public WorkScheduleDto searchSchedule(String newValue) throws SQLException, ClassNotFoundException {
        WorkSchedule workSchedule = workScheduleDAO.search(newValue);
        WorkScheduleDto workScheduleDto = new WorkScheduleDto(workSchedule.getScheduleId(),workSchedule.getHours(),workSchedule.getDate());
        return workScheduleDto;
    }
}
