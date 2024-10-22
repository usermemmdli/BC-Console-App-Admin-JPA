package org.example.strategy;

import jakarta.persistence.EntityManager;
import org.example.config.EntityManagerConfig;
import org.example.dao.entity.Card;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Scanner;

public class CreateCardStrategy implements Strategy{
    private static final EntityManager em =  EntityManagerConfig.em;
    @Override
    public Card.CardBuilder operate() {
        Card card = buildCard();
        em.getTransaction().begin();
        em.persist(card);
        em.getTransaction().commit();
        return null;
    }
    private Card buildCard(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name: ");
        String name = scanner.next();
        System.out.println("Enter card number: ");
        String cardNumber = scanner.next();
        System.out.println("Enter pin: ");
        String pin = scanner.next();
        System.out.println("Enter CCY code: ");
        String ccyCode = scanner.next();
        System.out.println("Enter holder name: ");
        String holderName = scanner.next();

        return Card.builder()
                .name(name)
                .cardNumber(cardNumber)
                .pin(pin)
                .ccyCode(ccyCode)
                .holderName(holderName)
                .isActive(true)
                .createdAt(Timestamp.from(Instant.now()))
                .updatedAt(null)
                .build();
    }
}
