package com.kenny.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Title: Redis Utility Class
 * @author Kenny
 */
@Component
public class RedisOperator {
	
//	@Autowired
//    private RedisTemplate<String, Object> redisTemplate;
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	// Key operations, simple key-value operations

	/**
	 * Command implementation: TTL key, returns the remaining time to live of a key in seconds.
	 * 
	 * @param key
	 * @return
	 */
	public long ttl(String key) {
		return redisTemplate.getExpire(key);
	}
	
	/**
	 * Command implementation: expire - set expiration time in seconds
	 * 
	 * @param key
	 * @return
	 */
	public void expire(String key, long timeout) {
		redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
	}
	
	/**
	 * Command implementation: INCR key - increment key by one
	 * 
	 * @param key
	 * @return
	 */
	public long incr(String key, long delta) {
		return redisTemplate.opsForValue().increment(key, delta);
	}

	/**
	 * Command implementation: KEYS pattern - find all keys matching the given pattern
	 */
	public Set<String> keys(String pattern) {
		return redisTemplate.keys(pattern);
	}

	/**
	 * Command implementation: DEL key - delete a key
	 * 
	 * @param key
	 */
	public void del(String key) {
		redisTemplate.delete(key);
	}

	// String operations

	/**
	 * Command implementation: SET key value - set a key-value pair (associate string value with key)
	 * 
	 * @param key
	 * @param value
	 */
	public void set(String key, String value) {
		redisTemplate.opsForValue().set(key, value);
	}

	/**
	 * Command implementation: SET key value EX seconds - set key-value with timeout in seconds
	 * 
	 * @param key
	 * @param value
	 * @param timeout
	 *            (in seconds)
	 */
	public void set(String key, String value, long timeout) {
		redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
	}

	/**
	 * Command implementation: GET key - return the string value associated with the key
	 * 
	 * @param key
	 * @return value
	 */
	public String get(String key) {
		return (String)redisTemplate.opsForValue().get(key);
	}

	/**
	 * Batch query, corresponds to mget
	 * @param keys
	 * @return
	 */
	public List<String> mget(List<String> keys) {
		return redisTemplate.opsForValue().multiGet(keys);
	}

	/**
	 * Batch query using pipeline
	 * @param keys
	 * @return
	 */
	public List<Object> batchGet(List<String> keys) {

//		nginx -> keepalive
//		redis -> pipeline

		List<Object> result = redisTemplate.executePipelined(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				StringRedisConnection src = (StringRedisConnection)connection;

				for (String k : keys) {
					src.get(k);
				}
				return null;
			}
		});

		return result;
	}


	// Hash operations

	/**
	 * Command implementation: HSET key field value - set the value of field in hash table key to value
	 * 
	 * @param key
	 * @param field
	 * @param value
	 */
	public void hset(String key, String field, Object value) {
		redisTemplate.opsForHash().put(key, field, value);
	}

	/**
	 * Command implementation: HGET key field - return the value of field in hash table key
	 * 
	 * @param key
	 * @param field
	 * @return
	 */
	public String hget(String key, String field) {
		return (String) redisTemplate.opsForHash().get(key, field);
	}

	/**
	 * Command implementation: HDEL key field [field ...] - delete one or more specified fields from hash table key, non-existent fields will be ignored
	 * 
	 * @param key
	 * @param fields
	 */
	public void hdel(String key, Object... fields) {
		redisTemplate.opsForHash().delete(key, fields);
	}

	/**
	 * Command implementation: HGETALL key - return all fields and values in hash table key
	 * 
	 * @param key
	 * @return
	 */
	public Map<Object, Object> hgetall(String key) {
		return redisTemplate.opsForHash().entries(key);
	}

	// List operations

	/**
	 * Command implementation: LPUSH key value - insert value at the head of list key
	 * 
	 * @param key
	 * @param value
	 * @return length of the list after LPUSH command
	 */
	public long lpush(String key, String value) {
		return redisTemplate.opsForList().leftPush(key, value);
	}

	/**
	 * Command implementation: LPOP key - remove and return the first element of list key
	 * 
	 * @param key
	 * @return first element of list key
	 */
	public String lpop(String key) {
		return (String)redisTemplate.opsForList().leftPop(key);
	}

	/**
	 * Command implementation: RPUSH key value - insert value at the tail (rightmost) of list key
	 * 
	 * @param key
	 * @param value
	 * @return length of the list after RPUSH command
	 */
	public long rpush(String key, String value) {
		return redisTemplate.opsForList().rightPush(key, value);
	}

}