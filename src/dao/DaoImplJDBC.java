package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Employee;
import model.Product;

public class DaoImplJDBC implements Dao {

	private Connection connection;

	@Override
	public void connect() {
		// Define connection parameters
		String url = "jdbc:mysql://localhost:3306/shop";
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
		String query = "select * from inventory";
		ArrayList<Product> products = new ArrayList<>();
		
		
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			// set id to search for
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Product product = new Product(rs.getString("name"),rs.getInt("price"),rs.getBoolean("avaible"),rs.getInt("stock"));
					products.add(product);
				}
			}
		} catch (SQLException e) {
			// in case error in SQL
			e.printStackTrace();
		}
		return products;
	}

	@Override
	public boolean writeInventory(ArrayList<Product> inventory) {
		
		String query = "insert into historical_inventory (id,available,price,stock,name) values (?,?,?,?,?)";
		
		
		try (PreparedStatement ps = connection.prepareStatement(query)) {

			for(Product product : inventory) {
				ps.setInt(1, product.getId());
				if(product.getStock()>0) {
					ps.setBoolean(2, true);				
				}else {
					ps.setBoolean(2, false);								
				}
				ps.setDouble(3,product.getWholesalerPrice().getValue());
				ps.setInt(4,product.getStock());
				ps.setString(5,product.getName());
				ps.executeUpdate();
			}
				
				
		
		} catch (SQLException e) {
			// in case error in SQL
			e.printStackTrace();
			return false;
		}
	
		return true;

	}

	@Override
	public void updateProduct(Product product, int stock) {
		String query = "update inventory set stock=? where id=?";
		
		
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setInt(1, product.getStock()+stock);
			ps.setInt(2,product.getId());
			ps.execute();
		} catch (SQLException e) {
			// in case error in SQL
			e.printStackTrace();
		}
	}
		
	

	@Override
	public void removeProduct(Product product) {
		String query = "delete from inventory where id=?";
		
		
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setInt(1,product.getId());
			ps.execute();
		} catch (SQLException e) {
			// in case error in SQL
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addProduct(Product product) {
		String query = "insert into inventory (id,avaible,price,stock,name) values (?,?,?,?,?)";
		
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setInt(1, product.getId());
			if(product.getStock()>0) {
				ps.setBoolean(2, true);				
			}else {
				ps.setBoolean(2, false);								
			}
			ps.setDouble(3,product.getWholesalerPrice().getValue());
			ps.setInt(4,product.getStock());
			ps.setString(5,product.getName());
			
			ps.execute();
			
		} catch (SQLException e) {
			// in case error in SQL
			e.printStackTrace();
		}
		
		
	}

}
