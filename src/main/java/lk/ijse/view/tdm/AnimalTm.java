package lk.ijse.view.tdm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnimalTm {
    private  String animalTg;
    private  String category;
    private  String animalType;
    private  String cageId;
    private  String AdminId;
    private Button btn;
}