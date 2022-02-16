package com.egen.weather1.awsMessaging;

import com.amazonaws.services.sns.AmazonSNS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class WeatherAlertSns {

    private final NotificationMessagingTemplate notificationMessagingTemplate;
    @Value("${weather.alert.topic}")
    private String topic;

    @Autowired
    public WeatherAlertSns(NotificationMessagingTemplate notificationMessagingTemplate) { //AmazonSNS amazonSns) {
        this.notificationMessagingTemplate = notificationMessagingTemplate;
    }

    public void send(String subject, String message) {
        this.notificationMessagingTemplate.sendNotification(topic, message, subject);
    }
}
