package com.shubh.kafkachat.consumer;

import com.shubh.kafkachat.constants.KafkaConstants;
import com.shubh.kafkachat.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MessageListener {
    @Autowired
    SimpMessagingTemplate template;

    List<Message> messages = new ArrayList<>(50);

    @KafkaListener(
            topics = KafkaConstants.KAFKA_TOPIC,
            groupId = KafkaConstants.GROUP_ID
    )
    public void listen(Message message) {
        messages.add(message);
        System.out.println(messages.size());
        System.out.println(messages);
        if (messages.size() == 50) {
            template.convertAndSend("/topic/group", messages);
            messages.clear();
        }

    }
}
