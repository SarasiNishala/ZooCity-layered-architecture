package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class SalaryDto {
    private String SalaryId;
    private String EmpId;
    private Double Payment;
    private Date Date;

    public SalaryDto(String salaryId, String empId, Double payment, LocalDate date) {
    }
}
