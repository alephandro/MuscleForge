import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
    static String url = "jdbc:mysql://localhost:3306/MuscleForge";
    static String user = "root";
    static String password = "";

	public static int executeInsert(String sql) throws SQLException {
		int res = 0;
		try {
			Connection myConnection = DriverManager.getConnection(url, user, password);
			Statement myStatement = myConnection.createStatement();
			res = myStatement.executeUpdate(sql);
			myConnection.close();
		} catch (SQLException e) {
			System.out.println("ERROR in Database Connection: " + e);
			throw e;
		}
		return res;
	}

	public static int executeQuery(String sql) throws SQLException {
		int res = 0;
		ResultSet databaseResult = null;
		try {
			Connection myConnection = DriverManager.getConnection(url, user, password);
			Statement myStatement = myConnection.createStatement();
			databaseResult = myStatement.executeQuery(sql);

			while(databaseResult.next())
				res = databaseResult.getRow();

			myConnection.close();
		} catch (SQLException e) {
			System.out.println("ERROR in Database Connection: " + e);
			throw e;
		}
		return res;
	}
}
