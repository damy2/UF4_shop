package model;

import java.sql.SQLException;

import dao.Dao;
import dao.DaoImplJDBC;

public class Employee extends Person implements main.Logable {
	private int employeeId;
	private String password;
//	final int USER = 123;
//	final String PASSWORD = "test";
	private Dao dao = new DaoImplJDBC();

	public Employee() {
		super();
	}

	public Employee(int employeeId, String password) {
		this.employeeId = employeeId;
		this.password = password;

	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public boolean login(int user, String password) {
		//if (user == USER && password.equals(PASSWORD)) {
		//	return true;
		// }
		// return false;
		try {
			dao.connect();
			Employee employee = dao.getEmployee(user, password);
			dao.disconnect();
			if (employee == null) {
				return false;
			}
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return false;
	}

}
