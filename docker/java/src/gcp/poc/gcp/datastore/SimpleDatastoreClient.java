package gcp.poc.gcp.datastore;

import com.google.cloud.datastore.Batch;
import com.google.cloud.datastore.Batch.Response;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;

public class SimpleDatastoreClient {

	public static void main(String[] args) {


		System.out.println("Using GOOGLE_APPLICATION_CREDENTIALS path -> "+System.getProperty("GOOGLE_APPLICATION_CREDENTIALS"));
		
		Datastore datastore = DatastoreOptions.newBuilder().setProjectId(System.getenv("DS_PROJECT_ID")).build().getService();
			    			    
		System.out.println("Using Datastore from project "+datastore.getOptions().getProjectId());
			    
		Batch batch = datastore.newBatch();
		Response resp = batch.submit();
				
	}
	
}
