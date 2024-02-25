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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.MedicineBO;
import lk.ijse.dto.MedicineDto;
import lk.ijse.view.tdm.MedicineTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class MedicineController {
    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colMedicineId;

    @FXML
    private TableColumn<?, ?> colMedicineName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQty;


    @FXML
    private Label lblMediId;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<MedicineTm> tblMedicine;

    public static TableView tbl;

    @FXML
    private TextField txtMediName;

    @FXML
    private TextField txtMediPrice;

    @FXML
    private TextField txtQty;

    MedicineBO medicineBO = (MedicineBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MEDICINE);
    @FXML
    void btnAnimalMedi(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/AnimalMedicine.fxml.fxml"));
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
        boolean b = validateMedicine();
        if (b){
            String id = lblMediId.getText();
            String foodName = txtMediName.getText();
            double price = Double.parseDouble(txtMediPrice.getText());
            int qty = Integer.parseInt(txtQty.getText());

            var dto = new MedicineDto(id,foodName,price,qty);

            try {
                boolean isSaved = medicineBO.updateMedicine(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Medicine Updated!").show();
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
        boolean b = validateMedicine();

        if(b){
            String id = lblMediId.getText();
            String mediName = txtMediName.getText();
            double price = Double.parseDouble(txtMediPrice.getText());
            int qty = Integer.parseInt(txtQty.getText());

            var dto = new MedicineDto(id,mediName,price,qty);

            try {
                boolean isSaved = medicineBO.saveMedicine(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Medicine saved!").show();
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
        lblMediId.setText("");
        txtMediName.clear();
        txtMediPrice.clear();
        txtQty.clear();
    }
    private boolean validateMedicine() {
        String name = txtMediName.getText();
        boolean isValid = Pattern.matches("([a-zA-Z\\s]+)", name);

        if (!isValid){
            new Alert(Alert.AlertType.ERROR, "Invalid Name").show();
            return false;
        }

        String price = (txtMediPrice.getText());
        boolean isValidAddress = Pattern.matches("(\\d{2,})", price);

        if (!isValidAddress){
            new Alert(Alert.AlertType.ERROR, "Invalid price").show();
            return false;
        }

        String qty = (txtQty.getText());
        boolean isValidTel = Pattern.matches("(\\d{1,})", qty);

        if (!isValidTel){
            new Alert(Alert.AlertType.ERROR, "Invalid Qty").show();
            return false;
        }

        return true;
    }
    public void initialize() {
        setCellValueFactory();
        loadAllMedicine();
        generateNextMediId();
        tbl=tblMedicine;
    }

    private void generateNextMediId() {
        try {
            String medicineId = medicineBO.generateNewMedicineId();
            lblMediId.setText(medicineId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllMedicine() {
        try {
            List<MedicineDto> dtoList = medicineBO.getAllAnimalMedicine();

            ObservableList<MedicineTm> obList = FXCollections.observableArrayList();

            for(MedicineDto dto : dtoList) {
                Button btn = new Button("remove");
                btn.setCursor(Cursor.HAND);

                btn.setOnAction((e) -> {
                    ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

                    if(type.orElse(no) == yes) {
                        int selectedIndex = tblMedicine.getSelectionModel().getSelectedIndex();
                        String code = (String) colMedicineId.getCellData(selectedIndex);

                        deleteMedicine(code);

                        obList.remove(selectedIndex);
                        tblMedicine.refresh();
                    }
                });
                var tm = new MedicineTm(
                        dto.getMediId(),
                        dto.getName(),
                        dto.getPrice(),
                        dto.getQty(),
                        btn
                );
                obList.add(tm);
            }
            tblMedicine.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void deleteMedicine(String code) {
        try {
            boolean isDeleted = medicineBO.deleteMedicine(code);
            if(isDeleted)
                new Alert(Alert.AlertType.CONFIRMATION, "Medicine deleted!").show();
        } catch (SQLException ex) {
            new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colMedicineId.setCellValueFactory(new PropertyValueFactory<>("MediId"));
        colMedicineName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

}
