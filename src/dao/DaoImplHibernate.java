package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import model.Employee;
import model.Product;


public class DaoImplHibernate implements Dao {

	private Session session;
	private Transaction tx;

	
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
		
		List<Product> products;
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		org.hibernate.SessionFactory sessionFactory = configuration.buildSessionFactory();
    	session = sessionFactory.openSession();		

    	
    	 try {
             // Start a transaction
             session.beginTransaction();
             
             // Query to fetch all students
              products = session.createQuery("from inventory").getResultList();
             // Commit the transaction
             session.getTransaction().commit();
         } finally {
             sessionFactory.close();
         }
 
		  
		session.close();
		return (ArrayList<Product>) products;
	}

	public void addProduct() {
		
	}
	public void updateProduct() {
		
	}
	public void removeProduct() {
		
	}

	
	@Override
	public boolean writeInventory(ArrayList<Product> inventory) {
		// TODO Auto-generated method stub
		return false;
	}

}
