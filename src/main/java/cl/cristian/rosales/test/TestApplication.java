package cl.cristian.rosales.test;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "User API", version = "2.0", description = "User Information"))
public class TestApplication {
	private static final Logger logger = LogManager.getLogger(TestApplication.class);
	public static void main(String[] args) {
		logger.info("Log4J !!!");
		SpringApplication.run(TestApplication.class, args);
	}

}
