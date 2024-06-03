package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconexion {
	public static final String JDBC_URL = "jdbc:mysql://localhost:3306/proyecto";
	public static Connection instance = null;

	public static Connection getConexion() throws SQLException {

		if (instance == null) {

			instance = DriverManager.getConnection(JDBC_URL, "root", "");

		}
		return instance;

	}
	
}

