package net.baouz.customerservice;

import net.baouz.customerservice.config.GlobalConfig;
import net.baouz.customerservice.entities.Customer;
import net.baouz.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(GlobalConfig.class)
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
		return args -> {
			customerRepository.save(
					Customer.builder()
							.email("customer1@gmail.com")
							.lastName("customer1")
							.firstName("customer1")
							.build()
			);
			customerRepository.save(
					Customer.builder()
							.email("customer2@gmail.com")
							.lastName("customer2")
							.firstName("customer2")
							.build()
			);
			customerRepository.save(
					Customer.builder()
							.email("customer3@gmail.com")
							.lastName("customer3")
							.firstName("customer3")
							.build()
			);
		};
	}
}
