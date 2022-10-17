package com.nisumpractice.reportgeneration.data;

import com.nisumpractice.reportgeneration.model.User;
import com.nisumpractice.reportgeneration.repository.UserRepository;
import lombok.extern.java.Log;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@Log
public class dataLoader implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Faker faker;

    @Override
    public void run(String... args) throws Exception {
        log.info("Loading Sample Data");
        AtomicReference<String> name = null;
        List<User> users = IntStream.rangeClosed(1, 50).
                mapToObj(value -> User.builder()
                        .userId(faker.name().username())
                        .cardHolderName(faker.name().fullName())
                        .cardType(faker.subscription().paymentMethods())
                        .cardNumber(faker.idNumber().valid())
                        .expiryDate(faker.number().numberBetween(1,12)+"/"+faker.number().numberBetween(18,26))
                        .cvv(faker.number().numberBetween(111, 999))
                        .emailId(faker.name().username().toString() + "@gmail.com")
                        .phoneNumber(faker.phoneNumber().cellPhone())
                        .build()).collect(Collectors.toList());
        userRepository.saveAll(users);
    }
}
