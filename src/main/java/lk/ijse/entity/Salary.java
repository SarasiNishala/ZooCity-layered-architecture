package lk.ijse.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data

public class Salary {
    private String SalaryId;
    private String EmpId;
    private Double Payment;
    private java.util.Date Date;
}