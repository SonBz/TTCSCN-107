package com.hvktmm.at13.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import comhvktmm.at13.utils.RedictUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SidePanelController implements Initializable {
    @FXML
    private Label txtNameUser;
    @FXML
    private JFXButton btnLogout,btnProfile;
    RedictUtils redictUtils = new RedictUtils();

    public void ClickLogout(ActionEvent event) throws Exception{
        Main main = new Main();
        Stage stage = (Stage) btnLogout.getScene().getWindow();
        main.start(stage);
    }
    public  void ClickProfile(ActionEvent event) throws  Exception{
        redictUtils.Redict("/view/Profile.fxml","Trang Cá Nhân");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtNameUser.setText(HomeController.name);
    }
}
