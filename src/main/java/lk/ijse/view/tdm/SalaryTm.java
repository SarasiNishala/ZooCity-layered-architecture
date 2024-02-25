package lk.ijse.view.tdm;

import javafx.scene.control.Button;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class SalaryTm {
    private String SalaryId;
    private String EmpId;
    private Double Payment;
    private LocalDate Date;
    private Button btn;

    public SalaryTm(String salaryId, String empId, Double payment, java.util.Date date, Button btn) {
    }
}