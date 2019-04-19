
import java.sql.*;
import java.util.*;

public class database {

    protected Connection connection = null;

    public void openConnection() {
        try {
            // connection = DriverManager.getConnection("jdbc:sqlite:/Volumes/Macintosh HD/Users/WildSnaill/Desktop/INFS2605/app.db");
            // connection = DriverManager.getConnection("jdbc:sqlite:/Volumes/Macintosh HD/Users/Qibinyu/Desktop/INFS2605s/app.db");
            connection = DriverManager.getConnection("jdbc:sqlite:/Volumes/Macintosh HD/Users/nicholasliang/Desktop/2605/app.db");
        } catch (SQLException failedConnection) {
            System.err.println(failedConnection);
        }
    }

    public void createTable() throws SQLException{
        
        openConnection();
        // Statement to insert tables
        Statement s = connection.createStatement();

        // Create tables
        s.execute("CREATE TABLE IF NOT EXISTS Nutrients"
        + "("
        + "Nutrient_ID INTEGER PRIMARY KEY,"
        + "Protein FLOAT,"
        + "Carbs FLOAT,"
        + "Fat FLOAT"
        + ")");

        s.execute("CREATE TABLE IF NOT EXISTS Person"
        + "("
        + "User_ID INTEGER PRIMARY KEY,"
        + "Username VARCHAR(20) NOT NULL,"
        + "Password VARCHAR(20) NOT NULL"
        + ")");

        s.execute("CREATE TABLE IF NOT EXISTS Daily_Input"
        + "("
        + "Daily_ID INTEGER PRIMARY KEY,"
        + "Day VARCHAR(10),"
        + "Stair_Count INTEGER,"
        + "Step_Count INTEGER,"
        + "Resting_Heart_Rate FLOAT,"
        + "Hours_Slept INTEGER,"
        + "Mental_Wellbeing INTEGER,"
        + "User_ID INTEGER NOT NULL,"
        + "FOREIGN KEY (User_ID) REFERENCES Person(User_ID)"
        + ")");

        s.execute("CREATE TABLE IF NOT EXISTS Activities"
        + "("
        + "Activity_ID INTEGER PRIMARY KEY,"
        + "Activity_Type VARCHAR(10),"
        + "Minutes_Trained INTEGER,"
        + "Calories_Burnt INTEGER,"
        + "Reps INTEGER,"
        + "Gym_Attendance VARCHAR(3),"
        + "User_ID INTEGER NOT NULL,"
        + "FOREIGN KEY (User_ID) REFERENCES Person(User_ID)"
        + ")");

        s.execute("CREATE TABLE IF NOT EXISTS Meals"
        + "("
        + "Meal_ID INTEGER PRIMARY KEY,"
        + "Meal_Name INTEGER,"
        + "Calories_Consumed INTEGER,"
        + "Date_Consumed DATE,"
        + "Nutrient_ID INTEGER,"
        + "User_ID INTEGER NOT NULL,"
        + "FOREIGN KEY (Nutrient_ID) REFERENCES Nutrients(Nutrient_ID),"
        + "FOREIGN KEY (User_ID) REFERENCES Person(User_ID)"
        + ")");

        s.execute("CREATE TABLE IF NOT EXISTS Human_Profile"
        + "("
        + "Profile_ID INTEGER PRIMARY KEY,"
        + "Age INTEGER,"
        + "Mass FLOAT,"
        + "Height FLOAT,"
        + "Lean_Mass FLOAT,"
        + "Fat_Mass FLOAT,"
        + "User_ID INTEGER NOT NULL,"
        + "FOREIGN KEY (User_ID) REFERENCES Person(User_ID)"
        + ")");

        s.execute("CREATE TABLE IF NOT EXISTS Medical_History"
        + "("
        + "Medical_ID INTEGER PRIMARY KEY,"
        + "Check_Up_Date DATE,"
        + "Comments VARCHAR(500),"
        + "User_ID INTEGER,"
        + "FOREIGN KEY (User_ID) REFERENCES Person(User_ID)"
        + ")");

        s.execute("CREATE TABLE IF NOT EXISTS Goals"
        + "("
        + "Goals_ID INTEGER PRIMARY KEY,"
        + "Weight_Goals FLOAT,"
        + "Nutrient_Goals FLOAT,"
        + "Physical_Activity_Goals FLOAT,"
        + "User_ID INTEGER NOT NULL,"
        + "FOREIGN KEY (User_ID) REFERENCES Person(User_ID)"
        + ")");

        s.close();
        connection.close();
    }

    public void insertDummyData() throws SQLException {

        openConnection();

        Statement s = connection.createStatement();

        s.execute("INSERT OR REPLACE INTO Person(Username, Password) VALUES ( 'nicky951', 'admin');");
        s.execute("INSERT OR REPLACE INTO Person(Username, Password) VALUES ( 'tonyN', 'admin');");
        s.execute("INSERT OR REPLACE INTO Person(Username, Password) VALUES ( 'jasenY', 'admin');");
        
        s.close();
        connection.close();
    } 

    public void insertQuery(String userInsertStatement)  throws SQLException{

        openConnection();

        Statement s = connection.createStatement();

        s.execute(userInsertStatement);

        s.close();
        connection.close();
    }

    public boolean loginQuery(String userLogin, String userPassword) throws SQLException{
        
        openConnection();

        boolean login = false;

        Statement s = connection.createStatement();

        String user;
        String password;

        try {
            ResultSet checkLogin = s.executeQuery("SELECT Username, Password FROM Person WHERE Username = '" + userLogin + "' AND Password = '" + userPassword + "';");

            user = checkLogin.getString("Username");
            password = checkLogin.getString("Password");
        
            if((user.equals(userLogin)) && (password.equals(userPassword))) {
                login = true;
            }

        } catch (SQLException noResult) {
            login = false;   
        }
        
        return login;
    }
}   