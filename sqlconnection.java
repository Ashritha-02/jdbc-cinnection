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
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter your last name: ");
		String lastname = scanner.nextLine();

		System.out.print("Enter your first name: ");
		String firstname = scanner.nextLine();

		System.out.print("Enter your email: ");
		String email = scanner.nextLine();
		Connection connection = DriverManager.getConnection(url, username, password);
		String sql = "insert into customers"
				+ " (last_name, first_name, email)" + " values (?, ?, ?)";
		Statement statement = connection.prepareStatement(sql);



		try {
			
			((PreparedStatement) statement).setString(1, lastname);
			((PreparedStatement) statement).setString( 2,firstname);
			((PreparedStatement) statement).setString(3, email);
			statement.executeUpdate(email);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	System.out.println("connected to the database");

	}
}
