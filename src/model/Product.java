package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="product")
@XmlType(propOrder= {"id","name","available","wholesalerPrice","publicPrice","stock"})
@Entity
@Table(name="Product")
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //with this tag we make the id auto_increment
	@Column(name = "id", unique = true, nullable = true)
	private int id;
	
	@Column
    private String name;
	@Column
	private double price;
    private Amount publicPrice;
    private Amount wholesalerPrice;
	@Column
    private boolean available;
	@Column(name = "stock")		
    private int stock;
    private static int totalProducts;
    
    static double EXPIRATION_RATE=0.60;
    
	public  Product(String name, double wholesalerPrice, boolean available, int stock) {
		super();
		this.id = totalProducts+1;
		this.name = name;
		this.wholesalerPrice = new Amount(wholesalerPrice);
		this.available = available;
		this.stock = stock;
		this.publicPrice = new Amount(wholesalerPrice *2);
		totalProducts++;
	}

	public Product() {
		this.id = totalProducts+1;
		this.available = true;
		totalProducts++;
	}

	public int getId() {
		return id;
	}

	@XmlAttribute(name="id")
	public void setId(int id) {
		this.id = id;
	}
	@XmlAttribute(name="name")
	public String getName() {
		return name;
	}
	

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name="publicPrice")
	public Amount getPublicPrice() {
		return publicPrice;
	}

	public void setPublicPrice(Amount publicPrice) {
		this.publicPrice = publicPrice;
	}
	
	@XmlElement(name="wholesalerPrice")
	public Amount getWholesalerPrice() {
		return wholesalerPrice;
	}

	public void setWholesalerPrice(Amount wholesalerPrice) {
		this.wholesalerPrice = wholesalerPrice;
	}

	@XmlElement(name="available")
	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	@XmlElement(name="stock")
	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public static int getTotalProducts() {
		return totalProducts;
	}

	public static void setTotalProducts(int totalProducts) {
		Product.totalProducts = totalProducts;
	}
	
	public void expire() {
		publicPrice.setValue(publicPrice.getValue()*EXPIRATION_RATE);
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", publicPrice=" + publicPrice + ", wholesalerPrice="
				+ wholesalerPrice + ", stock=" + stock + "]";
	}
	


    
}
