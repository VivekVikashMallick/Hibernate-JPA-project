package com.example.repo;

import com.example.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EmployeeRepo {
    private static final SessionFactory SF;
    private static final EntityManagerFactory EMF;
    private static final EntityManager EM;

    static {
        SF = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        EMF = Persistence.createEntityManagerFactory("jpa-example");
        EM = EMF.createEntityManager();
    }
    // JPA EntityManager
    public static EntityManager getEm() {
        return EM;
    }
    // Hibernate Session
    public Session getSession() {
        return SF.openSession();
    }

    public static void closeResources() {
        if (EM.isOpen()) {
            EM.close();
        }

        if (EMF.isOpen()) {
            EMF.close();
        }

        if (!SF.isClosed()) {
            SF.close();
        }
    }
}
