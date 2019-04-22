import javafx.fxml.FXML;
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

public class dailycontroller implements Initializable {

    database database = new database();
    String loggedInID;
    String day;
    String mental;
    String stairs;
    String steps;
    String rhr;
    String sleephours;
    String overall;
    int countM1;
    int countM2;
    int countM3;
    ArrayList<Integer> idList = new ArrayList<Integer>(); 
    ArrayList<Integer> ratingList = new ArrayList<Integer>(); 
    
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
    private ChoiceBox Days;

    @FXML
    private ChoiceBox Mentalwellbeing;

    @FXML
    private TextField Stairs;

    @FXML
    private TextField Steps;

    @FXML
    private TextField Rhr;

    @FXML
    private TextField Sleephr;

    @FXML
    private ChoiceBox Overall;

    @FXML
    private Button Record;

    @FXML
    private PieChart Piemental;

    @FXML
    private LineChart<?, ?> lineoverall;

    @FXML
    private Button Generatepie;

    @FXML
    private Button Generateline;

    public  void setLoggedInID( String id) {
        this.loggedInID = id;
    }  

    @FXML
    void add(ActionEvent event) {
        
        day = Days.getValue().toString();
        overall = Overall.getValue().toString();
        mental = Mentalwellbeing.getValue().toString();
        stairs = Stairs.getText();
        steps = Steps.getText();
        rhr = Rhr.getText();
        sleephours = Sleephr.getText();

        
        try {
            database.insertQuery("INSERT INTO Daily_Input(Day, Stair_Count, Step_Count, Resting_Heart_Rate, Hours_Slept, Mental_Wellbeing, Overall, User_ID) VALUES ('"
            + day + "', '" + stairs + "', '" + steps + "', '" + rhr + "', '" + sleephours + "', '" + mental + "', '" + overall + "', '" + loggedInID + "');"
            );
        } catch (SQLException a) {}
        
       

    }

    @FXML
    void genline(ActionEvent event) {

        try{
            idList = database.returnDailyID(loggedInID);
            ratingList = database.returnRating(loggedInID);

        } catch (SQLException c) {}


        XYChart.Series values = new XYChart.Series();
        
        for(int i = 0; i < idList.size(); i++) {
            values.getData().add(new XYChart.Data(Integer.toString(idList.get(i)), ratingList.get(i)));
        }

        lineoverall.getData().add(values);
        lineoverall.setVisible(true);
    }

    @FXML
    void mentalgraph(ActionEvent event) {
        try {
            countM1 = database.sumColumnDaily("Mental_Wellbeing", "1", loggedInID);
            countM2 = database.sumColumnDaily("Mental_Wellbeing", "2", loggedInID);
            countM3 = database.sumColumnDaily("Mental_Wellbeing", "3", loggedInID);
        } catch (SQLException a) {}

        ObservableList<PieChart.Data> data =
            FXCollections.observableArrayList(
                new PieChart.Data("Rating 1", countM1),
                new PieChart.Data("Rating 2", countM2),
                new PieChart.Data("Rating 3", countM3)
            );

        Piemental.setData(data);
        Piemental.setVisible(true);
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
     
        Days.setItems(FXCollections.observableArrayList(
            "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
        ));

        Overall.setItems(FXCollections.observableArrayList(
            "1", "2", "3", "4", "5"
        ));

        Mentalwellbeing.setItems(FXCollections.observableArrayList(
            "1", "2", "3"
        ));

    }

}
