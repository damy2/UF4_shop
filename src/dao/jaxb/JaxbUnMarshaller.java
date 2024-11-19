package dao.jaxb;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import model.Amount;
import model.Product;
import model.ProductList;

public class JaxbUnMarshaller {
	public ArrayList<Product> init() {
		// read from xml to java object

		ProductList products = null;
		ArrayList<Product> inventory = new ArrayList<Product>();
		try {
			JAXBContext context = JAXBContext.newInstance(ProductList.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			System.out.println("unmarshalling...");
			products = (ProductList) unmarshaller.unmarshal(new File("jaxb/inputInventory.xml"));
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		// print products
		if (products == null)
			System.out.println("Error unmarshalling");
		else {
			for (Product p : products.getProducts()) {
				if (p.getWholesalerPrice() != null) {
					p.setPublicPrice(new Amount(p.getWholesalerPrice().getValue() * 2));
				}
				System.out.println(p);
				inventory.add(p);
			}
		}
		return inventory;
	}
}
