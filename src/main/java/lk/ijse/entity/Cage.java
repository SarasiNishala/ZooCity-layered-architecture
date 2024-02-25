package lk.ijse.entity;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cage {
    private String CageId;
    private String Type;
    private int NoOfANimals;

}
