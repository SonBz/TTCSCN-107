package com.hvktmm.at13.controller;

import com.hvktmm.at13.dao.UserDao;
import com.hvktmm.at13.model.TransactionHistory;
import com.hvktmm.at13.model.UserReport;
import com.hvktmm.at13.report.ReportFile;
import com.hvktmm.at13.service.HistoryService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import comhvktmm.at13.utils.RedictUtils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.application.Application.launch;

public class ReportController implements Initializable {

    @FXML
    private JFXTextField txtNameFile;
    @FXML
    private JFXButton btnExit;

    UserDao userDao = new UserDao();
    ReportFile reportUser = new ReportFile();
    HistoryService historyService = new HistoryService();
    RedictUtils redictUtils = new RedictUtils();
    @FXML
    void ClickReportExport(ActionEvent event) throws FileNotFoundException, JRException {
        String nameFile =  txtNameFile.getText() +".pdf";
        ObservableList<TransactionHistory> listHistory =  historyService.historyExportData();
        String namePrameter = "HistoryParameter";
        String jasper = "lichSuBan.jrxml";
        reportUser.report(nameFile,listHistory,namePrameter,jasper);
        txtNameFile.setText("");
    }

    @FXML
    void ClickReportImport(ActionEvent event) throws FileNotFoundException, JRException {
        String nameFile =  txtNameFile.getText() +".pdf";
        ObservableList<TransactionHistory> listHistory =  historyService.historyImportData();
        String namePrameter = "PrHistoryImport";
        String jasper = "nhapkho.jrxml";
        reportUser.report(nameFile,listHistory,namePrameter,jasper);
    }

    @FXML
    void ClickReportUser(ActionEvent event) throws FileNotFoundException, JRException {
        String nameFile =  txtNameFile.getText() +".pdf";
        ObservableList<UserReport> listUser = userDao.userReportList();
        String namePrameter = "parameter";
        String jasper = "quanLyNguoiBan.jrxml";
        reportUser.report(nameFile,listUser,namePrameter,jasper);
        txtNameFile.setText("");
    }

    public void ClickExit(ActionEvent event) throws Exception {
        redictUtils.Redict("/view/ControllerAdmin.fxml","Trang Chủ",btnExit);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
