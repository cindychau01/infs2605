
import java.sql.*;
import java.util.*;

public class database {

    protected Connection connection = null;

    public void openConnection() {
        try {
          
            connection = DriverManager.getConnection("jdbc:sqlite:app.db");
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
        + "Password VARCHAR(20) NOT NULL,"
        + "Fname VARCHAR(20) NOT NULL,"
        + "Lname VARCHAR(20) NOT NULL"
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
        + "Overall INTEGER,"
        + "User_ID INTEGER NOT NULL,"
        + "FOREIGN KEY (User_ID) REFERENCES Person(User_ID)"
        + ")");

        s.execute("CREATE TABLE IF NOT EXISTS Activities"
        + "("
        + "Activity_ID INTEGER PRIMARY KEY,"
        + "Activity_Type VARCHAR(20),"
        + "Activity_Description VARCHAR(100),"
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
        + "Check_Up_Date VARCHAR(20),"
        + "Comments VARCHAR(500),"
        + "User_ID INTEGER,"
        + "FOREIGN KEY (User_ID) REFERENCES Person(User_ID)"
        + ")");

        s.execute("CREATE TABLE IF NOT EXISTS Goals"
        + "("
        + "Goals_ID INTEGER PRIMARY KEY,"
        + "Weight_Goals FLOAT,"
        + "Nutrient_Goals FLOAT,"
        + "Steps_Goals FLOAT,"
        + "User_ID INTEGER NOT NULL,"
        + "FOREIGN KEY (User_ID) REFERENCES Person(User_ID)"
        + ")");

        s.close();
        connection.close();
    }

    public void insertDummyData() throws SQLException {

        openConnection();

        Statement s = connection.createStatement();

        s.execute("INSERT OR REPLACE INTO Person(Username, Password, Fname, Lname) VALUES ( 'nicky951', 'admin', 'Nic', 'Liang');");
        s.execute("INSERT OR REPLACE INTO Human_Profile(Age, Mass, Height, Lean_mass, Fat_mass, User_ID) VALUES ( '20', '68', '1.69', '40', '28', '1');");
        s.execute("INSERT OR REPLACE INTO Daily_Input(Day, Stair_Count, Step_Count, Resting_Heart_Rate, Hours_Slept, Mental_Wellbeing, Overall, User_ID) VALUES ( 'Monday', '200', '100', '67', '6', '2', '1', '1');");
        s.execute("INSERT OR REPLACE INTO Goals(Weight_Goals, Nutrient_Goals, Steps_Goals, User_ID) VALUES ('65', '2500', '4000', '1');");
        s.execute("INSERT OR REPLACE INTO Medical_History(Check_Up_Date, Comments, User_ID) VALUES ('2019-01-01', 'None', '1');");
        s.execute("INSERT OR REPLACE INTO Activities(Activity_Type, Activity_Description, Minutes_Trained, Calories_Burnt, Reps, Gym_Attendance, User_ID) VALUES ('Anaerobic','Squats', '60', '200', '0', 'Yes', '1');");
        s.execute("INSERT OR REPLACE INTO Nutrients(Protein, Carbs, Fat) VALUES ('20.5', '400', '40');");
        s.execute("INSERT OR REPLACE INTO Nutrients(Protein, Carbs, Fat) VALUES ('112', '150', '50');");
        s.execute("INSERT OR REPLACE INTO Meals(Meal_Name, Calories_Consumed, Date_Consumed, Nutrient_ID, User_ID) VALUES ('Breakfast', '300', '2019-01-01', '1', '1');");
        s.execute("INSERT OR REPLACE INTO Meals(Meal_Name, Calories_Consumed, Date_Consumed, Nutrient_ID, User_ID) VALUES ('Lunch', '1500', '2019-01-01', '2', '1');");
        
        
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
        
        s.close();
        connection.close();

        return login;
    }

    public boolean checkUserExists(String newUser) throws SQLException {

        openConnection();

        Statement s = connection.createStatement();

        boolean exists = false;

        String user;

        try {
            ResultSet checkUser = s.executeQuery("SELECT Username FROM Person WHERE Username = '" + newUser + "';");

            user = checkUser.getString("Username");
        
            if(user.equals(newUser)) {
                exists = true;
            }

        } catch (SQLException noResult) {
            exists = false;   
        }

        s.close();
        connection.close();

        return exists;
    }

    public String checkUserID(String username) throws SQLException{

        String useridReturn;

        openConnection();
        Statement s = connection.createStatement();

        
        ResultSet userid = s.executeQuery("SELECT User_ID FROM Person WHERE Username = '" + username + "';");
        useridReturn = Integer.toString(userid.getInt("User_ID"));
    

        s.close();
        connection.close();
            
        return useridReturn;
    }

    public String returnWeightGoal (String loggedInID) throws SQLException {

        String weightReturn;

        openConnection();
        Statement s = connection.createStatement();

        ResultSet wGoal = s.executeQuery("SELECT Weight_Goals FROM Goals WHERE User_ID = '" + loggedInID + "';");

        weightReturn = Float.toString(wGoal.getFloat("Weight_Goals"));

        s.close();
        connection.close();

        return weightReturn;

    }

    public String returnNutrientGoal (String loggedInID) throws SQLException {

        String nutrientReturn;

        openConnection();
        Statement s = connection.createStatement();

        ResultSet nGoal = s.executeQuery("SELECT Nutrient_Goals FROM Goals WHERE User_ID = '" + loggedInID + "';");

        nutrientReturn = Float.toString(nGoal.getFloat("Nutrient_Goals"));

        s.close();
        connection.close();

        return nutrientReturn;

    }

    public String returnStepsGoal (String loggedInID) throws SQLException {

        String stepReturn;

        openConnection();
        Statement s = connection.createStatement();

        ResultSet sGoal = s.executeQuery("SELECT Steps_Goals FROM Goals WHERE User_ID = '" + loggedInID + "';");

        stepReturn = Float.toString(sGoal.getFloat("Steps_Goals"));

        s.close();
        connection.close();

        return stepReturn;

    }

    public String pieChartProtein(String loggedInID) throws SQLException {

        String pieProteinValue;

        openConnection();
        Statement s = connection.createStatement();

        ResultSet proteinVal = s.executeQuery("SELECT Protein" 
        + " FROM Nutrients" 
        + " INNER JOIN Meals USING (Nutrient_ID)"
        + " INNER JOIN Person USING (User_ID)"
        + " WHERE User_ID = '" + loggedInID + "'"
        + " ORDER BY Nutrient_ID DESC LIMIT 1;"
        );


        pieProteinValue = Float.toString(proteinVal.getFloat("Protein"));

        s.close();
        connection.close();

        return pieProteinValue;
    }

    public String pieChartCarbs(String loggedInID) throws SQLException {

        String pieCarbValue;

        openConnection();
        Statement s = connection.createStatement();

        ResultSet carbVal = s.executeQuery("SELECT Carbs" 
        + " FROM Nutrients" 
        + " INNER JOIN Meals USING (Nutrient_ID)"
        + " INNER JOIN Person USING (User_ID)"
        + " WHERE User_ID = '" + loggedInID + "'"
        + " ORDER BY Nutrient_ID DESC LIMIT 1;"
        );


        pieCarbValue = Float.toString(carbVal.getFloat("Carbs"));

        s.close();
        connection.close();

        return pieCarbValue;
    }

    public String pieChartFats(String loggedInID) throws SQLException {

        String pieFatValue;

        openConnection();
        Statement s = connection.createStatement();

        ResultSet fatVal = s.executeQuery("SELECT Fat" 
        + " FROM Nutrients" 
        + " INNER JOIN Meals USING (Nutrient_ID)"
        + " INNER JOIN Person USING (User_ID)"
        + " WHERE User_ID = '" + loggedInID + "'"
        + " ORDER BY Nutrient_ID DESC LIMIT 1;"
        );


        pieFatValue = Float.toString(fatVal.getFloat("Fat"));

        s.close();
        connection.close();

        return pieFatValue;
    }

    public String returnNutID() throws SQLException {

        String nutID;

        openConnection();
        Statement s = connection.createStatement();

        ResultSet nutVal = s.executeQuery("SELECT Nutrient_ID"
        + " FROM Nutrients"
        + " ORDER BY Nutrient_ID DESC LIMIT 1;"
        );

        nutID = Integer.toString(nutVal.getInt("Nutrient_ID"));

        s.close();
        connection.close();

        return nutID;
    }

    public String selectQuery(String loggedInID, String conditionReturn) throws SQLException {

        String returnValue;

        openConnection();
        Statement s = connection.createStatement();

        ResultSet value = s.executeQuery("SELECT " + conditionReturn + " FROM Human_Profile WHERE User_ID = " + loggedInID);

        returnValue = Float.toString(value.getFloat(conditionReturn));

        s.close();
        connection.close();

        return returnValue;
    }

    public String selectAge(String loggedInID, String conditionReturn) throws SQLException {

        String returnValue;

        openConnection();
        Statement s = connection.createStatement();

        ResultSet value = s.executeQuery("SELECT " + conditionReturn + " FROM Human_Profile WHERE User_ID = " + loggedInID);

        returnValue = Integer.toString(value.getInt(conditionReturn));

        s.close();
        connection.close();

        return returnValue;
    }

    public int returnAgeInt(String loggedInID, String conditionReturn) throws SQLException {
        int returnValue;

        openConnection();
        Statement s = connection.createStatement();

        ResultSet value = s.executeQuery("SELECT " + conditionReturn + " FROM Human_Profile WHERE User_ID = " + loggedInID);

        returnValue = value.getInt(conditionReturn);

        s.close();
        connection.close();

        return returnValue;
    }

    public void updateProfile(String column, String value, String condition) throws SQLException {

        openConnection();
        Statement s = connection.createStatement();

        s.execute("UPDATE Human_Profile SET " + column + " = '" + value + "' WHERE User_ID = " + condition + ";");

        s.close();
        connection.close();
    }

    public Float sumNutrient(String micro) throws SQLException {

        Float returnSum;

        openConnection();
        Statement s = connection.createStatement();

        ResultSet value = s.executeQuery("SELECT SUM(" + micro + ") FROM Nutrients");

        returnSum = value.getFloat("SUM(" + micro + ")");

        s.close();
        connection.close();

        return returnSum;
    }

    public int returnCount(String id, String activity) throws SQLException {

        int returnVal;

        openConnection();
        Statement s = connection.createStatement();
        
        ResultSet value = s.executeQuery("SELECT  COUNT(*) FROM Activities WHERE Activity_Type = '" + activity + "' AND User_ID = " + id);

        returnVal = value.getInt("COUNT(*)");

        s.close();
        connection.close();

        return returnVal;
    }

    public ArrayList returnActivityID(String id) throws SQLException {

        ArrayList<Integer> list = new ArrayList<Integer>();

        openConnection();
        Statement s = connection.createStatement();

        ResultSet values = s.executeQuery("SELECT Activity_ID FROM Activities WHERE User_ID =" + id);

        while(values.next()) {
            list.add(values.getInt("Activity_ID"));
        }

        s.close();
        connection.close();

        return list;
    }


    public ArrayList returnMinutes(String id) throws SQLException {

        ArrayList<Integer> list = new ArrayList<Integer>();

        openConnection();
        Statement s = connection.createStatement();

        ResultSet values = s.executeQuery("SELECT Minutes_Trained FROM Activities WHERE User_ID =" + id);

        while(values.next()) {
            list.add(values.getInt("Minutes_Trained"));
        }

        s.close();
        connection.close();

        return list;
    }

    public Integer sumColumnDaily(String column, String rating, String id) throws SQLException {

        Integer returnSum;

        openConnection();
        Statement s = connection.createStatement();

        ResultSet value = s.executeQuery("SELECT COUNT(*) FROM Daily_Input WHERE " +  column + " = " + rating + " AND User_ID = " + id);

        returnSum = value.getInt("COUNT(*)");

        s.close();
        connection.close();

        return returnSum;
    }

    public ArrayList returnDailyID(String id) throws SQLException {

        ArrayList<Integer> list = new ArrayList<Integer>();

        openConnection();
        Statement s = connection.createStatement();

        ResultSet values = s.executeQuery("SELECT Daily_ID FROM Daily_Input WHERE User_ID =" + id);

        while(values.next()) {
            list.add(values.getInt("Daily_ID"));
        }

        s.close();
        connection.close();

        return list;
    }


    public ArrayList returnRating(String id) throws SQLException {

        ArrayList<Integer> list = new ArrayList<Integer>();

        openConnection();
        Statement s = connection.createStatement();

        ResultSet values = s.executeQuery("SELECT Overall FROM Daily_Input WHERE User_ID =" + id);

        while(values.next()) {
            list.add(values.getInt("Overall"));
        }

        s.close();
        connection.close();

        return list;
    }

public Float returnMassGoal(String id) throws SQLException {

        Float returnVal;

        openConnection();
        Statement s = connection.createStatement();

        ResultSet value = s.executeQuery("SELECT Mass FROM Human_Profile WHERE User_ID = " + id + " ORDER BY MASS DESC LIMIT 1;");

        returnVal = value.getFloat("Mass");

        s.close();
        connection.close();

        return returnVal;
    }

    public Integer returnStepGoal(String id) throws SQLException {

        int returnVal;

        openConnection();
        Statement s = connection.createStatement();

        ResultSet value = s.executeQuery("SELECT SUM(Step_Count) FROM Daily_Input WHERE User_ID = " + id + " ORDER BY Step_Count DESC LIMIT 1;");

        returnVal = value.getInt("SUM(Step_Count)");

        s.close();
        connection.close();
        
        return returnVal;
    }

    public void updateMassGoal(String id, String value) throws SQLException{

        openConnection();
        Statement s = connection.createStatement();

        s.execute("UPDATE Goals SET Weight_Goals = '" + value + "' WHERE User_ID = " + id + ";");

        s.close();
        connection.close();

    }


    public void updateStepGoal(String id, String value) throws SQLException {

        openConnection();
        Statement s = connection.createStatement();

        s.execute("UPDATE Goals SET Steps_Goals = '" + value + "' WHERE User_ID = " + id + ";");

        s.close();
        connection.close();

    }


    public void updateCarbGoal(String id, String value) throws SQLException{

        openConnection();
        Statement s = connection.createStatement();

        s.execute("UPDATE Goals SET Nutrient_Goals = '" + value + "' WHERE User_ID = " + id + ";");

        s.close();
        connection.close();

    }

    public float returnHeight(String id) throws SQLException {

        Float returnVal;

        openConnection();
        Statement s = connection.createStatement();

        ResultSet value = s.executeQuery("SELECT Height FROM Human_Profile WHERE User_ID = " + id + " ORDER BY Height DESC LIMIT 1;");

        returnVal = value.getFloat("Height");

        s.close();
        connection.close();

        return returnVal;
    }

    public void insertMed(String date, String comment, String id) throws SQLException {
        
        openConnection();
        Statement s = connection.createStatement();

        s.execute("INSERT INTO Medical_History(Check_Up_Date, Comments, User_ID) VALUES ('" + date + "', '" + comment + "', '" + id + "');");

        s.close();
        connection.close();
    }

    
    public void deleteSteps(String id) throws SQLException {

        openConnection();
        Statement s = connection.createStatement();

        s.execute("UPDATE Daily_Input SET Step_Count = NULL WHERE User_ID = " + id + ";");


        s.close();
        connection.close();

    }
    
}   