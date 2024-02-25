package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Animal {
    private  String animalTg;
    private  String category;
    private  String animalType;
    private  String cageId;
    private  String AdminId;
}
