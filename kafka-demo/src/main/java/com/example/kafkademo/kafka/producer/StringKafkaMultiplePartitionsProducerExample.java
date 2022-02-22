package com.example.kafkademo.kafka.producer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.*;

public class StringKafkaMultiplePartitionsProducerExample {

//    private final static String TOPIC = "multiple-partitions-trail";
    private final static String TOPIC = "com-egen-bootcamp-multiple-partitions-string";
    private final static String BOOTSTRAP_SERVERS = "localhost:9092";
    private final static String CLIENT_ID_CONFIG = "string-producer-gagan";

    private static Producer<String, String> createProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, CLIENT_ID_CONFIG);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return new KafkaProducer<>(props);
    }

    static void runProducer(final int sendMessageCount) throws Exception {
        final Producer<String, String> producer = createProducer();
        List<String> partionKeyList = Arrays.asList("KEY1", "KEY2", "KEY3", "KEY4", "KEY5");
        try {
            for (int index = 0; index < sendMessageCount; index++) {

                final ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, partionKeyList.get(new Random().nextInt(partionKeyList.size())), "Hello! this is sample multi-partition producer message:" + index);

                RecordMetadata metadata = producer.send(record).get();

                System.out.printf("sent record(key=%s value=%s) " + "meta(partition=%d, offset=%d)\n",
                        record.key(), record.value(), metadata.partition(), metadata.offset());
            }
        } finally {
            producer.flush();
            producer.close();
        }
    }

    public static void main(String[] args) throws Exception {
        runProducer(10);
    }

}
