package com.assigment.bank.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "User")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends VersionedPersistence {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private UUID id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_number")
    private Long userNumber;

    @Column(name = "status")
    private String status;

    @JoinColumn(name = "address_id")
    @OneToOne(cascade = CascadeType.ALL)
    private AddressEntity userAddress;

    @JoinColumn(name = "contact_id")
    @OneToOne(cascade = CascadeType.ALL)
    private ContactEntity contactDetails;

    @JoinColumn(name = "account_id")
    @OneToOne(cascade = CascadeType.ALL)
    private AccountEntity savingsAccount;

    @OneToOne
    @JoinColumn(name = "bank_id")
    private BankEntity bank;

    @Column(name = "create_date")
    @Temporal(TemporalType.TIME)
    private Date createDateTime;

    @PrePersist
    protected void onCreate() {
        this.createDateTime = new Date();
    }
}
