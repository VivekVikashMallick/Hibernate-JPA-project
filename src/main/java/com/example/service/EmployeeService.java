package com.example.service;

import com.example.entity.Employee;
import com.example.repo.EmployeeRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EmployeeService {
    private final EmployeeRepo repo = new EmployeeRepo();

    // Insert using Hibernate Session API
    public void insertEmployeeByCore(Employee employee) {

        Transaction tx = null;

        try (Session session = repo.getSession()) {

            tx = session.beginTransaction();

            session.persist(employee);

            tx.commit();

            System.out.println(
                    "Employee inserted using Hibernate Session!"
            );

        } catch (RuntimeException e) {

            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            throw e;
        }
    }

    // Insert using JPA EntityManager API
    public void insertEmployeeByJPA(Employee employee) {

        EntityManager em = EmployeeRepo.getEm();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            em.persist(employee);

            tx.commit();

            System.out.println(
                    "Employee inserted using JPA EntityManager!"
            );

        } catch (RuntimeException e) {

            if (tx.isActive()) {
                tx.rollback();
            }

            throw e;
        }
    }

}

