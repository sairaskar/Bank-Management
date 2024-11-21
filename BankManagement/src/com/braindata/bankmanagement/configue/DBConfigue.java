package com.braindata.bankmanagement.configue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfigue {

	public static Connection getConnection() throws ClassNotFoundException, SQLException {

		// step 1 : Load Driver class
		Class.forName("com.mysql.cj.jdbc.Driver");

		// step 2 : Establish Connection
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagement", "root", "root");
		return con;

	}

}
