package comhvktmm.at13.utils;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RedictUtils {
    public void Redict(String scene,String title , JFXButton btn) throws Exception {
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(scene));
        Parent root = FXMLLoader.load(getClass().getResource(scene));
//        Parent parent= fxmlLoader.load();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
