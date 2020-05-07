package gcp.poc.kafka.streams;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.json.JSONArray;

import gcp.poc.kafka.streams.model.FirestoreEntity;
import gcp.poc.kafka.streams.model.StriimRecord;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Reads a Kafka stream and does CRUD into GCP Cloud Memorysotre (Redis).
 * 
 * For a quick introduction to Jedis - https://www.baeldung.com/jedis-java-redis-client-library
 * 
 * @see https://cloud.google.com/memorystore/docs/redis/connect-redis-instance-gce
 * @author duke2
 */
public class KafkaToMemorystoreStream {

	public static void main(String[] args) throws IOException {

		JedisPool jedisPool = createJedisPool();

		// Set up the configuration.
		final Properties props = new Properties();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-replication-to-memorystore");
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, System.getenv("KAFKA_BROKER") + ":9092");
		props.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 0);
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());

		// Get the source stream.
		final StreamsBuilder builder = new StreamsBuilder();
		final KStream<String, String> source = builder.stream("bucket");
		
		source.peek((key, value) -> {

			try {
				
				JSONArray striimRecords = new JSONArray(value);
					
				try (Jedis jedis = jedisPool.getResource()) {
			    					
					striimRecords.forEach(item -> {

						StriimRecord striimRecord = new StriimRecord(item.toString());
						
						FirestoreEntity firestoreEntity = FirestoreEntity.createFirestoreEntity(striimRecord);
	
						switch (striimRecord.getOperation()) {
						case INSERT:	
						case UPDATE:
							jedis.set(firestoreEntity.getNamespace(), firestoreEntity.getJson());
							String cachedResponse = jedis.get(firestoreEntity.getNamespace());
							System.out.println("INSERT/UPDATE -> "+jedis.get(firestoreEntity.getNamespace()));
							break;
						case DELETE:
							String namespace = firestoreEntity.getNamespace();
							jedis.del(namespace);
							System.out.println("DELETE -> "+namespace+" - "+jedis.get(firestoreEntity.getNamespace()));
							break;
						default:
							break;
						}
						

						
				
					});
					
					
					System.out.println("Keys in db: "+jedis.dbSize());
					
					jedis.close();
				
				} catch(Exception excpt) {
					excpt.printStackTrace();
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
		
		System.out.println("Started "+KafkaToMemorystoreStream.class.getCanonicalName());

		try {
			streams.start();
			latch.await();
		} catch (final Throwable e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		System.exit(0);

	}

	private static final JedisPool createJedisPool() throws IOException {

		String host;
		Integer port;

		host = System.getenv("REDIS_HOST");
		port = Integer.valueOf(System.getProperty("redis.port", "6379"));

		JedisPoolConfig poolConfig = new JedisPoolConfig();
		// Default : 8, consider how many concurrent connections into Redis you will
		// need under load
		poolConfig.setMaxTotal(128);

		return new JedisPool(poolConfig, host, port);
	}

}
