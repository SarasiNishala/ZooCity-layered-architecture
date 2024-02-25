package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class AnimalsMedi {
    private String AnimalTg;
    private String MediId;
    private java.sql.Date Date;
    private java.sql.Time Time;
    private int Qty;
}
