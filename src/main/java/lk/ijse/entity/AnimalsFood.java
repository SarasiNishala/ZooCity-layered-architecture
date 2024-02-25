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

public class AnimalsFood {
    private String AnimalTg;
    private String FoodId;
    private java.sql.Date Date;
    private java.sql.Time Time;
    private int Qty;

    public AnimalsFood(String animalTg, String foodId, LocalDate date, LocalDateTime time) {
    }
}
