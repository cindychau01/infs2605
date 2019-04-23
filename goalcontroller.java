import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
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

public class goalcontroller implements Initializable {

    database database = new database();
    String loggedInID;
    String BMI;
    String stepGoal;
    String weightGoal;
    String carbGoal;
    float mass;
    float height;


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
    private TextField newweightgoal;

    @FXML
    private TextField newcarbgoal;

    @FXML
    private TextField newstepgoal;

    @FXML
    private Button newgoals;

    @FXML
    private Text stepsremaining;

    @FXML
    private Text massremaining;

    @FXML
    private Text bmi;

    @FXML
    private Button calculatebmi;

    public  void setLoggedInID( String id) {
        this.loggedInID = id;
    }  

    @FXML
    void calcBMI(ActionEvent event) {

        try {
            mass = database.returnMassGoal(loggedInID);
            height = database.returnHeight(loggedInID);
        } catch (SQLException a) {}
        
        float fBMI = (mass / (height * height));

        bmi.setText(Float.toString(fBMI));

        bmi.setVisible(true);
    }

    @FXML
    void setnewgoals(ActionEvent event) {
        
        stepGoal = newstepgoal.getText();
        weightGoal = newweightgoal.getText();
        carbGoal = newcarbgoal.getText();

        try {
            database.updateStepGoal(loggedInID, stepGoal);

            database.updateMassGoal(loggedInID, weightGoal);

            database.updateCarbGoal(loggedInID, carbGoal);

            weightGoal = database.returnWeightGoal(loggedInID);
            stepGoal = database.returnStepsGoal(loggedInID);

            float currentSteps = database.returnStepGoal(loggedInID);
            float currentWeight = database.returnMassGoal(loggedInID);
            
            float mDiff;
            float sDiff;

            if (Float.parseFloat(weightGoal) > currentWeight) {
                mDiff = Float.parseFloat(weightGoal) - currentWeight;
            } else {
                mDiff = currentWeight - Float.parseFloat(weightGoal);
            }

            massremaining.setText(Float.toString(mDiff));

            if (Float.parseFloat(stepGoal) > currentSteps) {
                sDiff = Float.parseFloat(stepGoal) - currentSteps;
                stepsremaining.setText(Float.toString(sDiff));
            } else {
                stepsremaining.setText(weightGoal);
                database.deleteSteps(loggedInID);
            }

            stepsremaining.setVisible(true);
            massremaining.setVisible(true);
            
        } catch (SQLException a) {}


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

        try {
            weightGoal = database.returnWeightGoal(loggedInID);
            stepGoal = database.returnStepsGoal(loggedInID);

            float currentSteps = database.returnStepGoal(loggedInID);
            float currentWeight = database.returnMassGoal(loggedInID);
            
            float mDiff;
            float sDiff;

            if (Float.parseFloat(weightGoal) > currentWeight) {
                mDiff = Float.parseFloat(weightGoal) - currentWeight;
            } else {
                mDiff = currentWeight - Float.parseFloat(weightGoal);
            }

            massremaining.setText(Float.toString(mDiff));

            if (Float.parseFloat(stepGoal) > currentSteps) {
                sDiff = Float.parseFloat(stepGoal) - currentSteps;
                stepsremaining.setText(Float.toString(sDiff));
            } else {
                stepsremaining.setText("New Goal!");
                database.deleteSteps(loggedInID);
            }

            stepsremaining.setVisible(true);
            massremaining.setVisible(true);

        } catch (SQLException a) {}
    }

}
