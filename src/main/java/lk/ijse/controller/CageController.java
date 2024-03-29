package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.CageBO;
import lk.ijse.dto.CageDto;
import lk.ijse.view.tdm.CageTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class CageController {
    @FXML
    private ComboBox<String> cmbType;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colAnimalNo;

    @FXML
    private TableColumn<?, ?> colCageId;

    @FXML
    private TableColumn<?, ?> colCageType;

    @FXML
    private Label lblCageId;

    @FXML
    private TableView<CageTm> tblCage;

    @FXML
    private TextField txtNo;
    CageBO cageBO = (CageBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CAGE);

    @FXML
    void btnCageControl(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/CageManageForm.fxml"));
        Scene scene = new Scene(rootNode);

        Stage stage = new Stage();

        stage.setScene(scene);
        stage.centerOnScreen();

        stage.show();

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {clearFields();}

    @FXML
    void btnEditOnAction(ActionEvent event) {
        boolean b = validateCage();
        if (b){
            String id = lblCageId.getText();
            int no = Integer.parseInt(txtNo.getText());
            String type = cmbType.getValue();

            var dto = new CageDto(id,type,no);

            try {
                boolean isSaved = cageBO.updateCage(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Animal Updated!").show();
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
        boolean b = validateCage();

        if(b){
            String id = lblCageId.getText();
            int no = Integer.parseInt(txtNo.getText());
            String type = cmbType.getValue();

            var dto = new CageDto(id,type,no);

            try {
                boolean isSaved = cageBO.saveCage(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Cage saved!").show();
                    clearFields();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }
    void clearFields() {
        lblCageId.setText("");
        txtNo.clear();
        cmbType.setValue("");
    }

    private boolean validateCage() {

        String no = (txtNo.getText());
        boolean isValidTel = Pattern.matches("(\\d{1,})", no);

        if (!isValidTel){
            new Alert(Alert.AlertType.ERROR, "Invalid number").show();
            return false;
        }

        return true;
    }

    public  void setComboBox() {
        cmbType.getItems().add("Mammals");
        cmbType.getItems().add("Birds");
        cmbType.getItems().add("Fish");
    }

    public void initialize() {
        setComboBox();
        setListener();
        loadAllData();
        generateNextCageId();

        colCageId.setCellValueFactory(new PropertyValueFactory<>("CageId"));
        colCageType.setCellValueFactory(new PropertyValueFactory<>("Type"));
        colAnimalNo.setCellValueFactory(new PropertyValueFactory<>("NoOfANimals"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    private void setListener() {
        tblCage.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    var dto = new CageDto(
                            newValue.getCageId(),
                            newValue.getType(),
                            newValue.getNoOfANimals()
                    );
                    setFields(dto);
                });
    }

    private void setFields(CageDto dto) {
        lblCageId.setText(dto.getCageId());
        cmbType.setValue(dto.getType());
        txtNo.setText(String.valueOf(dto.getNoOfANimals()));
    }

    private void loadAllData() {

        try {
            List<CageDto> dtoList = cageBO.getAllCages();

            ObservableList<CageTm> obList = FXCollections.observableArrayList();

            for(CageDto dto : dtoList) {
                Button btn = new Button("remove");
                btn.setCursor(Cursor.HAND);

                btn.setOnAction((e) -> {
                    ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

                    if(type.orElse(no) == yes) {
                        int selectedIndex = tblCage.getSelectionModel().getSelectedIndex();
                        String code = (String) colCageId.getCellData(selectedIndex);

                        deleteCage(code);

                        obList.remove(selectedIndex);
                        tblCage.refresh();
                    }
                });
                var tm = new CageTm(
                        dto.getCageId(),
                        dto.getType(),
                        dto.getNoOfANimals(),
                        btn
                );
                obList.add(tm);
            }
            tblCage.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void deleteCage(String code) {
        try {
            boolean isDeleted = cageBO.deleteCage(code);
            if(isDeleted)
                new Alert(Alert.AlertType.CONFIRMATION, "cage deleted!").show();
        } catch (SQLException ex) {
            new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void generateNextCageId() {
        try {
            String cageId = cageBO.generateNewCageId();
            lblCageId.setText(cageId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
