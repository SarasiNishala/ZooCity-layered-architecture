package lk.ijse.entity;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Food {
    private String FoodId;
    private String Name;
    private double Price;
    private int Qty;
}
