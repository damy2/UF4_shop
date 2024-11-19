package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.jaxb.JaxbMarshaller;
import dao.jaxb.JaxbUnMarshaller;
import model.Employee;
import model.Product;

public class DaoImplJaxb implements Dao{

	@Override
	public Employee getEmployee(int emplyeeId, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void connect() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disconnect() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Product> getInventory() {
		return (new JaxbUnMarshaller()).init();
	}

	@Override
	public boolean writeInventory(ArrayList<Product> inventory) {
		
		return (new JaxbMarshaller()).init(inventory);
		
	}

}
