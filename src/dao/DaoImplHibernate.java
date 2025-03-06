package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import model.Employee;
import model.Product;
import model.ProductHistory;

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
	
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		org.hibernate.SessionFactory sessionFactory = configuration.buildSessionFactory();
		session = sessionFactory.openSession();

	}

	@Override
	public void disconnect() {
		session.close();

	}

	@Override
	public ArrayList<Product> getInventory() {

		List<Product> products;
		connect();

		try {
			// Start a transaction
			session.beginTransaction();
			Query q = session.createQuery("from Product p",Product.class);
			
			products = q.list();
			for (Product product : products) {
				product.setPrice(product.getPrice());
			}
			
			// Commit the transaction
			session.getTransaction().commit();
		} finally {
			disconnect();
		}

		session.close();
		return (ArrayList<Product>) products;
	}

	@Override
	public void addProduct(Product product) {
		connect();
		try {
			session.beginTransaction();
			// Set 'price' from 'wholesalerPrice' to save data in the database
			product.setPrice(product.getWholesalerPrice().getValue());
			session.save(product);
			session.getTransaction().commit();			
		} catch (HibernateException e) {
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	public void updateProduct(Product product,int stock) {
		connect();
		
		try {
			session.beginTransaction();
			session.update(product);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}


	public void removeProduct(Product product) {
		connect();
		try {
			session.beginTransaction();
			session.remove(product);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	@Override
	public boolean writeInventory(ArrayList<Product> inventory) {
			Date date = new Date();
			connect();
			try {
				
			session.beginTransaction();
				for (Product product : inventory) {
					ProductHistory history = new ProductHistory();
					history.setProductid(product.getId());
					history.setName(product.getName());
					history.setPrice(product.getPrice());
					history.setStock(product.getStock());
					history.setCreatedAt(date);
					session.save(history);
				}
				session.getTransaction().commit();
				
			} catch (HibernateException e) {
				if (session.getTransaction() != null)
					session.getTransaction().rollback();
				e.printStackTrace();
				return false;
			} finally {
				disconnect();
			}
			return true;
		}


	





}
