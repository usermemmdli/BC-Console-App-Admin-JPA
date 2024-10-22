package org.example.strategy;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.example.config.EntityManagerConfig;
import org.example.dao.entity.Card;
import org.example.dao.entity.Transactions;

import java.util.List;
import java.util.Scanner;

public class ShowAllTransactionsStrategy implements Strategy{
    @Override
    public Card.CardBuilder operate() {
        EntityManager em = EntityManagerConfig.em;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter sender card number: ");
        String senderCardNumber = scanner.next();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Transactions> cq = cb.createQuery(Transactions.class);
        Root<Transactions> rootEntry = cq.from(Transactions.class);
        Predicate isActivePredicate = cb.equal(rootEntry.get("senderCardNumber"),senderCardNumber);
        CriteriaQuery<Transactions> all = cq.where(isActivePredicate);
        List<Transactions> transactionsList = em.createQuery(all).getResultList();
        System.out.println(transactionsList);
        return null;
    }
}
