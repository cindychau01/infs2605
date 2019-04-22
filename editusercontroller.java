import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
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

public class editusercontroller implements Initializable {

    database database = new database();
    String loggedInID;
    String Age;
    String Height;
    String Mass;
    String Fatmass;
    String Leanmass;


    @FXML
    private Button banner;

    @FXML
    private Text title;

    @FXML
    private ImageView logo;

    @FXML
    private Button signout;

    @FXML
    private Spinner agespinner;

    @FXML
    private Button Dashboard;
   
    @FXML
    private TextField mass;

    @FXML
    private TextField height;

    @FXML
    private TextField fatmass;

    @FXML
    private TextField leanmass;

    @FXML
    private Button confirm;

    public  void setLoggedInID( String id) {
        this.loggedInID = id;
    }   

    @FXML
    void confirm(ActionEvent event) {

        Age = agespinner.getValue().toString();
        Mass = mass.getText();
        Height = height.getText();
        Fatmass = fatmass.getText();
        Leanmass = leanmass.getText();

        
        try { 
            database.updateProfile("Age", Age, loggedInID);
            database.updateProfile("Mass", Mass, loggedInID);
            database.updateProfile("Height", Height, loggedInID);
            database.updateProfile("Lean_Mass", Leanmass, loggedInID);
            database.updateProfile("Fat_Mass", Fatmass, loggedInID);
        } catch (SQLException z) {}    
        
        
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
        try{
            this.Age = database.selectAge(loggedInID, "Age");
            this.Height = database.selectQuery(loggedInID, "Height");
            this.Mass = database.selectQuery(loggedInID, "Mass");
            this.Fatmass = database.selectQuery(loggedInID, "Fat_Mass");
            this.Leanmass = database.selectQuery(loggedInID, "Lean_Mass");
        } catch (SQLException a) {}

        mass.setText(Mass);
        height.setText(Height);
        fatmass.setText(Fatmass);
        leanmass.setText(Leanmass);

        try {
            SpinnerValueFactory<Integer> ageValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,150, database.returnAgeInt(loggedInID, "Age"));
            agespinner.setValueFactory(ageValueFactory);
        } catch (SQLException b) {}

    }
}
