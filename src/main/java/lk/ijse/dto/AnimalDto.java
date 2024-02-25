package lk.ijse.dto;

import lk.ijse.entity.Animal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnimalDto extends Animal implements Serializable {
    private  String animalTg;
    private  String category;
    private  String animalType;
    private  String cageId;
    private  String AdminId;

}
