package net.baouz.accountservice;

import net.baouz.accountservice.clients.CustomerRestClient;
import net.baouz.accountservice.entities.BankAccount;
import net.baouz.accountservice.enums.AccountStatus;
import net.baouz.accountservice.enums.AccountType;
import net.baouz.accountservice.repository.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class AccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(BankAccountRepository bankAccountRepository, CustomerRestClient customerRestClient){
		return args -> {
			customerRestClient.allCustomers().forEach(c -> {
				bankAccountRepository.save(
						BankAccount.builder()
								.id(UUID.randomUUID().toString())
								.balance(Math.random()*54643)
								.currency("DA")
								.createdAt(LocalDate.now())
								.accountType(AccountType.CURRENT_ACCOUNT)
								.accountStatus(AccountStatus.CREATED)
								.customerId(c.getId())
								.build()
				);
				bankAccountRepository.save(
						BankAccount.builder()
								.id(UUID.randomUUID().toString())
								.balance(Math.random()*54643)
								.currency("EURO")
								.createdAt(LocalDate.now())
								.accountType(AccountType.SAVING_ACCOUNT)
								.accountStatus(AccountStatus.ACTIVATED)
								.customerId(c.getId())
								.build()
				);
			});

		};
	}
}
