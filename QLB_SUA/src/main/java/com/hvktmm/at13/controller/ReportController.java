package com.hvktmm.at13.controller;

import com.hvktmm.at13.dao.UserDao;
import com.hvktmm.at13.model.TransactionHistory;
import com.hvktmm.at13.model.UserReport;
import com.hvktmm.at13.report.ReportUser;
import com.hvktmm.at13.service.HistoryService;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReportController implements Initializable {

    @FXML
    private JFXTextField txtNameFile;
    UserDao userDao = new UserDao();
    ReportUser reportUser = new ReportUser();
    HistoryService historyService = new HistoryService();
    @FXML
    void ClickReportExport(ActionEvent event) throws FileNotFoundException, JRException {
        String nameFile =  txtNameFile.getText() +".pdf";
        ObservableList<TransactionHistory> listHistory =  historyService.historyExportData();
        String namePrameter = "HistoryParameter";
        String jasper = "lichSuBan.jrxml";
        reportUser.report(nameFile,listHistory,namePrameter,jasper);
    }

    @FXML
    void ClickReportImport(ActionEvent event) {

    }

    @FXML
    void ClickReportUser(ActionEvent event) throws FileNotFoundException, JRException {
        String nameFile =  txtNameFile.getText() +".pdf";
        ObservableList<UserReport> listUser = userDao.userReportList();
        String namePrameter = "parameter";
        String jasper = "quanLyNguoiBan.jrxml";
        reportUser.report(nameFile,listUser,namePrameter,jasper);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
