package gcp.poc.gcp.memorystore;

import java.io.IOException;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Simple Redis put/get
 * 
 * @see https://cloud.google.com/memorystore/docs/redis/connect-redis-instance-gce
 * @author duke2
 *
 */
public class SimpleRedisClient {

	
	public static void main(String[] args) throws IOException {

		JedisPool jedisPool = createJedisPool();

		Long visits;

		try (Jedis jedis = jedisPool.getResource()) {
			visits = jedis.incr("visits");
		}
		
		System.out.println("Visits incremented to: "+visits);

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
