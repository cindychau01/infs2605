import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import java.sql.*;
import javafx.fxml.Initializable;
import java.util.*;
import java.io.IOException;
import java.net.*;
import javafx.scene.*;
import javafx.stage.*;

public class firstmealcontroller implements Initializable {

    database database = new database();

    String meal;
    String calories;
    String carbs;
    String protein;
    String fats;
    String date;
    String id;
    String nutID;


    @FXML
    private Pane enter1stmeal;

    @FXML
    private Text title;

    @FXML
    private ImageView logo;

    @FXML
    private TextField Mealname;

    @FXML
    private TextField Calories;

    @FXML
    private TextField Protein;

    @FXML
    private TextField Carbs;

    @FXML
    private TextField Fats;

    @FXML
    private TextField Date;

    @FXML
    private Button finish;

    @FXML
    void createuser(ActionEvent event) {

        this.meal = Mealname.getText();
        this.calories = Calories.getText();
        this.carbs = Carbs.getText();
        this.protein = Protein.getText();
        this.fats = Fats.getText();
        this.date = Date.getText();

        try {
            database.insertQuery("INSERT INTO Nutrients(Protein, Carbs, Fat) VALUES ('" + protein + "', '" + carbs + "', '" + fats + "');");

            nutID = database.returnNutID();

            database.insertQuery("INSERT INTO Meals (Meal_Name, Calories_Consumed, Date_Consumed, Nutrient_ID, User_ID) VALUES ('" + meal + "', '" + calories + "', '" + date + "', '" + nutID + "', '" + id + "');");
                
        } catch (SQLException a) {}


        Stage nextStage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        FXMLLoader firstGoal = new FXMLLoader();

        entergoalcontroller controller = new entergoalcontroller();
        controller.setID(id);
        firstGoal.setController(controller);
                firstGoal.setLocation(getClass().getResource("entergoals.fxml"));

                try{
                    Parent root = firstGoal.load();
                    nextStage.setScene(new Scene(root));
                    nextStage.show();
                } catch (IOException e) {

                }
    }

    public void setID(String newID) {
        this.id = newID;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourcebundle) {

    }
}
