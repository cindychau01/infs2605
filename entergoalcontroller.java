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

public class entergoalcontroller implements Initializable {

    database database = new database();

    String id;
    String Weightgoal;
    String Caloriegoal;
    String Stepcountgoal;

    @FXML
    private Pane entergoals;

    @FXML
    private Text title;

    @FXML
    private ImageView logo;

    @FXML
    private Button finishaccount;

    @FXML
    private TextField weightgoal;

    @FXML
    private TextField caloriegoal;

    @FXML
    private TextField stepgoal;

    @FXML
    void finishup(ActionEvent event) {

        this.Caloriegoal = caloriegoal.getText();
        this.Weightgoal = weightgoal.getText();
        this.Stepcountgoal = stepgoal.getText();

        try {
            database.insertQuery("INSERT INTO Goals(Weight_Goals, Nutrient_Goals, Steps_Goals, User_ID) VALUES ('" + Weightgoal + "', '" + Caloriegoal + "', '" + Stepcountgoal + "', '" + id + "');");
        } catch (SQLException a) {}

        Stage nextStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                FXMLLoader login = new FXMLLoader();
        
                dashboardcontroller controller = new dashboardcontroller();
                
                controller.setLoggedInID(id);
                
                login.setController(controller);
                login.setLocation(getClass().getResource("dashboard.fxml"));
        
                try{
                    Parent root = login.load();
                    nextStage.setScene(new Scene(root));
                    nextStage.show();
                } catch (IOException e) {
        
                }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourcebundle) {

    }

    public void setID(String newID) {
        this.id = newID;

    }
}
