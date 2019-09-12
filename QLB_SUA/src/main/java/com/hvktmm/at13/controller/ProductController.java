package com.hvktmm.at13.controller;

import com.hvktmm.at13.dao.CompayDao;
import com.hvktmm.at13.dao.ProductDao;
import com.hvktmm.at13.model.Company;
import com.hvktmm.at13.model.Product;
import com.hvktmm.at13.model.ProductItem;
import com.hvktmm.at13.service.CompanyService;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductController implements Initializable {

    @FXML
    private JFXComboBox cbCompany,cbCapacity;
    @FXML
    private JFXTextField txtName,txtProductType,txtPrice;
    @FXML
    private TableView<ProductItem> tableView;
    @FXML
    private TableColumn tbStt,tbName,tbPrice,tbProductType,tbCapacity,tbCompany;

    private ObservableList<ProductItem> product_list = FXCollections.observableArrayList();
    private ObservableList<String> companyName = FXCollections.observableArrayList();
    private ObservableList<String> capacity = FXCollections.observableArrayList("100ml","200ml","300ml");
    CompayDao compayDao = new CompayDao();
    ProductDao productDao = new ProductDao();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        companyName = compayDao.NameCompany();
        //combox
        cbCompany.getItems().addAll(companyName);
        cbCompany.getItems().add(companyName.get(0));
        cbCompany.setValue(companyName.get(0));
        cbCapacity.getItems().addAll(capacity);
        cbCapacity.getItems().add(capacity.get(0));
        cbCapacity.setValue(capacity.get(0));

        //show table
        CompanyService companyService = new CompanyService();
        product_list = companyService.ProductNameCompany();

        tbStt.setCellFactory(col -> {
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
        tbName.setCellValueFactory(new PropertyValueFactory<ProductItem, String>("name"));
        tbPrice.setCellValueFactory(new PropertyValueFactory<ProductItem, Double>("price"));
        tbProductType.setCellValueFactory(new PropertyValueFactory<ProductItem, String>("product_type"));
        tbCapacity.setCellValueFactory(new PropertyValueFactory<ProductItem, String>("capacity"));
        tbCompany.setCellValueFactory(new PropertyValueFactory<ProductItem, String>("company"));
        tableView.setItems(product_list);


    }

    public void clickSave(ActionEvent event) throws IOException {
        String name = String.valueOf(cbCompany.getValue());
        int idCom = compayDao.idCompany(name);
        Product product = new Product(txtName.getText(),Double.valueOf(txtPrice.getText()),
                            String.valueOf(cbCapacity.getValue()),txtProductType.getText(),idCom);

        productDao.insert(product);
    }

}
