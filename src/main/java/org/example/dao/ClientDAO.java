package org.example.dao;


import org.example.configuration.SessionFactoryUtil;
import org.example.entity.Client;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class ClientDAO {
    public static void saveClient(Client client) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(client);
            transaction.commit();
        }
    }

    public static void saveOrUpdateClient(Client client) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(client);
            transaction.commit();
        }
    }

    public static Client getClient(long id) {
        Client client;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            client = session.get(Client.class, id);
            transaction.commit();
        }
        return client;
    }

    public static void saveClients(List<Client> clientList) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            clientList.stream().forEach((com) -> session.save(com));
            transaction.commit();
        }
    }

    public static List<Client> getClients() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT a FROM Client a", Client.class).list();
        }
    }

    public static Client getClientById(int id) {
        Client client;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            client = session.byId(Client.class).getReference(id);
            transaction.commit();
        }
        return client;
    }

    public static void deleteClient(Client client) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(client);
            transaction.commit();
        }
    }

}
