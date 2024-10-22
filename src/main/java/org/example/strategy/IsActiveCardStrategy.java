package org.example.strategy;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.example.config.EntityManagerConfig;
import org.example.dao.entity.Card;

import java.util.Scanner;

public class IsActiveCardStrategy implements Strategy {
    @Override
    public Card.CardBuilder operate() {
        EntityManager em = EntityManagerConfig.em;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select operation: ");
        System.out.println("1 - Block card");
        System.out.println("2 - Unblock card");
        int selectOperation = scanner.nextInt();

        em.getTransaction().begin();
        boolean isActive = false;

        if (selectOperation == 1) {
            isActive = false; // Block card
            System.out.println("Select block with id or card number: ");
        } else if (selectOperation == 2) {
            isActive = true; // Unblock card
            System.out.println("Select unblock with id or card number: ");
        } else {
            System.out.println("Invalid operation!");
            em.getTransaction().rollback();
            return null;
        }

        System.out.println("1 - by id");
        System.out.println("2 - by card number");
        int selectMethod = scanner.nextInt();

        Card card = null;
        if (selectMethod == 1) {
            System.out.println("Enter card id: ");
            int id = scanner.nextInt();
            card = em.find(Card.class, id);
        } else if (selectMethod == 2) {
            System.out.println("Enter card email: ");
            String email = scanner.next();
            try {
                card = (Card) em.createQuery("SELECT c FROM Card c WHERE c.cardNumber = :email")
                        .setParameter("email", email)
                        .getSingleResult();
            } catch (NoResultException e) {
                System.out.println("Card not found with this email!");
            }
        } else {
            System.out.println("Invalid operation!");
            em.getTransaction().rollback();
            return null;
        }

        if (card != null) {
            card.setIsActive(isActive);
            em.merge(card);
            System.out.println(isActive ? "Card unblocked" : "Card blocked");
        } else {
            em.getTransaction().rollback();
            return null;
        }

        em.getTransaction().commit();
        return null;
    }
}
