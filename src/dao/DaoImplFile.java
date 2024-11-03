package dao;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import main.Shop;
import model.Employee;
import model.Product;
import model.Sale;

public class DaoImplFile implements Dao {

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
		Path filePath = Paths.get("src\\files", "inputInventory.txt");

		ArrayList<Product> inventory = new ArrayList<Product>();
		try {
			String content = Files.readString(filePath);
			// reading file from given path
			String[] list = content.split(";");
			for (int i = 0; i < list.length - 1; i += 3) {
				String[] name = list[i].split(":");
				String[] wholeaSalerPrice = list[i + 1].split(":");
				String[] stock = list[i + 2].split(":");
				inventory.add(
						new Product(name[1], Double.valueOf(wholeaSalerPrice[1]), true, Integer.valueOf(stock[1])));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return inventory;
	}

	@Override
	public boolean writeInventory(ArrayList<Product> inventory) {
		LocalDate date = LocalDate.now();
		int counter = 0;	
		String content = "";

		for (Product product : inventory) {
			if (product != null) {
				counter++;
				content += counter + ";Product:" + product.getName() + ";Stock:" + product.getStock() + ";";

			}
		}
		content += "Numero total de productos:" + counter + ";";

		try {
			try {
				
				File file = new File(
						"C:\\Users\\Usuario\\eclipse-workspace\\dam2_m03_uf2_poo_shop2 2.0\\src\\files\\inventory_"
								+ date + ".txt");
				if (file.createNewFile()) {
					System.out.println("File created: " + file.getPath());
				} else {
					System.out.println("File already exists.");
					return false;
				}
			} catch (IOException e) {
				System.out.println("Error");
				return false;
			}
			PrintWriter out = new PrintWriter("inventory_" + date + ".txt");
			out.println(content);
			out.close();
			return true;

		} catch (Exception e) {
			System.out.println("Error");
			return false;

		}

	}

}
