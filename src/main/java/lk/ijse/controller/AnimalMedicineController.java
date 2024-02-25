package lk.ijse.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.AnimalBO;
import lk.ijse.bo.custom.AnimalsMediBO;
import lk.ijse.bo.custom.MedicineBO;
import lk.ijse.dto.AnimalDto;
import lk.ijse.dto.AnimalsMediDto;
import lk.ijse.dto.MedicineDto;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;

public class AnimalMedicineController {
    @FXML
    private ComboBox<String> cmbAnimalId;

    @FXML
    private ComboBox<String> cmbMediId;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private TextField txtQty;

    private TableView <?> tbl;
    AnimalsMediBO animalsMediBO = (AnimalsMediBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ANIMALSMEDICINE);
    MedicineBO medicineBO = (MedicineBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MEDICINE);
    AnimalBO animalBO = (AnimalBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ANIMAL);

    @FXML
    void btnClearOnAction(ActionEvent event) {clearFields();}

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = cmbAnimalId.getValue();

        try {
            boolean isDeleted = animalsMediBO.deleteAnimalsMedi(id);

            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, " deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnEditOnAction(ActionEvent event) {
        boolean b = validateAnimaMedi();
        if (b){
            String mediId = cmbMediId.getValue();
            String animalId = cmbAnimalId.getValue();
            int qty = Integer.parseInt(txtQty.getText());
            Date date = Date.valueOf(lblDate.getText());
            Time time = Time.valueOf(lblTime.getText());

            var dto = new AnimalsMediDto(animalId,mediId,date,time,qty);

            try {
                boolean isSaved = animalsMediBO.updateAnimalsMedi(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Animals Food Updated!").show();
                    clearFields();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        boolean b = validateAnimaMedi();

        if(b){
            String mediId = cmbMediId.getValue();
            String animalId = cmbAnimalId.getValue();
            int qty = Integer.parseInt(txtQty.getText());
            Date date = Date.valueOf(lblDate.getText());
            Time time= Time.valueOf(lblTime.getText());

            var dto = new AnimalsMediDto(animalId,mediId,date,time,qty);

            try {
                boolean isSaved = animalsMediBO.saveAnimalsMedi(dto);
                if (isSaved) {
                    MedicineModel medicineModel = new MedicineModel();
                    boolean isUpdateMedicine = medicineModel.updateStock(mediId,qty);
                    if (isUpdateMedicine){
                        new Alert(Alert.AlertType.CONFIRMATION, "Animal Food Saved!").show();
                        tbl.refresh();
                        clearFields();}
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }
    private void generateRealTime() {
        lblDate.setText(LocalDate.now().toString());
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
            lblTime.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    public void initialize() {
        generateRealTime();
        setComboBox();

    }
    void clearFields() {
        cmbAnimalId.setValue("");
        cmbMediId.setValue("");
        txtQty.clear();
    }
    private boolean validateAnimaMedi() {
        String qty = (txtQty.getText());
        boolean isValidTel = Pattern.matches("(\\d{1,})", qty);

        if (!isValidTel){
            new Alert(Alert.AlertType.ERROR, "Invalid Qty").show();
            return false;
        }

        return true;
    }

    public  void setComboBox(){

        ObservableList<String> obListAni = FXCollections.observableArrayList();
        try {
            List<AnimalDto> list = animalBO.getAllAnimals();

            for (AnimalDto dto : list) {
                obListAni.add(dto.getAnimalTg());
            }

            cmbAnimalId.setItems(obListAni);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        ObservableList<String> obListFd = FXCollections.observableArrayList();
        try {
            List<MedicineDto> list = medicineBO.getAllAnimalMedicine();

            for (MedicineDto dto : list) {
                obListFd.add(dto.getMediId());
            }

            cmbMediId.setItems(obListFd);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void setData(TableView tbl) {
        this.tbl=tbl;
    }
}
