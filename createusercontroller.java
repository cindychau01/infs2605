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
    String newAge;
    String newHeight;
    String newMass;
    String newLeanmass;
    String newFatmass;
    String newUser_ID;

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
    private ImageView logo;

    @FXML
    private TextField age;

    @FXML
    private TextField height;

    @FXML
    private TextField mass;

    @FXML
    private TextField leanmass;

    @FXML
    private TextField fatmass;


    @FXML
    void createuser(ActionEvent event) {
        
        newUser = username.getText();
        newPass = pass.getText();
        cPassword = cPass.getText();
        newFname = fName.getText();
        newLname = lName.getText();
        newAge = age.getText();
        newHeight = height.getText();
        newMass = mass.getText();
        newLeanmass = leanmass.getText();
        newFatmass = fatmass.getText();
        
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

                    newUser_ID = database.checkUserID(newUser);

                } catch (SQLException   a){
                    System.out.println(a);
                }

                try {
                    database.insertQuery("INSERT OR REPLACE INTO Human_Profile(Age, Mass, Height, Lean_Mass, Fat_Mass, User_ID) VALUES ( '" + newAge + "', '" + newMass + "', '" + newHeight + "', '" + newLeanmass  + "', '" +  newFatmass + "', '" +  newUser_ID + "');");
                } catch(SQLException c) {}
            }
        
    }

    @Override
    public void initialize(URL url, ResourceBundle resourcebundle) {
        
        errorsign.setVisible(false);
        error.setVisible(false);
     
    }

}
