package krywojciechowski.storage;

import java.sql.DatabaseMetaData;

import javax.xml.crypto.Data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import krywojciechowski.storage.Interfaces.Cacheable;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;

/**
 * Hello world!
 *
 */
public class Storage extends AbstractStorage
{

	private Jedis jedis;
	
	
	 public Storage(String host, int port) {
		 this.jedis = new Jedis(new HostAndPort(host, port));
	}
	
	@Override
	public void saveInCache(Cacheable cacheable) {
		this.jedis.set(cacheable.getCacheKey(),new Gson().toJson(cacheable));
	}

	@Override
	public Object find(Cacheable cacheable) {
		String data;
		if((data = this.jedis.get(cacheable.getCacheKey())) != null ) {
			return new Gson().fromJson(data, cacheable.getClass());
		} else return null;
	}

}
