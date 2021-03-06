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

public class myprofilecontroller implements Initializable {

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
    private ImageView logo;

    @FXML
    private Button Dashboard;

    @FXML
    private Text title;

    @FXML
    private Button signout;

    @FXML
    private ImageView personicon;

    @FXML
    private Text age;

    @FXML
    private Text fatmass;

    @FXML
    private Text leanmass;

    @FXML
    private Text height;

    @FXML
    private Text mass;

    @FXML
    private Text Profileage;

    @FXML
    private Text Profilemass;

    @FXML
    private Text Profileheight;

    @FXML
    private Text Profileleanmass;

    @FXML
    private Text Profilefatmass;

    @FXML
    private Button edit;

    
    public  void setLoggedInID( String id) {
        this.loggedInID = id;
    }   

    @FXML
    void edit(ActionEvent event) {
        
        Stage nextStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader editProfile = new FXMLLoader();

        editusercontroller controller = new editusercontroller();
        controller.setLoggedInID(loggedInID);
        editProfile.setController(controller);
        editProfile.setLocation(getClass().getResource("edituser.fxml"));

        try{
            Parent root = editProfile.load();
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

        age.setText(Age);
        fatmass.setText(Fatmass);
        leanmass.setText(Leanmass);
        height.setText(Height);
        mass.setText(Mass);
        
    }

    
}
