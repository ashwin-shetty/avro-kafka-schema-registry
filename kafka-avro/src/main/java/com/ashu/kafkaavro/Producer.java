package com.ashu.kafkaavro;

import com.ashu.Employee;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@CommonsLog(topic = "Producer Logger")
public class Producer {

  @Value("${topic.name}")
  private String TOPIC;

  private final KafkaTemplate<String, Employee> kafkaTemplate;

  @Autowired
  public Producer(KafkaTemplate<String, Employee> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  void sendMessage(Employee employee) {
    this.kafkaTemplate.send(this.TOPIC, employee.getName(), employee);
    log.info(String.format("Producer Logger ,Produced user -> %s", employee));
  }
}
