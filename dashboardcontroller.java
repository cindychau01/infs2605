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

public class dashboardcontroller implements Initializable {

    database database = new database();
    String loggedInID;
    String weightGoal;
    String nutrientGoal;
    String stepCountGoal;
    String protein;
    String carbs;
    String fats;
    

    @FXML
    private Pane Dashboard;

    @FXML
    private Button banner;

    @FXML
    private ImageView logo;

    @FXML
    private Button Profile;

    @FXML
    private Button Meals;

    @FXML
    private Button Exercise;

    @FXML
    private Button Goals;

    @FXML
    private Button Medical;

    @FXML
    private Button Dailyinput;

    @FXML
    private Text title;

    @FXML
    private PieChart Nutrientbreakdownpie;

    @FXML
    private Text Nutrientbreakdown;

    @FXML
    private Text Yourgoals;

    @FXML
    private Text Weightgoals;

    @FXML
    private Text Stepcount;

    @FXML
    private Text Nutrientgoals;

    @FXML
    private Text weightgoalresult;

    @FXML
    private Text nutrientgoalresult;

    @FXML
    private Text stepcountresult;

    @FXML
    private Text Nutrientbreakdown1;


    @FXML
    private Button signout;

    public  void setLoggedInID( String id) {
        this.loggedInID = id;
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
    void toprofile(ActionEvent event) {
        
        Stage nextStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader toProfile = new FXMLLoader();

        myprofilecontroller controller = new myprofilecontroller();
        
        controller.setLoggedInID(loggedInID);

        toProfile.setController(controller);
        toProfile.setLocation(getClass().getResource("userprofile.fxml"));

        try{
            Parent root = toProfile.load();
            nextStage.setScene(new Scene(root));
            nextStage.show();
        } catch (IOException e) {

        }
    }

    @FXML
    void toexercise(ActionEvent event) {

    }

    @FXML
    void togoals(ActionEvent event) {

    }

    @FXML
    void toinput(ActionEvent event) {

    }

    @FXML
    void tomeal(ActionEvent event) {

        Stage nextStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader meal = new FXMLLoader();

        mealcontroller controller  = new mealcontroller();
        controller.setLoggedInID(loggedInID);
        meal.setController(controller);
        meal.setLocation(getClass().getResource("meal.fxml"));

        try{
            Parent root = meal.load();
            nextStage.setScene(new Scene(root));
            nextStage.show();
        } catch (IOException e) {

        }
    }

    @FXML
    void tomedical(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourcebundle) {


        try {
            weightGoal = database.returnWeightGoal(loggedInID);
            weightgoalresult.setText(weightGoal + " KG");

            nutrientGoal = database.returnNutrientGoal(loggedInID);
            nutrientgoalresult.setText(nutrientGoal + " Cal.");

            stepCountGoal = database.returnStepsGoal(loggedInID);
            stepcountresult.setText(stepCountGoal + " Steps");

        } catch (SQLException a) {}

        try{
        
            protein = (database.pieChartProtein(loggedInID));  
            carbs = (database.pieChartCarbs(loggedInID));
            fats = (database. pieChartFats(loggedInID));

        } catch (SQLException b) {}

        
        Float fProtein = Float.parseFloat(protein);
        Float fFat = Float.parseFloat(fats);
        Float fCarbs = Float.parseFloat(carbs);
        
        ObservableList<PieChart.Data> data =
            FXCollections.observableArrayList(
                new PieChart.Data("Fats", fFat),
                new PieChart.Data("Carbs", fCarbs),
                new PieChart.Data("Protein", fProtein)
            );

        Nutrientbreakdownpie.setData(data);

    }


}
