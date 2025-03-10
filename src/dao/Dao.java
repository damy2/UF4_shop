package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Employee;
import model.Product;

public interface Dao {
	Employee getEmployee(int emplyeeId, String password);

	public void connect();

	public void disconnect();
	
	public ArrayList<Product> getInventory();
	
	public boolean writeInventory(ArrayList<Product> inventory);
	
	public void updateProduct(Product product, int stock);
	
	public void removeProduct(Product product);
	
	public void addProduct(Product product);


}