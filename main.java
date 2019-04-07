
import java.util.*;
import java.sql.*;
import javafx.application.*;

public class main {

    public static void main(String[] args) {
        
        loadDatabase();
    }

    public static void loadDatabase() {

        try{
        database.createTable(
          "CREATE TABLE IF NOT EXISTS Nutrients"
        + "("
        + "Nutrient_ID INT NOT NULL,"
        + "Protein FLOAT,"
        + "Carbs FLOAT,"
        + "Fat FLOAT,"
        + "PRIMARY KEY (Nutrient_ID)"
        + ")"
        );
        database.createTable("CREATE TABLE IF NOT EXISTS Person"
        + "("
        + "User_ID INT NOT NULL,"
        + "Username VARCHAR(20) NOT NULL,"
        + "Password VARCHAR(20) NOT NULL,"
        + "Friend_ID INT,"
        + "PRIMARY KEY (User_ID),"
        + "FOREIGN KEY (Friend_ID) REFERENCES Friends(Friend_ID)"
        + ")"
        );
        database.createTable("CREATE TABLE IF NOT EXISTS Daily_Input"
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
        + ")"
        );
        database.createTable("CREATE TABLE IF NOT EXISTS Activities"
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
        + ")"
        );
        database.createTable("CREATE TABLE IF NOT EXISTS Meals"
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
        + ")"
        );
        database.createTable("CREATE TABLE IF NOT EXISTS Human_Profile"
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
        + ")"
        );
        database.createTable("CREATE TABLE IF NOT EXISTS Medical_History"
        + "("
        + "Medical_ID VARCHAR(5) NOT NULL,"
        + "Check_Up_Date DATE,"
        + "Comments VARCHAR(500),"
        + "User_ID INT,"
        + "PRIMARY KEY (Medical_ID),"
        + "FOREIGN KEY (User_ID) REFERENCES Person(User_ID)"
        + ")"
        );
        database.createTable("CREATE TABLE IF NOT EXISTS Goals"
        + "("
        + "Goals_ID VARCHAR(5) NOT NULL,"
        + "Weight_Goals FLOAT,"
        + "Nutrient_Goals FLOAT,"
        + "Physical_Activity_Goals FLOAT,"
        + "User_ID INT NOT NULL,"
        + "PRIMARY KEY (Goals_ID),"
        + "FOREIGN KEY (User_ID) REFERENCES Person(User_ID)"
        + ")"
        );
        database.createTable("CREATE TABLE IF NOT EXISTS Friends"
        + "("
        + "Friend_ID INT NOT NULL,"
        + "User_ID INT NOT NULL,"
        + "PRIMARY KEY (Friend_ID),"
        + "FOREIGN KEY (User_ID) REFERENCES Person(User_ID)"
        + ");"
        );

        System.out.println("success");

        } catch(SQLException a) {

            System.out.println(a);
        }
    }
}