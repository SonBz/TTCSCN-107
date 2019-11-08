package com.hvktmm.at13.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import comhvktmm.at13.utils.RedictUtils;
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

import javax.xml.soap.Text;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class ControllerAdmin implements Initializable {
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXButton btnExport,btnProduct,btnImport,btnCompany,btnUser,btnCustomer,btnHistory,btnReport;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label txtName, txtTime,txtNameUser;
    RedictUtils redictUtils = new RedictUtils();
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
                if (drawer.isShown()) {
                    drawer.close();
                } else {
                    drawer.open();
                }
            }
        });
    }
    public void ClickImportWarehouse(ActionEvent event) throws Exception{
        redictUtils.Redict("/view/ImportWarehouse.fxml","Nhập Kho", btnExport);
    }
    public void ClickExportWarehouse(ActionEvent event) throws Exception{
        redictUtils.Redict("/view/ExportWarehouse.fxml","Hóa Đơn", btnImport);
    }
    public void ClickProduct(ActionEvent event) throws Exception{
        redictUtils.Redict("/view/Products.fxml","Sản Phẩm", btnProduct);
    }

    public void  ClickCompany(ActionEvent event) throws Exception {
        redictUtils.Redict("/view/Company.fxml", "Công Ty", btnCompany);
    }
    public void  ClickUser(ActionEvent event) throws Exception {
        redictUtils.Redict("/view/User.fxml", "Quản Lý Nhân Viên", btnUser);
    }
    public void  ClickCustomer(ActionEvent event ) throws Exception{
        redictUtils.Redict("/view/Customer.fxml", "Chi Tiết Khác Hàng", btnCustomer);
    }
    public void  ClickHistory(ActionEvent event ) throws Exception{
        redictUtils.Redict("/view/History.fxml", "Lịch Sử Giao Dịch", btnHistory);
    }
    public void ClickReport(ActionEvent event) throws Exception {
        redictUtils.Redict("/view/Report.fxml", "Báo Cáo Khách Hàng", btnReport);
    }

}
