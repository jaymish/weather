package com.example.kafkademo.kafka.producer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.connect.json.JsonSerializer;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class JsonKafkaProducerExample {

    private final static String TOPIC = "com-egen-bootcamp-multiple-partitions-json";
    private final static String BOOTSTRAP_SERVERS = "localhost:9092";
    private final static String CLIENT_ID_CONFIG = "json-producer-gagan";

    private final static ObjectMapper objectMapper = new ObjectMapper();

    private static Producer<String, JsonNode> createProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, CLIENT_ID_CONFIG);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());

        //Set acknowledgements for producer requests.
        props.put(ProducerConfig.ACKS_CONFIG, "all");

        //If the request fails, the producer can automatically retry,
        props.put(ProducerConfig.RETRIES_CONFIG, 2);

        //Specify buffer size in config
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);

        //Reduce the no of requests less than 0
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);

        //The buffer.memory controls the total amount of memory available to the producer for buffering.
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);


        // Transactional
        props.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "transactional-gaganQWERTY");
        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
        props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 1);

        return new KafkaProducer<>(props);
    }

    static void runProducer(final int sendMessageCount) throws Exception {
        final Producer<String, JsonNode> producer = createProducer();
        List<String> partionKeyList = Arrays.asList("KEY1", "KEY2", "KEY3", "KEY4", "KEY5", "KEY6", "KEY7", "KEY8", "KEY9", "KEY0");
        try {
            producer.initTransactions();
            producer.beginTransaction();
            for (int index = 0; index < sendMessageCount; index++) {
                final ProducerRecord<String, JsonNode> record = new ProducerRecord<>(TOPIC, partionKeyList.get(new Random().nextInt(partionKeyList.size())),
                        objectMapper.readValue(new File("./src/main/resources/userData.json"), JsonNode.class));

                RecordMetadata metadata = producer.send(record).get();
                System.out.printf("sent record(key=%s value=%s) " + "meta(partition=%d, offset=%d)\n",
                        record.key(), record.value(), metadata.partition(), metadata.offset());
//                if(index == 5)
//                    throw new KafkaException(); //To Demo Uncommitted messages
            }
            producer.commitTransaction();
        } catch (KafkaException exception) {
            System.out.println("Transaction aborted");
            producer.abortTransaction();
        } finally {
            producer.flush();
            producer.close();
        }
    }

    public static void main(String[] args) throws Exception {
        runProducer(20);
    }
}
