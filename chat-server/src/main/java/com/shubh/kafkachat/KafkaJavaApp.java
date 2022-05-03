package com.shubh.kafkachat;

import com.shubh.kafkachat.constants.KafkaConstants;
import com.shubh.kafkachat.model.Message;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class KafkaJavaApp {

    public static void main(String[] args) {

        SpringApplication.run(KafkaJavaApp.class, args);

    }
}
