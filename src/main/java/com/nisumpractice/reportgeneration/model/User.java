package com.nisumpractice.reportgeneration.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String cardHolderName;
    private String cardType;
    private String cardNumber;
    private String  expiryDate;
    private Integer cvv;
    private String emailId;
    private String phoneNumber;


}
