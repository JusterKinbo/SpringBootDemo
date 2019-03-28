package helloword.sp_redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import demo.juster.spboot.SampleController;
import demo.juster.spboot.redis.interfaces.JedisClient;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Slf4j
@RunWith(SpringRunner.class)   
@SpringBootTest(classes={SampleController.class})// 指定启动类
public class TestRedis {

	/**
     * 结合spring的redis单机版测试
     */
    @Test
    public void testSpringSingle(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/config/applicationContext-jedis.xml");
        JedisClient jedisclient = (JedisClient)context.getBean("jedisClientPool");
        
        jedisclient.set("jedisclient", "mytest");
		String result = jedisclient.get("jedisclient");
		log.info(result);
    }
	 
//    @Test
    public void testJedisSingle(){
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.set("test", "this i a test");
        String str  = jedis.get("test");
        log.info("---:"+str);
        //关闭jedis的链接
        jedis.close();
    }
    
    /**
     * 使用连接池
     */
//    @SuppressWarnings("resource")
//	@Test
    public void testJedisPool(){
        JedisPool jedisPool = new JedisPool("127.0.0.1", 6379);
        Jedis jedis = jedisPool.getResource();
        String str = jedis.get("test");
        log.info("---:"+str);
        jedis.close();
    }
}
