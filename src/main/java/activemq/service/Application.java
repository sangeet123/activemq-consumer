package activemq.service;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;

/**
 * Created by sangeet on 4/13/2017.
 */
@SpringBootApplication() @EnableJms()
@ComponentScan(basePackages = { "main.email*", "activemq.service*"})
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean public Queue queue() {
    return new ActiveMQQueue("user-ms");
  }
}
