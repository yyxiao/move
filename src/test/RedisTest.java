package test;

import com.util.JedisUtil;
import com.util.RedisUtil;
import redis.clients.jedis.Jedis;

/**
 * RedisTest
 * test
 *
 * @author xiaoyy
 * @TODO
 * @Date 2017-09-07 下午4:51
 * The word 'impossible' is not in my dictionary.
 */
public class RedisTest {
    public static void main(String[] args) {
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.getJedis().set("test", "阳阳测试");
        System.out.println(RedisUtil.getJedis().get("test"));
    }
//    public static void main(String[] args) {
//        Jedis jedis = new Jedis("127.0.0.1", 6379);
//        jedis.set("name","lala");//向key-->name中放入了value-->lala
//        System.out.println(jedis.get("name"));
//    }
}
