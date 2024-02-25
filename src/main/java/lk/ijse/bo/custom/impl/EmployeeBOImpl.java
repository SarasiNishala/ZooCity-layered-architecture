package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.EmployeeBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.EmployeeDAO;
import lk.ijse.dto.EmployeeDto;
import lk.ijse.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    @Override
    public ArrayList<EmployeeDto> getAllEmployee() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> employees = employeeDAO.getAll();
        ArrayList<EmployeeDto> employeeDtos = new ArrayList<>();

        for (Employee employee :employees ){
            employeeDtos.add(new EmployeeDto(employee.getEmpId(),employee.getEmpName(),employee.getEmpAddress(),employee.getEmpContact(),employee.getCategory(),employee.getShiftId(),employee.getAdminId()));
        }
        return employeeDtos;
    }

    @Override
    public boolean saveEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(new Employee(dto.getEmpId(),dto.getEmpName(),dto.getEmpAddress(),dto.getEmpContact(),dto.getCategory(),dto.getShiftId(),dto.getAdminId()));
    }

    @Override
    public boolean updateEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(dto.getEmpId(),dto.getEmpName(),dto.getEmpAddress(),dto.getEmpContact(),dto.getCategory(),dto.getShiftId(),dto.getAdminId()));

    }

    @Override
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }

    @Override
    public boolean existEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public EmployeeDto searchEmployee(String newValue) throws SQLException, ClassNotFoundException {
        Employee employee = employeeDAO.search(newValue);
        EmployeeDto employeeDto = new EmployeeDto(employee.getEmpId(),employee.getEmpName(),employee.getEmpAddress(),employee.getEmpContact(),employee.getCategory(),employee.getShiftId(),employee.getAdminId());
        return employeeDto;
    }

    @Override
    public String checkCategory(String empId) throws SQLException {
        return null;
    }
}
