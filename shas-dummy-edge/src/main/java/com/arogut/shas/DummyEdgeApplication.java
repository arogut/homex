package com.arogut.shas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component
public class DummyEdgeApplication implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(DummyEdgeApplication.class);

	private final RabbitTemplate rabbitTemplate;
	private final ConfigurableApplicationContext context;

	public DummyEdgeApplication(RabbitTemplate rabbitTemplate,
                                ConfigurableApplicationContext context) {
		this.rabbitTemplate = rabbitTemplate;
		this.context = context;
	}

	@Override
	public void run(String... strings) throws Exception {
		LOGGER.info("Sending message >>>>");
		rabbitTemplate.convertAndSend("message-in", "{}");
		context.close();
	}

	public static void main(String[] args) {
		SpringApplication.run(DummyEdgeApplication.class, args);
	}

}
