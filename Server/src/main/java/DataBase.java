import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
    static String url = "jdbc:mysql://localhost:3306/MuscleForge";
    static String user = "rana";
    static String password = "rana";
	static String rutaBackup = "backup.sql";

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

	public void realizarBackup() {
		try (Connection conn = DriverManager.getConnection(url, user, password);
			 PrintWriter writer = new PrintWriter(new FileWriter(rutaBackup))) {

			String comandoBackup = "mysqldump -u " + user + " --databases MuscleForge";
			Process proceso = Runtime.getRuntime().exec(comandoBackup);

			java.util.Scanner scanner = new java.util.Scanner(proceso.getInputStream());
			while (scanner.hasNext()) {
				writer.println(scanner.nextLine());
			}

			int exitVal = proceso.waitFor();
			if (exitVal == 0) {
				System.out.println("Copia de seguridad creada con éxito en: " + rutaBackup);
			} else {
				System.err.println("Error al crear la copia de seguridad");
			}

		} catch (SQLException | IOException | InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	public void ejecutarBackupEnBaseDeDatos() {
		try (Connection conn = DriverManager.getConnection(url, user, password);
			 Statement stmt = conn.createStatement();
			 BufferedReader reader = new BufferedReader(new FileReader(rutaBackup))) {

			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}

			String[] queries = sb.toString().split(";");
			for (String query : queries) {
				stmt.executeUpdate(query);
			}

			System.out.println("Backup ejecutado con éxito en la base de datos.");

		} catch (SQLException | IOException ex) {
			ex.printStackTrace();
		}
	}
}
