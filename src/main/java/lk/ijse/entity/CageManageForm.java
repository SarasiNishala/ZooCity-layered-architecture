package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CageManageForm {
    private String EmpId;
    private String CageId;
    private LocalDate Date;
    private LocalDateTime Time;

    public CageManageForm(String empId, String cageId, java.sql.Date date, java.sql.Time time) {
    }
}
