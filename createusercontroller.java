import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import java.sql.*;
import javafx.fxml.Initializable;
import java.util.*;
import java.net.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.control.Button;

public class createusercontroller implements Initializable{

    database database = new database();

    String newUser;
    String newPass;
    String cPassword;
    String newFname;
    String newLname;

    @FXML
    private Pane createuser;

    @FXML
    private TextField username;

    @FXML
    private TextField fName;

    @FXML
    private TextField lName;

    @FXML
    private TextField pass;

    @FXML
    private TextField cPass;

    @FXML
    private Button next;

    @FXML
    private Text title;

    @FXML
    private ImageView errorsign;

    @FXML
    private Text error;

    @FXML
    void createuser(ActionEvent event) {
        
        newUser = username.getText();
        newPass = pass.getText();
        cPassword = cPass.getText();
        newFname = fName.getText();
        newLname = lName.getText();
        
        boolean errormsg = false;

        if(cPassword.equals(newPass)) {
            errorsign.setVisible(false);
            error.setVisible(false);
            errormsg = false;
        } else {
            errorsign.setVisible(true);
            error.setVisible(true);
            errormsg = true;
        }
        
        
            if(errormsg == false) {
                try{
                    boolean exists = database.checkUserExists(newUser);

                    if(exists == true) {
                        errorsign.setVisible(true);
                        error.setVisible(true);
                        errormsg = true;
                    } else {
                        errorsign.setVisible(false);
                        error.setVisible(false);
                        errormsg = false;
                    }
                } catch (SQLException a) {

                }
            }

            if(errormsg == false) {
                try {
                    database.insertQuery("INSERT OR REPLACE INTO Person(Username, Password, Fname, Lname) VALUES ( '" + newUser + "', '" + newPass + "', '" + newFname + "', '" + newLname + "');");
                    System.out.println("inserted");
                } catch (SQLException   a){
                    System.out.println(a);
                }
            }
        
    }

    @Override
    public void initialize(URL url, ResourceBundle resourcebundle) {
        
        errorsign.setVisible(false);
        error.setVisible(false);
     
    }

}
