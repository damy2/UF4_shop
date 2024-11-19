package dao;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import dao.xml.DomWriter;
import dao.xml.SaxReader;
import model.Employee;
import model.Product;

public class DaoImplXml implements Dao {

	@Override
	public Employee getEmployee(int emplyeeId, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void connect() {
		// TODO Auto-generated method stub

	}

	@Override
	public void disconnect() {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Product> getInventory() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser;
		ArrayList<Product> inventory = new ArrayList<>();
		try {
			parser = factory.newSAXParser();
			File file = new File("src/files/inputInventory.xml");
			SaxReader saxReader = new SaxReader();
			parser.parse(file, saxReader);
			inventory = saxReader.getProducts();
		} catch (ParserConfigurationException | SAXException e) {
			System.out.println("ERROR creating the parser");
		} catch (IOException e) {
			System.out.println("ERROR file not found");
		}
		return inventory;

	}

	@Override
	public boolean writeInventory(ArrayList<Product> inventory) {
		DomWriter domWriter = new DomWriter();
		return domWriter.generateDocument(inventory);

	}

}
