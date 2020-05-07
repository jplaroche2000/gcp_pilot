package gcp.poc.kafka.streams.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;

public class CustomerOrderEntity extends FirestoreEntity {

	private Key key;
	private Integer id;
	private Date orderDate;
	private Integer customerId;	
	private Double total;
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"); // Oracle docker
	SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS"); // Oracle local
	private String json;

	public CustomerOrderEntity(JSONObject data) {
		if(data.has("ID")) {
			id = data.getInt("ID");
		}
		if (data.has("ORDER_DATE")) {
			String stringDate = data.getString("ORDER_DATE");
			try {
				orderDate = format.parse(stringDate);
			} catch (ParseException e) {
				try {
					orderDate = format2.parse(stringDate);
				} catch (ParseException e2) {
					e2.printStackTrace();
					orderDate = new Date();
				}
			}
		}
		if(data.has("CUSTOMER_ID")) {
			customerId = data.getInt("CUSTOMER_ID");
		}
		if(data.has("TOTAL")) {
			total = data.getDouble("TOTAL");
		}
		this.json = data.toString();
	}

	public String getJson() {
		return json;
	}





	public Key getKey() {
		return key;
	}




	public void setKey(Key key) {
		this.key = key;
	}




	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	public Date getOrderDate() {
		return orderDate;
	}




	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}




	public Integer getCustomerId() {
		return customerId;
	}




	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}




	public Double getTotal() {
		return total;
	}




	public void setTotal(Double total) {
		this.total = total;
	}




	@Override
	public Entity newEntity(Datastore datastore) {
		
		Entity customerEntity = Entity.newBuilder(datastore.newKeyFactory()
			    .setKind("CustomerOrder")
			    .newKey(id))
			    .set("ORDER_DATE", orderDate.toString())
			    .set("CUSTOMER_ID", customerId)
			    .set("TOTAL", total)
			    .build();
		
		return customerEntity;
	}


	@Override
	public Key getKey(Datastore datastore) {
		return datastore.newKeyFactory()
			    .setKind("CustomerOrder")
			    .newKey(id);
	}


	@Override
	public Entity updatedEntity(Datastore datastore) {
		Key key = datastore.newKeyFactory()
	    .setKind("CustomerOrder")
	    .newKey(id);
		
		Entity entity = datastore.get(key);
		
		Entity.Builder entityBuilder = Entity.newBuilder(entity);
		
		if(id != null) {
			entityBuilder.set("ID", id);
		}
		if(customerId != null) {
			entityBuilder.set("CUSTOMER_ID", customerId);
		}
		if (orderDate != null) {
			entityBuilder.set("ORDER_DATE", orderDate.toString());
		}
		if(total != null) {
			entityBuilder.set("TOTAL", total);
		}
		
		return entityBuilder.build();
		
	}
	
	@Override
	public String getNamespace() {
		return "/" + this.getClass().getCanonicalName() + "/" + id.toString();
	}
}