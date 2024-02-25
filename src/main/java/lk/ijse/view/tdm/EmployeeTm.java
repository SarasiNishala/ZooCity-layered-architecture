package lk.ijse.view.tdm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data

public class EmployeeTm {
    private String empId;
    private String empName;
    private String empAddress;
    private int empContact;
    private String category;
    private String shiftId;
    private String adminId;
    private Button btn;
}
