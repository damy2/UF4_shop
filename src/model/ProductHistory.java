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
	
	@Column(name = "product_id")
	private Double price;
	
	//This specifies that for every instance of this class we have many of the other
    @Column(name = "product_id")
	private boolean avaible; 

	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "product_id")
	private Product product;

	public ProductHistory(){} 
	 
	public ProductHistory(Product product) {
		this.createdAt = new Date();
		this.product = product;
	}
}
