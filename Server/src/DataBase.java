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
        System.out.println("hola");

        try {
            Connection myConnection = DriverManager.getConnection(url, user, password);
            Statement myStatement = myConnection.createStatement();
            myStatement.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println("ERROR in Database Connection: " + e);
        }
        return databaseResult;
    }
}
