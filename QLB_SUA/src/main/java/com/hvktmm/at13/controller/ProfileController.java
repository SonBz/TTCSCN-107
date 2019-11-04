package com.hvktmm.at13.controller;

import com.hvktmm.at13.dao.UserDao;
import com.hvktmm.at13.model.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import comhvktmm.at13.utils.PassUtils;
import comhvktmm.at13.utils.RedictUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;


import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    @FXML
    private Label lbGender, lbAddress, lpPhone, lbBirthday, lbFullName, lbUsername,lbEmail;

    @FXML
    private JFXPasswordField txtPass, txtPassNew, txtPassConfirm;

    @FXML
    private JFXButton btnExit;

    RedictUtils redictUtils = new RedictUtils();
    UserDao userDao = new UserDao();
    PassUtils passEncode=new PassUtils();

    @FXML
    public void ClickExit(ActionEvent event) throws Exception {
        redictUtils.Redict("/view/ControllerAdmin.fxml","Trang Chủ",btnExit);
    }

    @FXML
    public void ClickSave(ActionEvent event) throws Exception {
        User user = userDao.getUser(HomeController.userId);
        String pass = txtPass.getText();
        String enPass = passEncode.encrypt(pass);
        String passNew = txtPassNew.getText();
        String passComFirm = txtPassConfirm.getText();
        if(pass.equals("")|| passNew.equals("")||passComFirm.equals("")){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Tạo Mật Khẩu Thất Bại");
            alert.setContentText("Bạn Phải Nhập Đủ Thông Tin");
            alert.showAndWait();
        }else {
            if(!passNew.equals(passComFirm) ){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Tạo Mật Khẩu Thất Bại");
                alert.setContentText("Mật Khẩu Bạn Nhập Không Trùng Nhau");
                alert.showAndWait();
            }else {
                if(enPass.equals(user.getPassword())){
                    userDao.updatePassword(passNew,HomeController.userId);
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("THÀNH CÔNG");
                    alert.setHeaderText("Đổi mật khẩu thành công");
//                    alert.setContentText("Quay lại màn hình đăng nhập");
                    alert.showAndWait();
                    txtPass.setText("");
                    txtPassConfirm.setText("");
                    txtPassNew.setText("");
                }else {
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("Tạo Mật Khẩu Thất Bại");
                    alert.setContentText("Mật Khẩu Bạn Nhập Sai");
                    alert.showAndWait();
                }
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        User user = userDao.getUser(HomeController.userId);
        this.checkNull(lbGender,user.getGender());
        this.checkNull(lbAddress,user.getAddress());
        this.checkNull(lpPhone,user.getPhone_number());
        this.checkNull(lbEmail,user.getEmail());
        this.checkNull(lbBirthday,String.valueOf(user.getDate_of_birth()));
        String fullName = user.getFirst_name() +" "+ user.getLast_name();
        lbFullName.setText(fullName);
        lbUsername.setText(user.getUsername());

    }

    private void checkNull(Label label, String str){
        if(str==null){
            label.setText(str);
        }else {
            label.setText("  "+str);
        }
    }
}
