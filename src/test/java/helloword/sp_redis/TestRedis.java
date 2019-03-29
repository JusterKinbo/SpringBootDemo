package helloword.sp_redis;

import java.io.IOException;
import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import demo.juster.spboot.SampleController;
import demo.juster.spboot.redis.interfaces.JedisClient;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;


@Slf4j
@RunWith(SpringRunner.class)   
@SpringBootTest(classes={SampleController.class})// 指定启动类
public class TestRedis {

	/**
     * 结合spring的jedis集群版
	 * @throws IOException 
     */
    @Test
    public void testSpringJedisCluster() throws IOException{
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/config/spring-redis-cluster.xml");
        JedisCluster jedisCluster = (JedisCluster)context.getBean(JedisCluster.class);
        jedisCluster.set("key2", "3333");
        String str = jedisCluster.get("key2");
        System.out.println("--:"+str);
        jedisCluster.close();
    }
	
//	@Test
    public void testJedisCluster() throws IOException{
        HashSet<HostAndPort> nodes = new HashSet<HostAndPort>();
        nodes.add(new HostAndPort("127.0.0.1", 7001));
        nodes.add(new HostAndPort("127.0.0.1", 7002));
        nodes.add(new HostAndPort("127.0.0.1", 7003));
        nodes.add(new HostAndPort("127.0.0.1", 7004));
        nodes.add(new HostAndPort("127.0.0.1", 7005));
        nodes.add(new HostAndPort("127.0.0.1", 7006));
        JedisCluster cluster = new JedisCluster(nodes);
        cluster.set("jedisClusterKey", "hello_world");
        String str = cluster.get("jedisClusterKey");
        System.out.println("---:"+str);
        //关闭连接
        cluster.close();
    }
//	@Test
	public void testJedisTemplate()
	{
	ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
            "classpath:/config/spring-redis-sentinel.xml");
    RedisTemplate<String, String> template = (RedisTemplate<String, String>) context.getBean("redisTemplate");
    template.opsForValue().set("aaa", "aaabbb");
    System.err.println(template.opsForValue().get("aaa"));
    }
	/**
     * 结合spring的redis单机版测试
     */
//    @Test
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
