import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import java.util.concurrent.TimeUnit;
import java.sql.*;
import javafx.fxml.Initializable;
import java.util.*;
import java.net.*;



public class LoginController implements Initializable {

    database database = new database();
    

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

        String username = user.getText();
        String pass = password.getText();
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

    }

    @Override
    public void initialize(URL url, ResourceBundle resourcebundle) {
        errorsign.setVisible(false);
        Errormessage.setVisible(false);
    }

}
