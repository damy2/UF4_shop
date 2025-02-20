package model;

import java.sql.SQLException;

import dao.Dao;
import dao.DaoImplJDBC;
import dao.DaoImplMongoDB;

public class Employee extends Person implements main.Logable {
	private int employeeId;
	private String password;
//	final int USER = 123;
//	final String PASSWORD = "test";
	private Dao dao = new DaoImplMongoDB();

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
		try {
			Employee employee = dao.getEmployee(user, password);
			if (employee == null) {
				return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
