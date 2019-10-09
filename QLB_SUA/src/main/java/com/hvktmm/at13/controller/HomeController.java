package com.hvktmm.at13.controller;

import com.hvktmm.at13.dao.UserDao;
import com.hvktmm.at13.model.User;
import com.jfoenix.controls.*;
import comhvktmm.at13.utils.RedictUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class HomeController {
    @FXML
    private JFXButton btnLogin;
    @FXML
    private JFXTextField txtUserName;
    @FXML
    private JFXPasswordField txtPassword;
    public static int userId = 0;
    public  static  String name = "";
    RedictUtils redictUtils = new RedictUtils();

    public void clickLogin(ActionEvent event) throws Exception{
        String userName = txtUserName.getText();
        String pass = txtPassword.getText();
        UserDao userDao = new UserDao();
        User user = userDao.login(userName,pass);

        if (user!=null && user.getIs_staff()==1){
            userId = user.getId();
            name = user.getLast_name().toUpperCase()+" "+user.getFirst_name().toUpperCase();
            redictUtils.Redict("/view/ControllerAdmin.fxml","Trang Chủ",btnLogin);
        }
        else if(user!=null && user.getIs_staff()==0){
            userId = user.getId();
            Stage stage= (Stage) btnLogin.getScene().getWindow();
            Parent parent= FXMLLoader.load(getClass().getResource("/view/Controller.fxml"));
            Scene scene=new Scene(parent);
//            boolean fullscreen=stage.isFullScreen();
//            stage.setFullScreen(true);
            stage.setScene(scene);
            stage.show();
        }
        else {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Đăng Nhập không thành công");
            alert.setContentText("Sai tên tài khoản hoặc mật khẩu");
            alert.showAndWait();
            txtPassword.setText("");
            txtUserName.setText("");
        }
    }

}
