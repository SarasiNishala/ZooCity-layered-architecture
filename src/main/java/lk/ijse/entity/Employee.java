package lk.ijse.entity;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Employee {
    private String empId;
    private String empName;
    private String empAddress;
    private int empContact;
    private String category;
    private String shiftId;
    private String adminId;

}
