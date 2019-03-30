package demo.juster.spboot.kafka;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import demo.juster.spboot.pojo.msg.Message;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaSender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private ObjectMapper mapper = new ObjectMapper();

    //发送消息方法
    public void send() {
    	Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg(UUID.randomUUID().toString());
        message.setSendTime(new Date());
        String json = null;
        try {
            json = mapper.writeValueAsString(message);
        } catch (Exception e) {
            e.printStackTrace();
            json = "no value";
        }
        log.info("+++++++++++++++++++++  message = {}", json);
        kafkaTemplate.send("sp_kafka", json);
    }
}