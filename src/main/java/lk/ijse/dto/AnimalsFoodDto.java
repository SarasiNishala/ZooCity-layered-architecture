package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data

public class AnimalsFoodDto {
    private String AnimalTg;
    private String FoodId;
    private java.sql.Date Date;
    private java.sql.Time Time;
    private int Qty;
}
