package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.TicketBO;
import lk.ijse.dto.TicketDto;
import lk.ijse.view.tdm.TicketTm;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class TicketController {

    @FXML
    private ComboBox<String> cmbType;

    @FXML
    private TableColumn<?, ?> colAdminId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colTicketNo;

    @FXML
    private TableColumn<?, ?> colTicketType;

    @FXML
    private Label lblIncomeId;
    @FXML
    private Label lblDate;

    @FXML
    private Label lblPrice;

    @FXML
    private Label lblTicketId;

    @FXML
    private TableView<TicketTm> tblTicket;
    TicketBO ticketBO = (TicketBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.TICKET);
    @FXML
    void btnEditOnAction(ActionEvent event) {
        String TicketNo = lblTicketId.getText();
        String TicketType = cmbType.getValue();
        Double price = Double.valueOf(lblPrice.getText());
        String IncomeId = lblIncomeId.getText();
        String adminId = lblIncomeId.getText();
        LocalDate date = LocalDate.parse(lblDate.getText());

       var dto = new TicketDto(TicketNo,TicketType,price,date,adminId);

        try {
            boolean isUpdated = ticketBO.updateTickt(dto);
           System.out.println(isUpdated);
           if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, " updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String TicketNo = lblTicketId.getText();
        String TicketType = cmbType.getValue();
        Double price = Double.valueOf(lblPrice.getText());
        String IncomeId = lblIncomeId.getText();
        LocalDate date = LocalDate.parse(lblDate.getText());

        var dto = new TicketDto(TicketNo,TicketType,price,date,"A001");

        try {
            boolean isSaved = ticketBO.saveTicket(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "saved!").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void clearFields() {
        lblTicketId.setText("");
        cmbType.setValue("");
        lblPrice.setText("");
        lblIncomeId.setText("");
        lblDate.setText("");
    }

    public void initialize(){
        setComboBox();
        setCellValueFactory();
        loadAllData();
        setListener();
        generateNextTicketId();
        setDate();
    }
    private void setDate() {
        String date = String.valueOf(LocalDate.now());
        lblDate.setText(date);
    }

    private void setListener() {
        tblTicket.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    var dto = new TicketDto(
                            newValue.getTicketNo(),
                            newValue.getTicketType(),
                            newValue.getPrice(),
                            newValue.getDate(),
                            newValue.getAdminId()
                            );
                    setFields(dto);
                });
    }

    private void setFields(TicketDto dto) {
        lblIncomeId.setText(dto.getTicketNo());
        cmbType.setValue(dto.getTicketType());
        lblPrice.setText(String.valueOf(dto.getPrice()));
        lblDate.setText(String.valueOf(dto.getDate()));
    }

    private void loadAllData() {


        ObservableList<TicketTm> obList = FXCollections.observableArrayList();

        try {
            List<TicketDto> dtoList = ticketBO.getAllTickets();

            for(TicketDto dto : dtoList) {
                obList.add(
                        new TicketTm(
                                dto.getTicketNo(),
                                dto.getTicketType(),
                                dto.getPrice(),
                                dto.getDate(),
                                dto.getAdminId())
                );
            }

            tblTicket.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    private void setComboBox() {
        cmbType.getItems().add("Full");
        cmbType.getItems().add("Half");
    }

    private void setCellValueFactory() {
        colTicketNo.setCellValueFactory(new PropertyValueFactory<>("TicketNo"));
        colTicketType.setCellValueFactory(new PropertyValueFactory<>("TicketType"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        colAdminId.setCellValueFactory(new PropertyValueFactory<>("AdminId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
    }

    private void generateNextTicketId() {
        try {
            String ticketId = ticketBO.generateNewTicketId();
            lblTicketId.setText(ticketId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void selectTypeOnAction(ActionEvent event) throws SQLException {
        String TicketType = cmbType.getValue();

        if (TicketType.equals("Full")) {
            lblPrice.setText("300.00");
        } else if (TicketType.equals("Half")) {
            lblPrice.setText("200.00");
        }
    }
}
