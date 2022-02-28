package com.egen.weather1.service.kafka;

import com.egen.weather1.model.Weather;
import com.egen.weather1.service.EmailService;
import com.egen.weather1.service.impl.EmailServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService {
    EmailServiceImpl emailService;

    public ConsumerService(EmailServiceImpl emailService){
        this.emailService=emailService;
    }
    @KafkaListener(containerFactory = "kafkaListenerContainerFactory",
    topics = "${kafka.topic.string-demo.name}",
    groupId = "${kafka.topic.string-demo.groupId}")
    public void consume(String message){ log.info(String.format("$$$$ => Consumed Message: %s", message));}

    @KafkaListener(containerFactory = "jsonKafkaListenerContainerFactory",
            topics = "${kafka.topic.json-demo.name}",
            groupId = "${kafka.topic.json-demo.groupId}")
    public void consumeCustomerData(Weather weather){
        log.info("Consumed Message: {}, {}", weather.getId(), weather.getCity());
        System.out.println("Email Notification:"+ weather.getId() +weather.getCity()+weather.getWind().toString());
        emailService.sendSimpleMessage("jaymish9558@gmail.com", "Weather", weather.toString());
    }
}
