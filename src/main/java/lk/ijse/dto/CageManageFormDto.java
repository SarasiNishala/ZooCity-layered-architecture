package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CageManageFormDto {
    private String EmpId;
    private String CageId;
    private LocalDate Date;
    private LocalDateTime Time;
}
