package com.egen.weather1.service.kafka;

import com.google.common.util.concurrent.ListenableFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class ProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTemplate<String, String> customerKafkaTemplate;

    @Value("${kafka.topic.string-demo.name}")
    private String STRING_TOPIC;

    @Value("${kafka.topic.string-demo.groupId}")
    private String JSON_TOPIC;

    public ProducerService(KafkaTemplate<String, String> kafkaTemplate, KafkaTemplate<String, String> customerKafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.customerKafkaTemplate = customerKafkaTemplate;
    }

    public void sendMessage(String message){
        log.info(String.format("$$$$ => Consumed Message: %s", message));
        List<String> keyList = Arrays.asList("KEY1","KEY2","KEY3","KEY4","KEY5");

        kafkaTemplate.executeInTransaction(t -> {
             t.send(STRING_TOPIC, keyList.get(new Random().nextInt(keyList.size())), message);

            return true;
        });
    }

}
