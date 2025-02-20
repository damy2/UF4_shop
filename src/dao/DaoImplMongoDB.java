package dao;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientException;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import model.Employee;
import model.Product;

public class DaoImplMongoDB implements Dao {

	MongoCollection<Document> collection;
	ObjectId id;

	String uri = "mongodb://localhost:27017";
	MongoClientURI mongoClientURI = new MongoClientURI(uri);
	MongoClient mongoClient = new MongoClient(mongoClientURI);

	MongoDatabase mongoDatabase = mongoClient.getDatabase("shop");

	@Override
	public Employee getEmployee(int emplyeeId, String password) {

		collection = mongoDatabase.getCollection("users");
		Employee employee = null;
		Iterable<Document> documents = collection.find();

		for (Document doc : documents) {

			if (emplyeeId == doc.getInteger("employeeId") && password.equals(doc.getString("password"))) {
				employee = new Employee(emplyeeId, password);
			}
		}
		return employee;
	}

	@Override
	public void connect() {

	}

	@Override
	public void disconnect() {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Product> getInventory() {

		collection = mongoDatabase.getCollection("inventory");
		ArrayList<Product> inventory = new ArrayList<>();

		// read n documents
		// Read the first '5' documents
		Iterable<Document> documents = collection.find().limit(2);

		int count = 0;
		for (Document doc : documents) {

			String name = doc.getString("name");
			Document wholesalerPriceDoc = (Document) doc.get("wholesalerPrice");
			double wholesalerPrice = wholesalerPriceDoc.getDouble("value");
			boolean available = doc.getBoolean("available");
			int stock = doc.getInteger("stock");
			Product product = new Product(name, wholesalerPrice, available, stock);
			inventory.add(product);

		}
		return inventory;
	}

	@Override
	public boolean writeInventory(ArrayList<Product> inventory) {

		collection = mongoDatabase.getCollection("historical_inventory");

		try {
			if (!inventory.isEmpty()) {

				for (Product product : inventory) {
					boolean available;
					LocalDateTime date = LocalDateTime.now();
					if (product.getStock() > 0) {
						available = true;
					} else {
						available = false;
					}
					Document doc = new Document("_id", new ObjectId()).append("id", product.getId())
							.append("name", product.getName())
							.append("wholesalerPrice",
									new Document().append("value", product.getWholesalerPrice().getValue())
											.append("currency", product.getWholesalerPrice().getCurrency()))
							.append("available", available).append("stock", product.getStock())
							.append("created_at", date);
					collection.insertOne(doc);
				}
				return true;
			}
			return false;
		} catch (MongoClientException e) {
			System.out.println(e);
			return false;
		}
	}

	public void addProduct(Product product) {
		collection = mongoDatabase.getCollection("inventory");
		boolean available;
		LocalDateTime date = LocalDateTime.now();
		if (product.getStock() > 0) {
			available = true;
		} else {
			available = false;
		}
		Document doc = new Document("_id", new ObjectId()).append("id", product.getId())
				.append("name", product.getName())
				.append("wholesalerPrice",
						new Document().append("value", product.getWholesalerPrice().getValue()).append("currency",
								product.getWholesalerPrice().getCurrency()))
				.append("available", available).append("stock", product.getStock()).append("created_at", date);
		collection.insertOne(doc);
	}

	public void updateProduct(Product product, int stock) {
		collection = mongoDatabase.getCollection("inventory");
		UpdateResult result = collection.updateOne(eq("id", product.getId()),set("stock", stock+product.getStock()));

	}

	public void removeProduct(Product product) {
		collection = mongoDatabase.getCollection("inventory");
		Iterable<Document> documents = collection.find();

		for (Document doc : documents) {
			Bson query = eq("id", product.getId());

			try {
				DeleteResult result = collection.deleteOne(query);
				System.out.println("Deleted document count: " + result.getDeletedCount());
			} catch (MongoException me) {
				System.err.println("Unable to delete due to an error: " + me);
			}
		}

	}
}
