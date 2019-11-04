package comhvktmm.at13.utils;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RedictUtils{
    public void Redict(String scene,String title , JFXButton btn) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(scene));
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void Redict(String scene,String title) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(scene));
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
    public void RedictHide(ActionEvent event , String scene, String title) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(scene));
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();

    }
}
