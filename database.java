
import java.sql.*;
import java.util.*;

public class database {

    protected Connection connection = null;

    public void openConnection() {
        try {
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
        + "Friend_ID VARCHAR(5),"
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
        + "Friend_ID VARCHAR(5) NOT NULL,"
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

        //id, username, password, friendid
        s.execute("INSERT OR REPLACE INTO Person VALUES('1', 'nicky951', 'admin', 'f1')");
        s.execute("INSERT OR REPLACE INTO Person VALUES('2', 'tonyN', 'cabra', 'f2')");
        s.execute("INSERT OR REPLACE INTO Person VALUES('3', 'jasenY', 'trinity', 'f3')");
        s.execute("INSERT OR REPLACE INTO Person VALUES('4', 'ari', 'infs2605', 'f4')");
        s.execute("INSERT OR REPLACE INTO Person VALUES('5', 'justinY', 'doofdoof', 'f5')");
        s.execute("INSERT OR REPLACE INTO Person VALUES('6', 'max', 'coding', 'f6')");
        s.execute("INSERT OR REPLACE INTO Person VALUES('7', 'pat999', 'IAG', 'f7')");
        s.execute("INSERT OR REPLACE INTO Person VALUES('8', 'muzkan', 'mumskan', 'f8')");
        s.execute("INSERT OR REPLACE INTO Person VALUES('9', 'larry121', 'bitsahr', 'f9')");
        s.execute("INSERT OR REPLACE INTO Person VALUES('10', 'lipu', 'curry11', 'f10')");

        //dailyid, day, staircount, stepcount, restheartrate, hoursslept, mental wellbeing, userid
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d1', 'Monday', '100', '10000','80' , '3', '3', '1')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d2', 'Tuesday', '200', '12310','80', '4', '4', '1')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d3', 'Wednesday', '300', '9990','80', '5', '5', '1')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d4', 'Thursday', '400', '2500','80', '6', '5', '1')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d5', 'Friday', '500', '1040','80', '7', '5', '1')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d6', 'Saturday', '600', '10193','80', '2', '1', '1')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d7', 'Sunday', '700', '22440','80', '10', '3', '1')");

        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d8', 'Monday', '100', '10000','80', '3', '3', '2')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d9', 'Tuesday', '200', '12310','80', '4', '4', '2')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d10', 'Wednesday', '300', '9990','80', '5', '5', '2')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d11', 'Thursday', '400', '2500','80', '6', '5', '2')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d12', 'Friday', '500', '1040','80', '7', '5', '2')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d13', 'Saturday', '600', '10193','80', '2', '1', '2')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d14', 'Sunday', '700', '22440','80', '10', '3', '2')");

        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d15', 'Monday', '100', '10000','80', '3', '3', '3')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d16', 'Tuesday', '200', '12310','80', '4', '4', '3')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d17', 'Wednesday', '300', '9990','80' , '5', '5', '3')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d18', 'Thursday', '400', '2500','80' ,'6', '5', '3')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d19', 'Friday', '500', '1040','80' , '7', '5', '3')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d20', 'Saturday', '600', '10193','80' , '2', '1', '3')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d21', 'Sunday', '700', '22440','80' , '10', '3', '3')");

        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d22', 'Monday', '100', '10000','80' , '3', '3', '4')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d23', 'Tuesday', '200', '12310','80' , '4', '4', '4')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d24', 'Wednesday', '300', '9990','80' , '5', '5', '4')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d25', 'Thursday', '400', '2500','80' , '6', '5', '4')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d26', 'Friday', '500', '1040','80' , '7', '5', '4')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d27', 'Saturday', '600', '10193','80' , '2', '1', '4')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d28', 'Sunday', '700', '22440','80' , '10', '3', '4')");

        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d29', 'Monday', '100', '10000','80' , '3', '3', '5')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d30', 'Tuesday', '200', '12310','80' , '4', '4', '5')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d31', 'Wednesday', '300', '9990','80' , '5', '5', '5')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d32', 'Thursday', '400', '2500','80' , '6', '5', '5')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d33', 'Friday', '500', '1040','80' , '7', '5', '5')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d34', 'Saturday', '600', '10193','80' , '2', '1', '5')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d35', 'Sunday', '700', '22440','80' , '10', '3', '5')");

        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d36', 'Monday', '100', '10000','80' , '3', '3', '6')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d37', 'Tuesday', '200', '12310','80' , '4', '4', '6')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d38', 'Wednesday', '300', '9990','80' , '5', '5', '6')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d39', 'Thursday', '400', '2500','80' , '6', '5', '6')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d40', 'Friday', '500', '1040','80' , '7', '5', '6')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d41', 'Saturday', '600', '10193','80' , '2', '1', '6')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d42', 'Sunday', '700', '22440','80' , '10', '3', '6')");

        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d43', 'Monday', '100', '10000','80' , '3', '3', '7')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d44', 'Tuesday', '200', '12310','80' , '4', '4', '7')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d45', 'Wednesday', '300', '9990','80' , '5', '5', '7')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d46', 'Thursday', '400', '2500','80' , '6', '5', '7')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d47', 'Friday', '500', '1040','80' , '7', '5', '7')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d48', 'Saturday', '600', '10193','80' , '2', '1', '7')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d49', 'Sunday', '700', '22440','80' , '10', '3', '7')");

        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d50', 'Monday', '100', '10000','80' , '3', '3', '8')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d51', 'Tuesday', '200', '12310','80' , '4', '4', '8')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d52', 'Wednesday', '300', '9990','80' , '5', '5', '8')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d53', 'Thursday', '400', '2500','80' , '6', '5', '8')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d54', 'Friday', '500', '1040','80' , '7', '5', '8')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d55', 'Saturday', '600', '10193','80' , '2', '1', '8')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d56', 'Sunday', '700', '22440','80' , '10', '3', '8')");

        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d57', 'Monday', '100', '10000','80' , '3', '3', '9')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d58', 'Tuesday', '200', '12310','80' , '4', '4', '9')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d59', 'Wednesday', '300', '9990','80' , '5', '5', '9')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d60', 'Thursday', '400', '2500','80' , '6', '5', '9')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d61', 'Friday', '500', '1040','80' , '7', '5', '9')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d62', 'Saturday', '600', '10193','80' , '2', '1', '9')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d63', 'Sunday', '700', '22440','80' , '10', '3', '9')");

        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d64', 'Monday', '100', '10000','80' , '3', '3', '10')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d65', 'Tuesday', '200', '12310','80' , '4', '4', '10')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d66', 'Wednesday', '300', '9990','80' , '5', '5', '10')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d67', 'Thursday', '400', '2500','80' , '6', '5', '10')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d68', 'Friday', '500', '1040','80' , '7', '5', '10')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d69', 'Saturday', '600', '10193','80' , '2', '1', '10')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('d70', 'Sunday', '700', '22440','80' , '10', '3', '10')");
       
        
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