package gcp.poc.kafka.streams;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.json.JSONArray;

import com.google.cloud.datastore.Batch;
import com.google.cloud.datastore.Batch.Response;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.datastore.v1.QueryResultBatch;

import gcp.poc.kafka.streams.model.FirestoreEntity;
import gcp.poc.kafka.streams.model.StriimRecord;

/**
 * Reads a Kafka stream and does batch inserts into GCP Cloud Datastore.
 * 
 * @see https://cloud.google.com/datastore/docs/reference/libraries#client-libraries-usage-java
 * @author duke2
 */
public class KafkaToFirestoreStream {

	private static String[] kinds = new String[] {	
			"Customer",
			"CustomerOrder",
			"CustomerOrderItem",
			"Address",
			"AddressLink"
	};
	
	
	public static void main(String[] args) {

		// Set up the configuration.
		final Properties props = new Properties();
	
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-replication-to-datastore");
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, System.getenv("KAFKA_BROKER") + ":9092");
		props.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 0);
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
		
		// Get the source stream.
		final StreamsBuilder builder = new StreamsBuilder();
		final KStream<String, String> source = builder.stream("bucket");

		System.out.println("Using GOOGLE_APPLICATION_CREDENTIALS path -> "+System.getenv("GOOGLE_APPLICATION_CREDENTIALS"));
		
		source.peek((key, value) -> {

			try {
				
				JSONArray striimRecords = new JSONArray(value);
				
			    Datastore datastore = DatastoreOptions.newBuilder().setProjectId(System.getenv("DS_PROJECT_ID")).build().getService();
			    			    
			    System.out.println("Using Datastore from project "+datastore.getOptions().getProjectId());
			    
			    Batch batch = datastore.newBatch();
			    
			    System.out.println("Processing message payload: "+value);
			    
				striimRecords.forEach(item -> {

					StriimRecord striimRecord = new StriimRecord(item.toString());
					
					FirestoreEntity firestoreEntity = FirestoreEntity.createFirestoreEntity(striimRecord);

					switch (striimRecord.getOperation()) {
					case INSERT:							
						firestoreEntity.persist(batch);
						break;
					case UPDATE:
						firestoreEntity.update(batch);
						break;
					case DELETE:
						firestoreEntity.delete(batch);
						break;
					default:
						break;
					}
					
			
				});
				
				Response resp = batch.submit();
				
				System.out.println("Bath submitted");
				
				
				for (String kind : kinds) {
					
					QueryResults<Key> result = datastore.run(Query.newKeyQueryBuilder().setKind(kind).build());
					int count = 0;
				    while (result.hasNext()) {
				    	Key entityKey = result.next();
				        count++;
				    }
				    System.out.println(kind+" entity count -> "+count);
				    
				}
				
				

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

		final Topology topology = builder.build();
		final KafkaStreams streams = new KafkaStreams(topology, props);
		// Print the topology to the console.
		System.out.println(topology.describe());
		final CountDownLatch latch = new CountDownLatch(1);

		// Attach a shutdown handler to catch control-c and terminate the application
		// gracefully.
		Runtime.getRuntime().addShutdownHook(new Thread("streams-wordcount-shutdown-hook") {
			@Override
			public void run() {
				streams.close();
				latch.countDown();
			}
		});
		
		System.out.println("Started "+KafkaToFirestoreStream.class.getCanonicalName());

		try {
			streams.start();
			latch.await();
		} catch (final Throwable e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		System.exit(0);
	}

}