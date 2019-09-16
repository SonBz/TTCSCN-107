package com.hvktmm.at13.controller;

import com.hvktmm.at13.dao.UserDao;
import com.hvktmm.at13.model.CompanyItem;
import com.hvktmm.at13.model.ProductItem;
import com.hvktmm.at13.model.User;
import com.hvktmm.at13.model.UserItem;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.util.Callback;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.Initializable;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.InitializationException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.function.Predicate;

public class UserController implements Initializable {

    @FXML
    private JFXTextField txtLastName,txtFirstName,txtEmail,txtTell,txtSearch;
    @FXML
    private JFXTextField txtAddress,txtDateOfBirth,txtUsername,txtPassword;
    @FXML
    private JFXRadioButton rbMale,rbFemale;
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn tbFirstName,tbLastName,tbEmail,tbTell,tbAddress,tbDateOfBirth,tbGender,tbUsername;
    @FXML
    private Pagination pagination;
    @FXML
    private TableColumn<User, String> tbId = new TableColumn<User, String>();

    private ObservableList<User> user_list = FXCollections.observableArrayList();
    FilteredList<User> filteredList =new FilteredList<>(user_list,e->true);
    UserDao userDao = new UserDao();
    int itemPage = 5, from = 0, to =0;


    @Override
    public void initialize() throws InitializationException {
//        int count = userDao.countUser();
        user_list.addAll(userDao.userList());
        tableView();
        tableView.setItems(user_list);
//        int pageCount = (count/itemPage) + 1;
//        pagination.setPageCount(pageCount);
//        pagination.setPageFactory(this::createPage);
    }

    public void ClickSave(ActionEvent event) throws ParseException {
        User user = new User(txtFirstName.getText(),txtLastName.getText(),txtUsername.getText(),txtPassword.getText(),
                            txtEmail.getText(),txtTell.getText(),txtAddress.getText(),new SimpleDateFormat("yyyy-mm-dd").parse(txtDateOfBirth.getText()),
                            rbFemale.isSelected()?"Nữ":"Nam");
        userDao.insertUser(user);
        User user_table = new User(txtFirstName.getText(),txtLastName.getText(),txtUsername.getText(), txtEmail.getText(),
                txtTell.getText(),txtAddress.getText(),new SimpleDateFormat("yyyy-mm-dd").parse(txtDateOfBirth.getText()),
                rbFemale.isSelected()?"Nữ":"Nam");
        user_list.add(user_table);
        setNull();

    }
    public void ClickExit(ActionEvent event){
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

    }

    public void setNull(){
        txtFirstName.setText("");
        txtLastName.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        txtEmail.setText("");
        txtTell.setText("");
        txtAddress.setText("");
        txtDateOfBirth.setText("");

    }

    public void tableView(){
        tbId.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> param) {
                return new ReadOnlyObjectWrapper(tableView.getItems().indexOf(param.getValue()) + 1);
            }
        });
        tbId.setSortable(false);
        tbFirstName.setCellValueFactory(new PropertyValueFactory<User, String>("first_name"));
        tbLastName.setCellValueFactory(new PropertyValueFactory<User, String>("last_name"));
        tbEmail.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        tbTell.setCellValueFactory(new PropertyValueFactory<User, String>("phone_number"));
        tbAddress.setCellValueFactory(new PropertyValueFactory<User, String>("address"));
        tbDateOfBirth.setCellValueFactory(new PropertyValueFactory<User, Date>("date_of_birth"));
        tbGender.setCellValueFactory(new PropertyValueFactory<User, String>("gender"));
        tbUsername.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
    }

//    private Node createPage(int pageIndex){
//        from = pageIndex * itemPage;
//        to = itemPage;
//        user_list.addAll(userDao.userListLimit(from,to));
//        tableView.setItems(user_list);
//        return  tableView;
//    }

    public void clickDelete(ActionEvent event){
        User user = (User) tableView.getSelectionModel().getSelectedItem();
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("XÁC NHẬN");
        alert.setHeaderText("Bạn có chắc chắn muốn xóa");
        alert.setContentText("User : "+user.getUsername());
        ButtonType buttonTypeYes = new ButtonType("YES", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("NO", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(buttonTypeYes,buttonTypeNo);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == buttonTypeYes){
            userDao.deleteUser(user.getUsername());
            user_list.removeAll((User) tableView.getSelectionModel().getSelectedItem());
        }

    }
    @FXML
    public void searchUser(){
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(user ->{
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if(user.getLast_name().toLowerCase().indexOf(lowerCaseFilter) !=-1 ){
                    return true;
                }
                else if(user.getFirst_name().toLowerCase().indexOf(lowerCaseFilter) != -1 ){
                    return true;
                }
                else if(user.getUsername().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                return false;
            });
        });
        SortedList<User> userSortedList = new SortedList<>(filteredList);
        userSortedList.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(userSortedList);
    }

}
