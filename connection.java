package net.codejava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JavaMySQLTest {
	public static void main(String[] args) throws SQLException {
		String url = "jdbc:mysql://localhost:3306/namdb ";
		String username = "root";
		String password = "ashritha";

		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			String sql = "SELECT * FROM customer";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			int count = 0;
			while (result.next()) {
				String firstname = result.getString("firstname");
				String lastname = result.getString("lastname");
				count++;
				System.out.println("Customer " + count + ":" + firstname + "" + lastname);
			}

			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("connected to the database");

	}
}