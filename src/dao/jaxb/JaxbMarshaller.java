package dao.jaxb;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import view.LoginView;
import model.Product;
import model.ProductList;


public class JaxbMarshaller {
	
	public boolean init (ArrayList<Product> inventory) {
		LocalDate date = LocalDate.now();
		try {
			JAXBContext context = JAXBContext.newInstance(ProductList.class);
			Marshaller marshaller = context.createMarshaller();
			System.out.println("marshalling... ");
			ProductList products = createXml(inventory);
			marshaller.marshal(products,new File("src/files/inventory_" + date + ".xml"));
			return true;
		} catch (JAXBException e) {
			e.printStackTrace();
			return false;
		}
	}

	private ProductList createXml(ArrayList<Product> inventory) {
		
		
		// print products
		for (Product p : inventory) {
			System.out.println(p);
		}
		return new ProductList(inventory,inventory.size());
	}
}
