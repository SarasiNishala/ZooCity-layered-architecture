package lk.ijse.view.tdm;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketTm {
    private String TicketNo;
    private String TicketType;
    private Double Price;
    private LocalDate Date;
    private String AdminId;
}
