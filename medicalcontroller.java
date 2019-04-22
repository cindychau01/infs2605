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
import javafx.scene.control.TextArea;


public class medicalcontroller implements Initializable {

    database database = new database();
    String loggedInID;
    String dateSeen;
    String details;
    
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
    private DatePicker date;

    @FXML
    private TextArea comment;

    @FXML
    private Button record;

    public  void setLoggedInID( String id) {
        this.loggedInID = id;
    }  

    @FXML
    void record(ActionEvent event) {

        dateSeen = date.getValue().toString();
        details = comment.getText();

        try{
            database.insertMed(dateSeen, details, loggedInID);
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

    }

}