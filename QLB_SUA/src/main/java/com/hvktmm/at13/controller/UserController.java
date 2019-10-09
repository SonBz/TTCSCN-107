package com.hvktmm.at13.controller;

import com.hvktmm.at13.dao.UserDao;
import com.hvktmm.at13.model.CompanyItem;
import com.hvktmm.at13.model.ProductItem;
import com.hvktmm.at13.model.User;
import com.hvktmm.at13.model.UserItem;
import com.jfoenix.controls.*;
import comhvktmm.at13.utils.DateTableUtils;
import comhvktmm.at13.utils.RedictUtils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.util.Callback;
import javafx.util.converter.DefaultStringConverter;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.Initializable;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.InitializationException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.function.Predicate;

public class UserController implements Initializable {

    @FXML
    private JFXTextField txtLastName, txtFirstName, txtEmail, txtTell, txtSearch;
    @FXML
    private JFXTextField txtAddress, txtUsername, txtPassword;
    @FXML
    private JFXDatePicker txtDateOfBirth;
    @FXML
    private JFXRadioButton rbMale, rbFemale;
    @FXML
    private TableView<User> tableView;
    @FXML
    private TableColumn<User, String> tbFirstName, tbLastName, tbEmail, tbTell, tbAddress, tbUsername,tbGender;
    @FXML
    private TableColumn<User, Date> tbDateOfBirth;
    @FXML
    private TableColumn tbEdit;
    @FXML
    private Pagination pagination;
    @FXML
    private JFXButton btnExit;
    RedictUtils redictUtils = new RedictUtils();
    @FXML
    private TableColumn<User, String> tbId = new TableColumn<User, String>();
    private ObservableList<String> gender = FXCollections.observableArrayList("Nam", "Nữ");

    private ObservableList<User> user_list = FXCollections.observableArrayList();
    FilteredList<User> filteredList = new FilteredList<>(user_list, e -> true);
    UserDao userDao = new UserDao();
    int itemPage = 5, from = 0, to = 0;


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
        User user = new User(txtFirstName.getText(), txtLastName.getText(), txtUsername.getText(), txtPassword.getText(),
                txtEmail.getText(), txtTell.getText(), txtAddress.getText(),
                new SimpleDateFormat("yyyy-mm-dd").parse(txtDateOfBirth.getValue().toString()), rbFemale.isSelected() ? "Nữ" : "Nam");
        userDao.insertUser(user);
        User user_table = new User(txtFirstName.getText(), txtLastName.getText(), txtUsername.getText(), txtEmail.getText(),
                txtTell.getText(), txtAddress.getText(), new SimpleDateFormat("yyyy-mm-dd").parse(txtDateOfBirth.getValue().toString()),
                rbFemale.isSelected() ? "Nữ" : "Nam", new JFXButton("update"));
        user_list.add(user_table);
        setNull();

    }

    public void clickExit(ActionEvent event) throws Exception {
//        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
        redictUtils.Redict("/view/ControllerAdmin.fxml","Trang Chủ",btnExit);

    }

    public void setNull() {
        txtFirstName.setText("");
        txtLastName.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        txtEmail.setText("");
        txtTell.setText("");
        txtAddress.setText("");
        txtDateOfBirth.setValue(null);

    }
    public void tableView() {
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
        tbGender.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(), gender));
        tbGender.setCellValueFactory(new PropertyValueFactory<User, String>("gender"));
        tbUsername.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        tableView.setEditable(true);
        editTable();
        tbEdit.setCellFactory(tableColumEdit());

    }

//    private Node createPage(int pageIndex){
//        from = pageIndex * itemPage;
//        to = itemPage;
//        user_list.addAll(userDao.userListLimit(from,to));
//        tableView.setItems(user_list);
//        return  tableView;
//    }

    public void clickDelete(ActionEvent event) {
        User user = (User) tableView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("XÁC NHẬN");
        alert.setHeaderText("Bạn có chắc chắn muốn xóa");
        alert.setContentText("User : " + user.getUsername());
        ButtonType buttonTypeYes = new ButtonType("YES", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("NO", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeYes) {
            userDao.deleteUser(user.getUsername());
            user_list.removeAll((User) tableView.getSelectionModel().getSelectedItem());
        }

    }
    @FXML
    public void searchUser() {
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(user -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (user.getLast_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (user.getFirst_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (user.getUsername().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                return false;
            });
        });
        SortedList<User> userSortedList = new SortedList<>(filteredList);
        userSortedList.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(userSortedList);
    }

    // create edit in table
    public void editTable() {
        tbFirstName.setCellFactory(TextFieldTableCell.forTableColumn());
        tbFirstName.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setFirst_name(e.getNewValue());
        });
        tbLastName.setCellFactory(TextFieldTableCell.forTableColumn());
        tbLastName.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setLast_name(e.getNewValue());
        });
        tbEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        tbEmail.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setEmail(e.getNewValue());
        });
        tbTell.setCellFactory(TextFieldTableCell.forTableColumn());
        tbTell.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setPhone_number(e.getNewValue());
        });
        tbAddress.setCellFactory(TextFieldTableCell.forTableColumn());
        tbAddress.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setAddress(e.getNewValue());
        });
        tbGender.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setGender(e.getNewValue());
        });
    }

    // create button edit in table
    protected Callback tableColumEdit() {
        Callback<TableColumn<User, String>, TableCell<User, String>> cellCallback = (pagram) -> {
            final TableCell<User, String> tableCell = new TableCell<User, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        final Button button = new Button("Edit");
                        button.setOnAction(event -> {
                            User user = getTableView().getItems().get(getIndex());
                           userDao.updateUser(user);
                        });
                        setGraphic(button);
                        setText(null);
                    }
                }
            };
            return tableCell;
        };
        return cellCallback;
    }

}
