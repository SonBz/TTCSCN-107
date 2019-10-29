package com.hvktmm.at13.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/Home.fxml"));
        primaryStage.setTitle("Quản Lý Bán Sữa");
        Scene scene = new Scene(root, 982, 615);
        primaryStage.setScene(scene);
//        scene.getStylesheets().add(getClass().getResource("/style/styles.css").toExternalForm());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
