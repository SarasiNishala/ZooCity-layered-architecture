package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.SalaryBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.SalaryDAO;
import lk.ijse.dto.MedicineDto;
import lk.ijse.dto.SalaryDto;
import lk.ijse.entity.Medicine;
import lk.ijse.entity.Salary;

import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryBOImpl implements SalaryBO {
    SalaryDAO salaryDAO = (SalaryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SALARY);
    @Override
    public ArrayList<SalaryDto> getAllSalary() throws SQLException, ClassNotFoundException {
        ArrayList<Salary> salaries = salaryDAO.getAll();
        ArrayList<SalaryDto> salaryDtos = new ArrayList<>();

        for (Salary salary :salaries ){
            salaryDtos.add(new SalaryDto(salary.getEmpId(),salary.getEmpId(),salary.getPayment(),salary.getDate()));
        }
        return salaryDtos;
    }

    @Override
    public boolean saveSalary(SalaryDto dto) throws SQLException, ClassNotFoundException {
        return salaryDAO.save(new Salary(dto.getSalaryId(),dto.getEmpId(),dto.getPayment(),dto.getDate()));
    }

    @Override
    public boolean deleteSalary(String id) throws SQLException, ClassNotFoundException {
        return salaryDAO.delete(id);
    }

    @Override
    public boolean existSalary(String id) throws SQLException, ClassNotFoundException {
        return salaryDAO.exist(id);
    }

    @Override
    public String generateNewSalaryId() throws SQLException, ClassNotFoundException {
        return salaryDAO.generateNewId();
    }

    @Override
    public SalaryDto searchSalary(String newValue) throws SQLException, ClassNotFoundException {
        Salary salary = salaryDAO.search(newValue);
        SalaryDto salaryDto = new SalaryDto(salary.getEmpId(),salary.getEmpId(),salary.getPayment(),salary.getDate());
        return salaryDto;
    }

    @Override
    public String checkCategory(String empId) throws SQLException {
        return null;
    }
}
