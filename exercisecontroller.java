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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ChoiceBox;



public class exercisecontroller implements Initializable {

    database database = new database();

    String loggedInID;
    String acttype;
    String actdes;
    String minutes;
    String calburnt;
    String reps;
    String gymatten;
    int countR;
    int countA;
    ArrayList<Integer> idList = new ArrayList<Integer>(); 
    ArrayList<Integer> minuteList = new ArrayList<Integer>(); 

    @FXML
    private PieChart chartavr;

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
    private ChoiceBox Activitytype;

    @FXML
    private TextArea Activitydesc;

    @FXML
    private TextField Minutestrained;

    @FXML
    private TextField Caloriesburnt;

    @FXML
    private TextField Reps;

    @FXML
    private ChoiceBox Gymattendance;

    @FXML
    private Button avrchart;

    @FXML
    private Button minutesspent;

    @FXML
    private LineChart<?, ?> linegraph;

    @FXML
    private Button add;

    @FXML
    void add(ActionEvent event) {

        acttype = Activitytype.getValue().toString();
        actdes = Activitydesc.getText();
        minutes = Minutestrained.getText();
        calburnt = Caloriesburnt.getText();
        reps = Reps.getText();
        gymatten = Gymattendance.getValue().toString();

        try {
            database.insertQuery("INSERT OR REPLACE INTO Activities(Activity_Type, Activity_Description, Minutes_Trained, Calories_Burnt, Reps, Gym_Attendance, User_ID) VALUES ('"
            + acttype + "', '" + actdes + "', '" + minutes + "', '" + calburnt + "', '" + reps + "', '" + gymatten + "', '" + loggedInID + "');");

        } catch (SQLException a) {

        }

    }

    public  void setLoggedInID( String id) {
        this.loggedInID = id;
    }  

    @FXML
    void showline(ActionEvent event) {
        try {
            idList = database.returnActivityID(loggedInID);
            minuteList = database.returnMinutes(loggedInID);
        } catch (SQLException a) {}

        XYChart.Series values = new XYChart.Series();
        
        for(int i = 0; i < idList.size(); i++) {
            values.getData().add(new XYChart.Data(Integer.toString(idList.get(i)), minuteList.get(i)));
        }

        linegraph.getData().add(values);
        linegraph.setVisible(true);
    }

    @FXML
    void showpie(ActionEvent event) {

        try{
            countR = database.returnCount(loggedInID, "Resistance");
            countA = database.returnCount(loggedInID, "Aerobic");

        } catch (SQLException a) {}

        ObservableList<PieChart.Data> data =
            FXCollections.observableArrayList(
                new PieChart.Data("Resistance", countR),
                new PieChart.Data("Aerobic", countA)
            );

        chartavr.setData(data);
        chartavr.setVisible(true);
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

        Activitytype.setItems(FXCollections.observableArrayList(
            "Aerobic", "Resistance"
        ));

        Gymattendance.setItems(FXCollections.observableArrayList(
            "Yes", "No"
        ));

        try{
            countR = database.returnCount(loggedInID, "Resistance");
            countA = database.returnCount(loggedInID, "Aerobic");

        } catch (SQLException a) {}

        ObservableList<PieChart.Data> data =
            FXCollections.observableArrayList(
                new PieChart.Data("Resistance", countR),
                new PieChart.Data("Aerobic", countA)
            );

        chartavr.setData(data);
        chartavr.setVisible(true);
        
        try {
            idList = database.returnActivityID(loggedInID);
            minuteList = database.returnMinutes(loggedInID);
        } catch (SQLException a) {}

        XYChart.Series values = new XYChart.Series();
        
        for(int i = 0; i < idList.size(); i++) {
            values.getData().add(new XYChart.Data(Integer.toString(idList.get(i)), minuteList.get(i)));
        }

        linegraph.getData().add(values);
        linegraph.setVisible(false);
       


    }
}
