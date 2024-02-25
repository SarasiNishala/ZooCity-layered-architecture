package lk.ijse.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.*;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.AnimalDto;
import lk.ijse.dto.AnimalsFoodDto;
import lk.ijse.dto.FoodDto;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;

public class AnimalFoodsController {
    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private ComboBox <String> cmbAnimalId;

    @FXML
    private ComboBox <String> cmbFoodId;

    @FXML
    private TextField txtQty;
    AnimalsFoodBO animalsFoodBO = (AnimalsFoodBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ANIMALSFOOD);
    FoodBO foodBO = (FoodBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.FOOD);
    AnimalBO animalBO = (AnimalBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ANIMAL);

    @FXML
    void btnClearOnAction(ActionEvent event) {clearFields();}

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = cmbAnimalId.getValue();

        try {
            boolean isDeleted = animalsFoodBO.deleteAnimalsFood(id);

            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

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
            List<FoodDto> list = foodBO.getAllAnimalFood();

            for (FoodDto dto : list) {
                obListFd.add(dto.getFoodId());
            }

            cmbFoodId.setItems(obListFd);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnEditOnAction(ActionEvent event) throws SQLException {
        boolean b = validateAnimalFood();
        if (b){
            String foodId = cmbFoodId.getValue();
            String animalId = cmbAnimalId.getValue();
            int qty = Integer.parseInt(txtQty.getText());
            Date date = Date.valueOf(lblDate.getText());
            Time time = Time.valueOf(lblTime.getText());

            var dto = new AnimalsFoodDto(animalId,foodId,date,time,qty);

            Connection connection = null;
            try {
                connection = DBConnection.getDbConnection().getConnection();
                connection.setAutoCommit(false);

                boolean isSaved = animalsFoodBO.updateAnimalsFood(dto);
                if (isSaved) {
                    FoodModel foodModel = new FoodModel();
                    boolean isUpdateFood = foodModel.updateStock(foodId,qty);
                    if (isUpdateFood) {
                        connection.commit();
                        new Alert(Alert.AlertType.CONFIRMATION, "Animals Food Updated!").show();
                        clearFields();
                    }
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                connection.rollback();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } finally {
                connection.setAutoCommit(true);
            }
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        boolean b = validateAnimalFood();

        if(b){
            String foodId = cmbFoodId.getValue();
            String animalId = cmbAnimalId.getValue();
            int qty = Integer.parseInt(txtQty.getText());
            Date date = Date.valueOf(lblDate.getText());
            Time time = Time.valueOf(lblTime.getText());

            var dto = new AnimalsFoodDto(animalId,foodId,date,time,qty);


            try {
                boolean isSaved = animalsFoodBO.saveAnimalsFood(dto);
                if (isSaved) {
                    FoodModel foodModel = new FoodModel();
                    boolean isUpdateFood = foodModel.updateStock(foodId,qty);
                    if (isUpdateFood){
                    new Alert(Alert.AlertType.CONFIRMATION, "Animal Food Saved!").show();
                    clearFields();}
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }

    }
    public void initialize() {
         generateRealTime();
         setComboBox();

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
    void clearFields() {
        cmbAnimalId.setValue("");
        cmbFoodId.setValue("");
        txtQty.clear();
    }
    private boolean validateAnimalFood() {
        String qty = (txtQty.getText());
        boolean isValidTel = Pattern.matches("(\\d{1,})", qty);

        if (!isValidTel){
            new Alert(Alert.AlertType.ERROR, "Invalid Qty").show();
            return false;
        }
        return true;
    }
}
