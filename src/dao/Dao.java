package dao;

import java.sql.SQLException;

import model.Employee;

public interface Dao {
	Employee getEmployee(int emplyeeId, String password);

	public void connect() throws SQLException;

	public void disconnect() throws SQLException;


}