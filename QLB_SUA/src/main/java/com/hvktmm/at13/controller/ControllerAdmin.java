package com.hvktmm.at13.controller;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class ControllerAdmin implements Initializable {
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label txtName, txtTime;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a");
        txtName.setText(HomeController.name);
        txtTime.setText(ft.format(dNow));
        try {
            VBox vBox = FXMLLoader.load(getClass().getResource("/view/SidePanelContent.fxml"));
            drawer.setSidePane(vBox);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HamburgerBackArrowBasicTransition transition =  new HamburgerBackArrowBasicTransition(hamburger);
//        HamburgerBasicCloseTransition transition = new HamburgerBasicCloseTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                transition.setRate(transition.getRate() * -1);
                transition.play();
                if (drawer.isOpened()) {
                    drawer.close();
                } else {
                    drawer.open();
                }
            }
        });
    }
    public void ClickImportWarehouse(ActionEvent event) throws Exception{
        Redict("/view/ImportWarehouse.fxml","Nhập Kho");
    }
    public void ClickExportWarehouse(ActionEvent event) throws Exception{
        Redict("/view/ExportWarehouse.fxml","Hóa Đơn");
    }
    public void ClickProduct(ActionEvent event) throws Exception{
        Redict("/view/Products.fxml","Sản Phẩm");
    }

    public void  ClickCompany(ActionEvent event) throws Exception {
        Redict("/view/Company.fxml", "Công Ty");
    }
    public void  ClickUser(ActionEvent event) throws Exception {
        Redict("/view/User.fxml", "Quản Lý Nhân Viên");
    }

    public void Redict(String scene,String title) throws Exception{
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(scene));
        Parent root = FXMLLoader.load(getClass().getResource(scene));
//        Parent parent= fxmlLoader.load();
        Stage stage = new Stage();
        stage.getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
