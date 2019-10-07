package com.hvktmm.at13.controller;

import com.hvktmm.at13.dao.CompayDao;
import com.hvktmm.at13.dao.ProductDao;
import com.hvktmm.at13.model.Product;
import com.hvktmm.at13.model.ProductItem;
import com.hvktmm.at13.model.User;
import com.hvktmm.at13.service.CompanyService;
import com.hvktmm.at13.service.ProductService;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.DefaultStringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.LongStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ProductController implements Initializable {

    @FXML
    private JFXComboBox cbCompany,cbCapacity;
    @FXML
    private JFXTextField txtName,txtProductType,txtPrice,txtSearch,txtAmount;
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn<ProductItem, String> tbName,tbProductType,tbCapacity,tbCompany,tbAmount;
    @FXML
    private TableColumn<ProductItem, Long> tbPrice;
    @FXML
    private TableColumn tbEdit;
    @FXML
    private TableColumn<ProductItem, String> tbId = new TableColumn<ProductItem, String>();

    private ObservableList<ProductItem> product_list = FXCollections.observableArrayList();
    private ObservableList<String> companyName = FXCollections.observableArrayList();
    private ObservableList<String> capacity = FXCollections.observableArrayList("100ml","200ml","300ml");
    FilteredList<ProductItem> filteredList =new FilteredList<>(product_list, e->true);
    CompayDao compayDao = new CompayDao();
    ProductDao productDao = new ProductDao();
    ProductService companyService = new ProductService();
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
        product_list.addAll(companyService.ProductNameCompany());
        tbId.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ProductItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ProductItem, String> param) {
                return new ReadOnlyObjectWrapper(tableView.getItems().indexOf(param.getValue()) + 1);
            }
        });
        tbId.setSortable(false);
        tbName.setCellValueFactory(new PropertyValueFactory<ProductItem, String>("name"));
        tbPrice.setCellValueFactory(new PropertyValueFactory<ProductItem, Long>("price"));
        tbProductType.setCellValueFactory(new PropertyValueFactory<ProductItem, String>("product_type"));
        tbCapacity.setCellValueFactory(new PropertyValueFactory<ProductItem, String>("capacity"));
        tbCapacity.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(), capacity));
        tbCompany.setCellValueFactory(new PropertyValueFactory<ProductItem, String>("company"));
        tbCompany.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(), companyName));
        tbAmount.setCellValueFactory(new PropertyValueFactory<ProductItem, String>("amount"));
        tableView.setItems(product_list);
        editTable();
        tbEdit.setCellFactory(tableColumEdit());
        tableView.setEditable(true);

    }

    public void clickSave(ActionEvent event) throws IOException {
        String name = String.valueOf(cbCompany.getValue());
        int idCom = compayDao.idCompany(name);
        Product product = new Product(txtName.getText(),Long.valueOf(txtPrice.getText()),
                            String.valueOf(cbCapacity.getValue()),txtProductType.getText(),idCom, Integer.valueOf(txtAmount.getText()));

        productDao.insert(product);
        product_list = companyService.ProductNameCompany();
        tableView.setItems(product_list);
    }

    public  void clickExit(ActionEvent event){
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    public void searchUser(){
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(product ->{
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if(product.getName().toLowerCase().indexOf(lowerCaseFilter) !=-1 ){
                    return true;
                }
                else if(product.getCapacity().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                return false;
            });
        });
        SortedList<ProductItem> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedList);
    }

    public void clickDelete(ActionEvent event){
        ProductItem productItem = (ProductItem) tableView.getSelectionModel().getSelectedItem();
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("XÁC NHẬN");
        alert.setHeaderText("Bạn có chắc chắn muốn xóa");
        alert.setContentText("Tên sản phẩm : "+productItem.getName());
        ButtonType buttonTypeYes = new ButtonType("YES", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("NO", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(buttonTypeYes,buttonTypeNo);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == buttonTypeYes){
            productDao.deleteProduct(productItem.getName());
            product_list.removeAll((ProductItem) tableView.getSelectionModel().getSelectedItem());
        }

    }
    public void editTable() {
        tbName.setCellFactory(TextFieldTableCell.forTableColumn());
        tbName.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setName(e.getNewValue());
        });
        tbPrice.setCellFactory(TextFieldTableCell.forTableColumn(new LongStringConverter()));

        tbPrice.setOnEditCommit((CellEditEvent<ProductItem, Long> e) -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setPrice(e.getNewValue());
        });
        tbProductType.setCellFactory(TextFieldTableCell.forTableColumn());
        tbProductType.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setProduct_type(e.getNewValue());
        });
        tbCapacity.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setCapacity(e.getNewValue());
        });
        tbCompany.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setCompany(e.getNewValue());
        });
    }

    protected Callback tableColumEdit() {
        Callback<TableColumn<ProductItem, String>, TableCell<ProductItem, String>> cellCallback = (pagram) -> {
            final TableCell<ProductItem, String> tableCell = new TableCell<ProductItem, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        final Button button = new Button("Edit");
                        button.setOnAction(event -> {
                            ProductItem productItem = getTableView().getItems().get(getIndex());
                            Product product = new Product(productItem.getName(),productItem.getPrice(),productItem.getCapacity(),
                                                productItem.getProduct_type(),productItem.getCompany_id());
                            productDao.updateProduct(product);
                        });
                        setGraphic(button);
                        setText(null);
                    }
                }
            };
            return tableCell;
        };
        return cellCallback;
    }


}
