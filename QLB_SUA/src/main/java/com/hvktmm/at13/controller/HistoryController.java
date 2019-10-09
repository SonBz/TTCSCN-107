package com.hvktmm.at13.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import comhvktmm.at13.utils.RedictUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;


public class HistoryController implements Initializable {

    @FXML
    private TableView tableImport;
    @FXML
    private TableColumn tbIdIm, tbAmountIm, tbDateIm, tbNoteIm, tbNameIm, tbUserIm, tbAmuontPrIm;
    @FXML
    private TableView tableExport;
    @FXML
    private TableColumn tbIdEx, tbAmountEx, tbDateEx, tbNote, tbNameEx, tbUserEx, tbAmuontPrEx;
    @FXML
    private JFXTextField txtSearchIm,txtSearchEx;
    @FXML
    private JFXButton btnExit;
    RedictUtils redictUtils = new RedictUtils();

    @FXML
    public void searchExport(KeyEvent event) {

    }

    @FXML
    public void searchImport(KeyEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void clickExit() throws Exception {
        redictUtils.Redict("/view/ControllerAdmin.fxml","Trang Chủ",btnExit);
    }
}
