package com.aca.capstone.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MariaDB {
	private static String connectionUrl = 
			"jdbc:mariadb://localhost:3306/passwordmanager?user=******&password=******"; //insert custom db credentials here
	/*public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = MariaDB.getConnection();
		if(connection != null) {
			System.out.println("A real connection!");
			DatabaseMetaData metaData = connection.getMetaData();
			ResultSet rs = metaData.getTables(null, null, "%", null);
			while (rs.next()) {
				System.out.println(rs.getString("table_name"));
			}
		} else {
			System.out.println("Help. Connection null.");
		}
	}
	*/
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			connection = DriverManager.getConnection(connectionUrl);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}

}