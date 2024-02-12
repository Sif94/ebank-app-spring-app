package net.baouz.accountservice.clients;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import net.baouz.accountservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRestClient {
    @GetMapping("/customers/{id}")
    @CircuitBreaker(name = "customerService", fallbackMethod = "getDefaultCustomer")
    Customer findCustomerById(@PathVariable Long id);
    @GetMapping("/customers")
    @CircuitBreaker(name = "customerService", fallbackMethod = "getAllDefaultCustomers")
    List<Customer> allCustomers();

    default Customer getDefaultCustomer(Long id, Exception exception){
        return Customer.builder()
                .id(id)
                .email("NOT_AVAILABLE")
                .firstName("NOT_AVAILABLE")
                .lastName("NOT_AVAILABLE")
                .build();
    }

    default List<Customer> getAllDefaultCustomers(Exception exception){
        return List.of();
    }
}
