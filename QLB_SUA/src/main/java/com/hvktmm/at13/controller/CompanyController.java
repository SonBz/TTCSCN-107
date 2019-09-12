package com.hvktmm.at13.controller;

import com.hvktmm.at13.dao.CompayDao;
import com.hvktmm.at13.model.Company;
import com.hvktmm.at13.model.CompanyItem;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CompanyController implements Initializable {
    @FXML
    private JFXTextField txtName,txtAddress,txtTell;
    @FXML
    private TableView<Company> tableView;
    @FXML
    private TableColumn tbId;
    @FXML
    private TableColumn tbName;
    @FXML
    private TableColumn tbAddress;
    @FXML
    private TableColumn tbTell;

    private ObservableList<Company> companyList = FXCollections.observableArrayList();


    public void ClickAddCompany(ActionEvent event) throws IOException {
        int id;
        Company company = new Company(txtName.getText(),txtTell.getText(),txtAddress.getText());
        CompayDao compayDao = new CompayDao();
        try {
            id = compayDao.insert(company);
            Company companyItem = new Company(id,txtName.getText(),txtTell.getText(),txtAddress.getText());
            txtName.setText("");
            txtAddress.setText("");
            txtTell.setText("");
            companyList.add(companyItem);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void ClickClose(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        CompayDao compayDao = new CompayDao();
        companyList = compayDao.companyList();
        tbId.setCellFactory(col -> {
            TableCell<String, Integer> indexCell = new TableCell<>();
            ReadOnlyObjectProperty<TableRow> rowProperty = indexCell.tableRowProperty();
            ObjectBinding<String> rowBinding = Bindings.createObjectBinding(() -> {
                TableRow<String> row = rowProperty.get();
                if (row != null) {
                    int rowIndex = row.getIndex()+1;
                    if (rowIndex <= row.getTableView().getItems().size()) {
                        return Integer.toString(rowIndex);
                    }
                }
                return null;
            }, rowProperty);
            indexCell.textProperty().bind(rowBinding);
            return indexCell;
        });
        tbName.setCellValueFactory(new PropertyValueFactory<Company, String>("name"));
        tbAddress.setCellValueFactory(new PropertyValueFactory<Company, String>("address"));
        tbTell.setCellValueFactory(new PropertyValueFactory<Company, String>("phone_number"));
        tableView.setItems(companyList);
    }
}
