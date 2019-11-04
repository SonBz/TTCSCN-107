package com.hvktmm.at13.controller;

import com.hvktmm.at13.model.Customer;
import com.hvktmm.at13.model.TransactionHistory;
import com.hvktmm.at13.service.HistoryService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import comhvktmm.at13.utils.RedictUtils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;


public class HistoryController implements Initializable {

    @FXML
    private TableView tableImport;
    @FXML
    private TableColumn tbIdIm, tbAmountIm, tbDateIm, tbNoteIm, tbNameIm, tbUserIm;
    @FXML
    private TableView tableExport;
    @FXML
    private TableColumn tbIdEx, tbAmountEx, tbDateEx, tbNote, tbNameEx, tbUserEx;
    @FXML
    private JFXTextField txtSearchIm,txtSearchEx;
    @FXML
    private JFXButton btnExit;
    RedictUtils redictUtils = new RedictUtils();
    ObservableList<TransactionHistory> historyExport = FXCollections.observableArrayList();
    ObservableList<TransactionHistory> historyImport = FXCollections.observableArrayList();
    private FilteredList<TransactionHistory> filteredListExport=new FilteredList<>(historyExport, e->true);
    private FilteredList<TransactionHistory> filteredListImport =new FilteredList<>(historyImport, e->true);
    HistoryService historyService = new HistoryService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        historyImport.addAll(historyService.historyImportData());
        historyExport.addAll(historyService.historyExportData());

        tableHistoryExport();
        tableHistoryImport();
    }

    public void clickExit() throws Exception {
        redictUtils.Redict("/view/ControllerAdmin.fxml","Trang Chủ",btnExit);
    }
    public void tableHistoryImport(){
        tbIdIm.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TransactionHistory, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TransactionHistory, String> param) {
                return new ReadOnlyObjectWrapper(tableImport.getItems().indexOf(param.getValue()) + 1);
            }
        });
        tbIdIm.setSortable(false);
        tbAmountIm.setCellValueFactory(new PropertyValueFactory<>("amount"));
        tbDateIm.setCellValueFactory(new PropertyValueFactory<>("dateExport"));
        tbNoteIm.setCellValueFactory(new PropertyValueFactory<>("note"));
        tbNameIm.setCellValueFactory(new PropertyValueFactory<>("nameProduct"));
        tbUserIm.setCellValueFactory(new PropertyValueFactory<>("user"));
        tableImport.setItems(historyImport);

    }
    public void tableHistoryExport(){
        tbIdEx.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TransactionHistory, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TransactionHistory, String> param) {
                return new ReadOnlyObjectWrapper(tableExport.getItems().indexOf(param.getValue()) + 1);
            }
        });
        tbIdEx.setSortable(false);
        tbAmountEx.setCellValueFactory(new PropertyValueFactory<>("amount"));
        tbDateEx.setCellValueFactory(new PropertyValueFactory<>("dateExport"));
        tbNote.setCellValueFactory(new PropertyValueFactory<>("note"));
        tbNameEx.setCellValueFactory(new PropertyValueFactory<>("nameProduct"));
        tbUserEx.setCellValueFactory(new PropertyValueFactory<>("user"));
        tableExport.setItems(historyExport);
    }
    public void searchExport() {
        txtSearchEx.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredListExport.setPredicate(e ->{
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if(e.getNameProduct().toLowerCase().indexOf(lowerCaseFilter) !=-1){
                    return true;
                }else if (e.getUser().toLowerCase().indexOf(lowerCaseFilter) !=-1){
                    return true;
                }
                return false;
            });
        });
        SortedList<TransactionHistory> userSortedList = new SortedList<>(filteredListExport);
        userSortedList.comparatorProperty().bind(tableExport.comparatorProperty());
        tableExport.setItems(userSortedList);
    }

    public void searchImport() {
       txtSearchIm.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredListImport.setPredicate(transactionHistory ->{
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                System.out.println("1");
                String lowerCaseFilter = newValue.toLowerCase();
                if(transactionHistory.getNameProduct().toLowerCase().indexOf(lowerCaseFilter) !=-1){
                    return true;
                }else if (transactionHistory.getUser().toLowerCase().indexOf(lowerCaseFilter) !=-1){
                    return true;
                }
                return false;
            });
        });
        SortedList<TransactionHistory> userSortedList = new SortedList<>(filteredListImport);
        userSortedList.comparatorProperty().bind(tableImport.comparatorProperty());
        tableImport.setItems(userSortedList);
    }
}
