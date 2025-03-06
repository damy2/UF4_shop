package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity  //Specifies that this corresponts to a Database table.
@Table(name= "historical_inventory") 
public class ProductHistory {
		


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //with this tag we make the id auto_increment
	@Column(name = "id", unique = true, nullable = true) //with this tag we specify the atributes
	private int id;	
	
	@Column(name="name")								 //nullable = treu/false specifies that the values can be null or not
	private String name;
	
 //This specifies that for every instance of this class we have many of the other
    @Column(name = "stock")	
	private int Stock;
	
	@Column
	private double price;
	
	//This specifies that for every instance of this class we have many of the other
    @Column
	private boolean avaible; 

	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "product_id")
	private int productid;

	public ProductHistory(){} 
	 
	public ProductHistory(Product product) {
		this.createdAt = new Date();
		this.productid = product.getId();
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStock() {
		return Stock;
	}

	public void setStock(int stock) {
		Stock = stock;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isAvaible() {
		return avaible;
	}

	public void setAvaible(boolean avaible) {
		this.avaible = avaible;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}
}
