package net.baouz.accountservice.entities;

import jakarta.persistence.*;
import lombok.*;
import net.baouz.accountservice.enums.AccountStatus;
import net.baouz.accountservice.enums.AccountType;
import net.baouz.accountservice.model.Customer;

import java.time.LocalDate;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class BankAccount {

    @Id
    private String id;

    private LocalDate createdAt;

    private double balance;

    private String currency;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    private Long customerId;
    @Transient
    private Customer customer;
}
