
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
        + "Nutrient_ID INT NOT NULL,"
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
        + "Daily_ID INT NOT NULL,"
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
        + "Activity_ID INT NOT NULL,"
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
        + "Meal_ID INT NOT NULL,"
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
        + "Profile_ID INT NOT NULL,"
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
        + "Medical_ID INT NOT NULL,"
        + "Check_Up_Date DATE,"
        + "Comments VARCHAR(500),"
        + "User_ID INT,"
        + "PRIMARY KEY (Medical_ID),"
        + "FOREIGN KEY (User_ID) REFERENCES Person(User_ID)"
        + ")");

        s.execute("CREATE TABLE IF NOT EXISTS Goals"
        + "("
        + "Goals_ID INT NOT NULL,"
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

        openConnection();

        Statement s = connection.createStatement();

        //ID, Protein, Carbs, Fat,
        s.execute("INSERT OR REPLACE INTO Nutrients VALUES ('1', '10.5', '27', '2.5');");
        s.execute("INSERT OR REPLACE INTO Nutrients VALUES ('2', '15.2', '50.1', '50.5');");
        s.execute("INSERT OR REPLACE INTO Nutrients VALUES ('3', '7.6', '23.5', '100.5');");
        s.execute("INSERT OR REPLACE INTO Nutrients VALUES ('4', '18.4', '34', '2.3');");
        s.execute("INSERT OR REPLACE INTO Nutrients VALUES ('5', '20.1', '19.8', '5.5');");
        s.execute("INSERT OR REPLACE INTO Nutrients VALUES ('6', '8.5', '57.9', '24.2');");
        s.execute("INSERT OR REPLACE INTO Nutrients VALUES ('7', '11', '21.1', '4.1');");
        s.execute("INSERT OR REPLACE INTO Nutrients VALUES ('8', '12.3', '27.5', '3.55');");
        s.execute("INSERT OR REPLACE INTO Nutrients VALUES ('9', '3.1', '27.8', '19.5');");
        s.execute("INSERT OR REPLACE INTO Nutrients VALUES ('10', '31.8', '22', '33.3');");

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
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('1', 'Monday', '100', '10000','80' , '3', '3', '1')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('2', 'Tuesday', '200', '12310','80', '4', '4', '1')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('3', 'Wednesday', '300', '9990','80', '5', '5', '1')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('4', 'Thursday', '400', '2500','80', '6', '5', '1')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('5', 'Friday', '500', '1040','80', '7', '5', '1')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('6', 'Saturday', '600', '10193','80', '2', '1', '1')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('7', 'Sunday', '700', '22440','80', '10', '3', '1')");

        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('8', 'Monday', '100', '10000','80', '3', '3', '2')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('9', 'Tuesday', '200', '12310','80', '4', '4', '2')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('10', 'Wednesday', '300', '9990','80', '5', '5', '2')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('11', 'Thursday', '400', '2500','80', '6', '5', '2')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('12', 'Friday', '500', '1040','80', '7', '5', '2')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('13', 'Saturday', '600', '10193','80', '2', '1', '2')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('14', 'Sunday', '700', '22440','80', '10', '3', '2')");

        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('15', 'Monday', '100', '10000','80', '3', '3', '3')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('16', 'Tuesday', '200', '12310','80', '4', '4', '3')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('17', 'Wednesday', '300', '9990','80' , '5', '5', '3')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('18', 'Thursday', '400', '2500','80' ,'6', '5', '3')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('19', 'Friday', '500', '1040','80' , '7', '5', '3')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('20', 'Saturday', '600', '10193','80' , '2', '1', '3')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('21', 'Sunday', '700', '22440','80' , '10', '3', '3')");

        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('22', 'Monday', '100', '10000','80' , '3', '3', '4')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('23', 'Tuesday', '200', '12310','80' , '4', '4', '4')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('24', 'Wednesday', '300', '9990','80' , '5', '5', '4')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('25', 'Thursday', '400', '2500','80' , '6', '5', '4')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('26', 'Friday', '500', '1040','80' , '7', '5', '4')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('27', 'Saturday', '600', '10193','80' , '2', '1', '4')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('28', 'Sunday', '700', '22440','80' , '10', '3', '4')");

        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('29', 'Monday', '100', '10000','80' , '3', '3', '5')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('30', 'Tuesday', '200', '12310','80' , '4', '4', '5')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('31', 'Wednesday', '300', '9990','80' , '5', '5', '5')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('32', 'Thursday', '400', '2500','80' , '6', '5', '5')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('33', 'Friday', '500', '1040','80' , '7', '5', '5')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('34', 'Saturday', '600', '10193','80' , '2', '1', '5')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('35', 'Sunday', '700', '22440','80' , '10', '3', '5')");

        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('36', 'Monday', '100', '10000','80' , '3', '3', '6')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('37', 'Tuesday', '200', '12310','80' , '4', '4', '6')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('38', 'Wednesday', '300', '9990','80' , '5', '5', '6')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('39', 'Thursday', '400', '2500','80' , '6', '5', '6')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('40', 'Friday', '500', '1040','80' , '7', '5', '6')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('41', 'Saturday', '600', '10193','80' , '2', '1', '6')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('42', 'Sunday', '700', '22440','80' , '10', '3', '6')");

        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('43', 'Monday', '100', '10000','80' , '3', '3', '7')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('44', 'Tuesday', '200', '12310','80' , '4', '4', '7')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('45', 'Wednesday', '300', '9990','80' , '5', '5', '7')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('46', 'Thursday', '400', '2500','80' , '6', '5', '7')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('47', 'Friday', '500', '1040','80' , '7', '5', '7')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('48', 'Saturday', '600', '10193','80' , '2', '1', '7')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('49', 'Sunday', '700', '22440','80' , '10', '3', '7')");

        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('50', 'Monday', '100', '10000','80' , '3', '3', '8')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('51', 'Tuesday', '200', '12310','80' , '4', '4', '8')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('52', 'Wednesday', '300', '9990','80' , '5', '5', '8')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('53', 'Thursday', '400', '2500','80' , '6', '5', '8')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('54', 'Friday', '500', '1040','80' , '7', '5', '8')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('55', 'Saturday', '600', '10193','80' , '2', '1', '8')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('56', 'Sunday', '700', '22440','80' , '10', '3', '8')");

        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('57', 'Monday', '100', '10000','80' , '3', '3', '9')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('58', 'Tuesday', '200', '12310','80' , '4', '4', '9')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('59', 'Wednesday', '300', '9990','80' , '5', '5', '9')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('60', 'Thursday', '400', '2500','80' , '6', '5', '9')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('61', 'Friday', '500', '1040','80' , '7', '5', '9')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('62', 'Saturday', '600', '10193','80' , '2', '1', '9')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('63', 'Sunday', '700', '22440','80' , '10', '3', '9')");

        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('64', 'Monday', '100', '10000','80' , '3', '3', '10')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('65', 'Tuesday', '200', '12310','80' , '4', '4', '10')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('66', 'Wednesday', '300', '9990','80' , '5', '5', '10')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('67', 'Thursday', '400', '2500','80' , '6', '5', '10')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('68', 'Friday', '500', '1040','80' , '7', '5', '10')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('69', 'Saturday', '600', '10193','80' , '2', '1', '10')");
        s.execute("INSERT OR REPLACE INTO Daily_Input VALUES('70', 'Sunday', '700', '22440','80' , '10', '3', '10')");
       
        
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