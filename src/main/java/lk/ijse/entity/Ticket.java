package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ticket {
    private String TicketNo;
    private String TicketType;
    private Double Price;
    private LocalDate Date;
    private String AdminId;

}
