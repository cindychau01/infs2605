import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.event.ActionEvent;
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
import javafx.collections.*;
import javafx.scene.control.DatePicker;

public class mealcontroller implements Initializable {

    database database = new database();

    String loggedInID;
    String meal;
    String calories;
    String carbs;
    String protein;
    String fats;
    String date;
    String nutID;
    Float totalP;
    Float totalC;
    Float totalF;

    @FXML
    private Button banner;

    @FXML
    private Button Dashboard;

    @FXML
    private Text title;

    @FXML
    private ImageView logo;

    @FXML
    private Button signout;

    @FXML
    private TextField Mealname;

    @FXML
    private TextField Calories;

    @FXML
    private DatePicker Date;

    @FXML
    private TextField Protein;

    @FXML
    private TextField Fats;

    @FXML
    private TextField Carbs;

    @FXML
    private Button add;

    @FXML
    private Button chart;

    @FXML
    private PieChart sumchart;

    @FXML
    private Text msg;

    public void setLoggedInID( String id) {
        this.loggedInID = id;
    }   

    @FXML
    void add(ActionEvent event) {

        this.meal = Mealname.getText();
        this.calories = Calories.getText();
        this.carbs = Carbs.getText();
        this.protein = Protein.getText();
        this.fats = Fats.getText();
        this.date = Date.getValue().toString();

        try {
            database.insertQuery("INSERT INTO Nutrients(Protein, Carbs, Fat) VALUES ('" + protein + "', '" + carbs + "', '" + fats + "');");

            nutID = database.returnNutID();

            database.insertQuery("INSERT INTO Meals (Meal_Name, Calories_Consumed, Date_Consumed, Nutrient_ID, User_ID) VALUES ('" + meal + "', '" + calories + "', '" + date + "', '" + nutID + "', '" + loggedInID + "');");
              
        } catch (SQLException a) {}
    }


    @FXML
    void chart(ActionEvent event) {

        try {
            totalP = database.sumNutrient("Protein");
            totalF = database.sumNutrient("Fat");
            totalC = database.sumNutrient("Carbs");
        } catch (SQLException b) {}

        ObservableList<PieChart.Data> data =
        FXCollections.observableArrayList(
            new PieChart.Data("Fats", totalF),
            new PieChart.Data("Carbs", totalC),
            new PieChart.Data("Protein", totalP)
        );
        
        sumchart.setData(data);
        sumchart.setVisible(true);
        msg.setVisible(true);
    }

    @FXML
    void signout(ActionEvent event) {

        Stage nextStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader signout = new FXMLLoader();

        LoginController controller = new LoginController();
        
        signout.setController(controller);
        signout.setLocation(getClass().getResource("Login.fxml"));

        try{
            Parent root = signout.load();
            nextStage.setScene(new Scene(root));
            nextStage.show();
        } catch (IOException e) {

        }
    }

    @FXML
    void todashboard(ActionEvent event) {

        Stage nextStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader dashboard = new FXMLLoader();

        dashboardcontroller controller = new dashboardcontroller();
        
        controller.setLoggedInID(loggedInID);
        dashboard.setController(controller);
        dashboard.setLocation(getClass().getResource("dashboard.fxml"));

        try{
            Parent root = dashboard.load();
            nextStage.setScene(new Scene(root));
            nextStage.show();
        } catch (IOException e) {

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourcebundle) {
        sumchart.setVisible(false);
        msg.setVisible(false);
    }
}
