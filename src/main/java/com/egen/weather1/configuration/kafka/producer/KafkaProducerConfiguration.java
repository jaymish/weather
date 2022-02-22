package com.egen.weather1.configuration.kafka.producer;


import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfiguration {

    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.producer.client-id}")
    private String clientId;

    /*@Value("${spring.kafka.producer.key-serializer}")
    private String keySerializer;

    @Value("${spring.kafka.producer.value-serializer}")
    private String valueSerializer;*/

    @Value("${spring.kafka.producer.acks}")
    private String acks;

    @Value("${spring.kafka.producer.retries}")
    private String retries;

    @Value("${spring.kafka.producer.batch-size}")
    private String batchSize;

    @Value("${spring.kafka.producer.buffer-memory}")
    private String bufferMemory;

    @Value("${spring.kafka.producer.linger-ms}")
    private String lingerMs;

    @Value("${spring.kafka.producer.enable-Idempoyence}")
    private String enableIdempoyence;

    @Value("${spring.kafka.producer.transactionalId}")
    private String transactionalId;

    @Bean
    public ProducerFactory<String, String> producerFactoryString() throws UnknownHostException{
        Map<String , Object> configProps = new HashMap<>();

        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);

        configProps.put(ProducerConfig.CLIENT_ID_CONFIG, clientId+"_"+ InetAddress.getLocalHost().getHostName()+"string");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //Set acknowledgements for producer requests.
        configProps.put(ProducerConfig.ACKS_CONFIG, acks);
        //If the request fails, the producer can automatically retry,
        configProps.put(ProducerConfig.RETRIES_CONFIG, retries);
        //Specify buffer size in config
        configProps.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
        //Reduce the no of requests less than 0
        configProps.put(ProducerConfig.LINGER_MS_CONFIG, lingerMs);
        //The buffer.memory controls the total amount of memory available to the producer for buffering.
        configProps.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);
        configProps.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, enableIdempoyence);
        configProps.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, transactionalId);

        DefaultKafkaProducerFactory<String, String> factory = new DefaultKafkaProducerFactory<>(configProps);
        if(transactionalId != null){
            factory.setTransactionIdPrefix(transactionalId+"_"+InetAddress.getLocalHost().getHostName());
            factory.setProducerPerConsumerPartition(false);
        }

        return factory;
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplateString() throws UnknownHostException {
        return new KafkaTemplate<>(producerFactoryString());
    }
}