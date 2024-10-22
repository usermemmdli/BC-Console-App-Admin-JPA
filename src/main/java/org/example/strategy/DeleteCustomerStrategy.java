package org.example.strategy;

import jakarta.persistence.EntityManager;
import org.example.config.EntityManagerConfig;
import org.example.dao.entity.Card;

import java.util.Scanner;

public class DeleteCustomerStrategy implements Strategy {
    @Override
    public Card.CardBuilder operate() {
        EntityManager em = EntityManagerConfig.em;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select delete with id or email: ");
        System.out.println("1 - delete with id");
        System.out.println("2 - delete with email");
        int select = scanner.nextInt();
        em.getTransaction().begin();
        if (select == 1) {
            System.out.println("Enter customer id: ");
            int id = scanner.nextInt();
            Card card = em.find(Card.class, id);
            em.remove(card);
            System.out.println("Customer deleted");

        } else if (select == 2) {
            System.out.println("Enter customer email: ");
            String email = scanner.next();
            Card card = em.find(Card.class, email);
            em.remove(card);
            System.out.println("Customer deleted");

        } else {
            System.out.println("Invalid operation!");
        }
        em.getTransaction().commit();
        return null;
    }
}
