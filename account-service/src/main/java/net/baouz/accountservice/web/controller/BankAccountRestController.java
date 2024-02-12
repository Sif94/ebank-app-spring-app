package net.baouz.accountservice.web.controller;

import net.baouz.accountservice.clients.CustomerRestClient;
import net.baouz.accountservice.entities.BankAccount;
import net.baouz.accountservice.model.Customer;
import net.baouz.accountservice.repository.BankAccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class BankAccountRestController {
    private final BankAccountRepository bankAccountRepository;
    private final CustomerRestClient customerRestClient;

    public BankAccountRestController(BankAccountRepository bankAccountRepository, CustomerRestClient customerRestClient) {
        this.bankAccountRepository = bankAccountRepository;
        this.customerRestClient = customerRestClient;
    }

    @GetMapping
    public ResponseEntity<List<BankAccount>> getAllBankAccounts(){
        List<BankAccount> bankAccountList = bankAccountRepository.findAll();
        bankAccountList.forEach(ba -> ba.setCustomer(customerRestClient.findCustomerById(ba.getCustomerId())));
        return new ResponseEntity<List<BankAccount>>(bankAccountList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccount> getBankAccountById(@PathVariable String id){
        Optional<BankAccount> bankAccount = bankAccountRepository.findById(id);
        if (!bankAccount.isPresent()){
            throw new RuntimeException("Bank Account not found");
        }
        BankAccount bankAccount1 = bankAccount.get();
        Customer customer = customerRestClient.findCustomerById(bankAccount1.getCustomerId());
        bankAccount1.setCustomer(customer);
        return new ResponseEntity<BankAccount>(bankAccount1, HttpStatus.OK);
    }



}
