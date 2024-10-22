package org.example.strategy;

import org.example.config.EntityManagerConfig;
import org.example.dao.entity.Card;
import org.example.dao.entity.Customer;

import jakarta.persistence.EntityManager;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Scanner;

public class CreateCustomerStrategy implements Strategy {
    private static final EntityManager em =  EntityManagerConfig.em;
    @Override
    public Card.CardBuilder operate() {
        Customer customer = buildCustomer();
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
        return null;
    }

    private Customer buildCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name: ");
        String name = scanner.next();
        System.out.println("Enter surname: ");
        String surname = scanner.next();
        System.out.println("Enter email: ");
        String email = scanner.next();

        return Customer.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .createdAt(Timestamp.from(Instant.now()))
                .updatedAt(null)
                .build();
    }
}
