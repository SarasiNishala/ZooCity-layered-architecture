package lk.ijse.entity;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Medicine {
    private String MediId;
    private String Name;
    private double Price;
    private int Qty;
}
