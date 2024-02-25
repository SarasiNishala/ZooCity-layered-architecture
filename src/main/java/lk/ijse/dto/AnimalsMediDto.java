package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class AnimalsMediDto {
    private String AnimalTg;
    private String MediId;
    private java.sql.Date Date;
    private java.sql.Time Time;
    private int Qty;
}
