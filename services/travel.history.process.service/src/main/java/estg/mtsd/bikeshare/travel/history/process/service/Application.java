package estg.mtsd.bikeshare.travel.history.process.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableEurekaClient
@EnableMongoRepositories
@EnableKafka
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
