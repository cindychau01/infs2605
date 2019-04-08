
import java.sql.*;
import java.util.*;

public class database {

    protected Connection connection = null;

    public void createTable() throws SQLException{
        
        // Create connection
        connection = DriverManager.getConnection("jdbc:sqlite:/Volumes/Macintosh HD/Users/nicholasliang/Desktop/2605/app.db");

        // Statement to insert tables
        Statement s = connection.createStatement();

        // Create tables
        s.execute("CREATE TABLE IF NOT EXISTS Nutrients"
        + "("
        + "Nutrient_ID VARCHAR(5) NOT NULL,"
        + "Protein FLOAT,"
        + "Carbs FLOAT,"
        + "Fat FLOAT,"
        + "PRIMARY KEY (Nutrient_ID)"
        + ")");

        s.execute("CREATE TABLE IF NOT EXISTS Person"
        + "("
        + "User_ID INT NOT NULL,"
        + "Username VARCHAR(20) NOT NULL,"
        + "Password VARCHAR(20) NOT NULL,"
        + "Friend_ID INT,"
        + "PRIMARY KEY (User_ID),"
        + "FOREIGN KEY (Friend_ID) REFERENCES Friends(Friend_ID)"
        + ")");

        s.execute("CREATE TABLE IF NOT EXISTS Daily_Input"
        + "("
        + "Daily_ID VARCHAR(5) NOT NULL,"
        + "Day VARCHAR(10),"
        + "Stair_Count INT,"
        + "Step_Count INT,"
        + "Resting_Heart_Rate FLOAT,"
        + "Hours_Slept INT,"
        + "Mental_Wellbeing INT,"
        + "User_ID INT NOT NULL,"
        + "PRIMARY KEY (Daily_ID),"
        + "FOREIGN KEY (User_ID) REFERENCES Person(User_ID)"
        + ")");

        s.execute("CREATE TABLE IF NOT EXISTS Activities"
        + "("
        + "Activity_ID VARCHAR(5) NOT NULL,"
        + "Activity_Type VARCHAR(10),"
        + "Minutes_Trained INT,"
        + "Calories_Burnt INT,"
        + "Reps INT,"
        + "Gym_Attendance VARCHAR(3),"
        + "User_ID INT NOT NULL,"
        + "PRIMARY KEY (Activity_ID),"
        + "FOREIGN KEY (User_ID) REFERENCES Person(User_ID)"
        + ")");

        s.execute("CREATE TABLE IF NOT EXISTS Meals"
        + "("
        + "Meal_ID VARCHAR(5) NOT NULL,"
        + "Meal_Name INT,"
        + "Calories_Consumed INT,"
        + "Date_Consumed DATE,"
        + "Nutrient_ID INT,"
        + "User_ID INT NOT NULL,"
        + "PRIMARY KEY (Meal_ID),"
        + "FOREIGN KEY (Nutrient_ID) REFERENCES Nutrients(Nutrient_ID),"
        + "FOREIGN KEY (User_ID) REFERENCES Person(User_ID)"
        + ")");

        s.execute("CREATE TABLE IF NOT EXISTS Human_Profile"
        + "("
        + "Profile_ID VARCHAR(5) NOT NULL,"
        + "Age INT,"
        + "Mass FLOAT,"
        + "Height FLOAT,"
        + "Lean_Mass FLOAT,"
        + "Fat_Mass FLOAT,"
        + "User_ID INT NOT NULL,"
        + "PRIMARY KEY (Profile_ID),"
        + "FOREIGN KEY (User_ID) REFERENCES Person(User_ID)"
        + ")");

        s.execute("CREATE TABLE IF NOT EXISTS Medical_History"
        + "("
        + "Medical_ID VARCHAR(5) NOT NULL,"
        + "Check_Up_Date DATE,"
        + "Comments VARCHAR(500),"
        + "User_ID INT,"
        + "PRIMARY KEY (Medical_ID),"
        + "FOREIGN KEY (User_ID) REFERENCES Person(User_ID)"
        + ")");

        s.execute("CREATE TABLE IF NOT EXISTS Goals"
        + "("
        + "Goals_ID VARCHAR(5) NOT NULL,"
        + "Weight_Goals FLOAT,"
        + "Nutrient_Goals FLOAT,"
        + "Physical_Activity_Goals FLOAT,"
        + "User_ID INT NOT NULL,"
        + "PRIMARY KEY (Goals_ID),"
        + "FOREIGN KEY (User_ID) REFERENCES Person(User_ID)"
        + ")");

        s.execute("CREATE TABLE IF NOT EXISTS Friends"
        + "("
        + "Friend_ID INT NOT NULL,"
        + "User_ID INT NOT NULL,"
        + "PRIMARY KEY (Friend_ID),"
        + "FOREIGN KEY (User_ID) REFERENCES Person(User_ID)"
        + ");"
        );
        
        s.close();
        connection.close();
    }

    public void insertDummyData() throws SQLException {

        connection = DriverManager.getConnection("jdbc:sqlite:/Volumes/Macintosh HD/Users/nicholasliang/Desktop/2605/app.db");

        Statement s = connection.createStatement();

        //ID, Protein, Carbs, Fat,
        s.execute("INSERT OR REPLACE INTO Nutrients VALUES ('N1', '10.5', '27', '2.5');");
        s.execute("INSERT OR REPLACE INTO Nutrients VALUES ('N2', '15.2', '50.1', '50.5');");
        s.execute("INSERT OR REPLACE INTO Nutrients VALUES ('N3', '7.6', '23.5', '100.5');");
        s.execute("INSERT OR REPLACE INTO Nutrients VALUES ('N4', '18.4', '34', '2.3');");
        s.execute("INSERT OR REPLACE INTO Nutrients VALUES ('N5', '20.1', '19.8', '5.5');");
        s.execute("INSERT OR REPLACE INTO Nutrients VALUES ('N6', '8.5', '57.9', '24.2');");
        s.execute("INSERT OR REPLACE INTO Nutrients VALUES ('N7', '11', '21.1', '4.1');");
        s.execute("INSERT OR REPLACE INTO Nutrients VALUES ('N8', '12.3', '27.5', '3.55');");
        s.execute("INSERT OR REPLACE INTO Nutrients VALUES ('N9', '3.1', '27.8', '19.5');");
        s.execute("INSERT OR REPLACE INTO Nutrients VALUES ('N10', '31.8', '22', '33.3');");
        
        s.close();
        connection.close();
    } 
}