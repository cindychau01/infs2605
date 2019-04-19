
import java.util.*;
import java.sql.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.event.*;
import java.io.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;


public class app extends Application{

    public static void main(String[] args) {
        launch(args); 
    }

    @Override
    public void start(Stage primaryStage) throws IOException{

        database database = new database();

        try{
            database.createTable();
            database.insertDummyData();
            
        } catch (SQLException createTableException) {
            System.err.println(createTableException);
        }


        FXMLLoader loader = new FXMLLoader();
        LoginController controller = new LoginController();
        loader.setController(controller);
        loader.setLocation(getClass().getResource("Login.fxml"));

        Parent root = loader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}