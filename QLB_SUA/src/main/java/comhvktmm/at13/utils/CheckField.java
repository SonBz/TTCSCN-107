package comhvktmm.at13.utils;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CheckField {

    public void checkString(JFXTextField txtCheck) throws FileNotFoundException {
        RequiredFieldValidator validator = new RequiredFieldValidator();
        txtCheck.getValidators().add(validator);
        validator.setMessage("Bắt buộc");
        txtCheck.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    txtCheck.validate();
                }
            }
        });
        Image icon = new Image(new FileInputStream("E:/Thuc_tap_cs/TTCSCN/TTCSCN-107/QLB_SUA/src/main/resources/img/warning.png"));
        validator.setIcon(new ImageView(icon));

    }
    public void checkInt(JFXTextField txtCheck) throws FileNotFoundException {
        NumberValidator validator = new NumberValidator();
        txtCheck.getValidators().add(validator);
        validator.setMessage("Sai định dạng");
        txtCheck.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    txtCheck.validate();
                }
            }
        });
        Image icon = new Image(new FileInputStream("E:/Thuc_tap_cs/TTCSCN/TTCSCN-107/QLB_SUA/src/main/resources/img/warning.png"));
        validator.setIcon(new ImageView(icon));

    }
}
