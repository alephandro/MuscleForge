package com.example.gym;

import com.example.gym.utils.Exercise;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    static String url = "jdbc:mysql://db:3306/MuscleForge";
    static String urlLoad = "jdbc:mysql://db:3306/";
    static String user = "root";
    static String password = "";
	static String backupPath = "/shared-data/backup.sql";

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

	public static List<Exercise> executeQueryExercises(String sql) throws SQLException {
		int res = 0;
		ResultSet databaseResult = null;
		List<Exercise> result = null;
		try {
			Connection myConnection = DriverManager.getConnection(url, user, password);
			Statement myStatement = myConnection.createStatement();
			databaseResult = myStatement.executeQuery(sql);
			result = new ArrayList<>();

			String valorColumna1, valorColumna2, valorColumna3;
			Exercise exercise;

			while(databaseResult.next()) {
				valorColumna1 = databaseResult.getString("name");
				valorColumna2 = databaseResult.getString("muscle_group");
				valorColumna3 = databaseResult.getString("description");

				exercise = new Exercise(valorColumna1, valorColumna2, valorColumna3);
				result.add(exercise);

			}
			myConnection.close();
		} catch (SQLException e) {
			System.out.println("ERROR in Database Connection: " + e);
			throw e;
		}

		return result;
	}

	public static String saveDatabase() {
		try (Connection conn = DriverManager.getConnection(url, user, password)) {
			File f = new File(backupPath);
			while(true) {
				if(f.exists() && !f.isDirectory())
					break;
				System.out.println("Waiting for file");
				Thread.sleep(5000);
			}
			return backupPath;
		} catch (SQLException | InterruptedException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static void loadDatabase() {
		try (Connection conn = DriverManager.getConnection(urlLoad, user, password);
			 Statement stmt = conn.createStatement();
			 BufferedReader reader = new BufferedReader(new FileReader(backupPath))) {

			String line;
			StringBuilder query = new StringBuilder();
			stmt.executeUpdate("DROP SCHEMA IF EXISTS MuscleForge;");
			while ((line = reader.readLine()) != null) {
				if (!line.trim().startsWith("--") && !line.trim().isEmpty()) {
					query.append(line);
					if (line.trim().endsWith(";")) {
						stmt.executeUpdate(query.toString());
						query.setLength(0);
					}
				}
			}

			System.out.println("Database restored successfully from: " + backupPath);

		} catch (SQLException | IOException ex) {
			ex.printStackTrace();
		}

	}
}
