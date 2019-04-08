
import java.util.*;
import java.sql.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.event.*;
import java.io.*;
import javafx.fxml.FXML;


public class app extends Application{

    public static void main(String[] args) {
        launch(args); 
    }

    
    public void start(Stage primaryStage) throws IOException{

        database database = new database();

        try{
            database.createTable();
            System.out.println("Created Tables");

            database.insertDummyData();
            System.out.println("Inserted Data");
            
        } catch (SQLException createTableException) {
            System.err.println(createTableException);
        }

        try {
            Platform.exit();
        } catch (Exception exitException) {
            System.err.println(exitException);
        }
    }
}