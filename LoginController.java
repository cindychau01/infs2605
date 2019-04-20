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




public class LoginController implements Initializable {

    database database = new database();
    String username;
    String pass;

    @FXML
    private Pane Loginpage;

    @FXML
    private TextField user;

    @FXML
    private TextField password;

    @FXML
    private Button loginbutton;

    @FXML
    private Text title;

    @FXML
    private Line Userline;

    @FXML
    private Line passwordline;

    @FXML
    private Text signupmessage;

    @FXML
    private Button signup;

    @FXML
    private ImageView errorsign;

    @FXML
    private Text Errormessage;

    

    @FXML
    void loginclick(ActionEvent event) {

        this.username = user.getText();
        this.pass = password.getText();

        try{
            boolean loginStatus = database.loginQuery(username, pass);

            if(loginStatus == true) {
                System.out.println(username + " logged in");
                errorsign.setVisible(false);
                Errormessage.setVisible(false);
            } else {
                System.out.println("failed login");
                errorsign.setVisible(true);
                Errormessage.setVisible(true);   
            }
        } catch (SQLException loginException) {
            System.err.println(loginException);
        }

        
    }

    @FXML
    void signupclick(ActionEvent event) {

        Stage nextStage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        FXMLLoader createUser = new FXMLLoader();
        
        createusercontroller controller = new createusercontroller();

        createUser.setController(controller);
        createUser.setLocation(getClass().getResource("createuser.fxml"));

        try{
            Parent root = createUser.load();
            nextStage.setScene(new Scene(root));
            nextStage.show();
        } catch (IOException e) {

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourcebundle) {
        errorsign.setVisible(false);
        Errormessage.setVisible(false);
    }

    public String getName() {
        return this.username;
    }

}
