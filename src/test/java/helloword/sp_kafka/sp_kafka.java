package helloword.sp_kafka;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import demo.juster.spboot.SampleController;
import demo.juster.spboot.kafka.KafkaReceiver;
import demo.juster.spboot.kafka.KafkaSender;
import helloword.sp_redis.TestRedis;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.JedisCluster;

@Slf4j
@RunWith(SpringRunner.class)   
@SpringBootTest(classes={SampleController.class})// 指定启动类
public class sp_kafka {

	@Autowired
	KafkaSender sender;
	
	@Autowired
	KafkaReceiver receiver;
	
    @Test
    public void testKafka() {

         for (int i = 0; i < 3; i++) {
             //调用消息发送类中的消息发送方法
             sender.send();

             try {
                 Thread.sleep(3000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }		
    }
}
