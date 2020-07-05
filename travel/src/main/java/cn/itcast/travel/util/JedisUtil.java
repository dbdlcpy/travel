package cn.itcast.travel.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Jedis工具类
 */
public final class JedisUtil {
    private static JedisPool jedisPool;

    static {
        //Load the properties
        InputStream is = JedisPool.class.getClassLoader().getResourceAsStream("jedis.properties");
        //Create Properties Object
        Properties pro = new Properties();
        //Load
        try {
            pro.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Grab the configuration and set them to JedisPoolConfig
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(Integer.parseInt(pro.getProperty("maxTotal")));
        config.setMaxIdle(Integer.parseInt(pro.getProperty("maxIdle")));

        //Init JedisPool
        jedisPool = new JedisPool(config, pro.getProperty("host"), Integer.parseInt(pro.getProperty("port")));


    }


    /**
     * Get connection method
     */
    public static Jedis getJedis() {
        return jedisPool.getResource();
    }

    /**
     * CLose Jedis
     */
    public static void close(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}
