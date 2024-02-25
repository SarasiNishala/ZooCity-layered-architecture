package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketDto {
        private String TicketNo;
        private String TicketType;
        private Double Price;
        private LocalDate Date;
        private String AdminId;
}
