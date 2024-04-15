import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
    static String url = "jdbc:mysql://localhost:3306/MuscleForge";
    static String user = "root";
    static String password = "";

    public static ResultSet connectAndExecuteSQL(String sql) {

        ResultSet databaseResult = null;

        try {
            Connection myConnection = DriverManager.getConnection(url, user, password);
            Statement myStatement = myConnection.createStatement();

            switch(sql.charAt(0)) {
                case 'I': //INSERT
                    myStatement.executeUpdate(sql);
                    break;
                case 'S': //SELECT
                    return myStatement.executeQuery(sql);
            }

            myConnection.close();
        } catch (SQLException e) {
            System.out.println("ERROR in Database Connection: " + e);
        }

        return databaseResult;
    }
}
