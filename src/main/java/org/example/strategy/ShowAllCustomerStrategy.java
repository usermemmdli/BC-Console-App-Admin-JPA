package org.example.strategy;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.example.config.EntityManagerConfig;
import org.example.dao.entity.Card;
import org.example.dao.entity.Customer;

import java.util.List;
import java.util.Scanner;

public class ShowAllCustomerStrategy implements Strategy{

    @Override
    public Card.CardBuilder operate() {
        EntityManager em = EntityManagerConfig.em;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name: ");
        String name = scanner.next();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        Root<Customer> rootEntry = cq.from(Customer.class);
        Predicate isActivePredicate = cb.equal(rootEntry.get("name"),name);
        CriteriaQuery<Customer> all = cq.where(isActivePredicate);
        List<Customer> customerList = em.createQuery(all).getResultList();
        System.out.println(customerList);
        return null;
    }
}
