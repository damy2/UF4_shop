package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Employee;
import model.Product;

public class DaoImplJDBC implements Dao {

	private Connection connection;

	@Override
	public void connect() {
		// Define connection parameters
		String url = "jdbc:mysql://localhost:3306/employee";
		String user = "root";
		String pass = "";
		try {
			this.connection = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	@Override
	public Employee getEmployee(int emplyeeId, String password) {
		Employee employee = null;
		// prepare query
		String query = "select * from employee where password = ? and employeeId = ?";

		try (PreparedStatement ps = connection.prepareStatement(query)) {
			// set id to search for
			ps.setInt(2, emplyeeId);
			ps.setString(1, password);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					employee = new Employee(emplyeeId, password);
				}
			}
		} catch (SQLException e) {
			// in case error in SQL
			e.printStackTrace();
		}
		return employee;
	}

	@Override
	public void disconnect() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	@Override
	public ArrayList<Product> getInventory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean writeInventory(ArrayList<Product> inventory) {
		return false;

	}

}
