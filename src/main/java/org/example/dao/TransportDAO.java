package org.example.dao;

import org.example.configuration.SessionFactoryUtil;
import org.example.dto.ClientDTO;
import org.example.entity.Employee;
import org.example.entity.Transport;
import org.example.entity.TransportCompany;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class TransportDAO {
    public static void saveTransport (Transport transport) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(transport);
            transaction.commit();
        }
    }

    public static void saveOrUpdateTransport (Transport transport) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(transport);
            transaction.commit();
        }
    }

    public static Transport getTransport (long id) {
        Transaction transaction = null;
        Transport transport;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            // get Company entity using get() method
            transport = session.get(Transport.class, id);
            transaction.commit();
        }
        return transport;
    }

    public static void saveTransports (List<Transport> transportList) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            transportList.stream().forEach(session::save);
            transaction.commit();
        }
    }

    public static List<Transport> getTransports() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT a FROM Transport a", Transport.class).list();
        }
    }

    public static Transport getTransportById (int id) {
        Transport transport;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            // get Company entity using byId() method
            transport = session.byId(Transport.class).getReference(id);
            transaction.commit();
        }
        return transport;
    }

    public static void deleteTransport (Transport transport) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(transport);
            transaction.commit();
        }
    }

    public static List<Transport> sortDeliveriesByDestination(){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Transport> cr = cb.createQuery(Transport.class);
            Root<Transport> root = cr.from(Transport.class);
            cr.select(root).orderBy(cb.asc(root.get("destinationPoint")));

            Query<Transport> query = session.createQuery(cr);
            List<Transport> transports = query.getResultList();
            return transports;
        }
    }

    public static List<Transport> filterTransportsByDestinationPoint(String destinationPoint) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Transport> cr = cb.createQuery(Transport.class);
            Root<Transport> root = cr.from(Transport.class);
            cr.select(root).where(cb.equal(root.get("destinationPoint"), destinationPoint));

            Query<Transport> query = session.createQuery(cr);
            List<Transport> transports = query.getResultList();
            return transports;
        }
    }

    public static BigDecimal employeeIncomes(Employee employee){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<BigDecimal> query = cb.createQuery(BigDecimal.class);

            Root<Transport> root = query.from(Transport.class);
            query.select(cb.sum(root.get("transport_price"))).where(cb.equal(root.get("employee"), employee));

            Query<BigDecimal> qr = session.createQuery(query);
            BigDecimal income = qr.getSingleResult();
            return income;
        }
    }

    public static Long employeeNumberOfTransports(Employee employee) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Long> query = cb.createQuery(Long.class);
            Root<Transport> root = query.from(Transport.class);
            query.select(cb.count(root)).where(cb.equal(root.get("employee"), employee));

            Query<Long> qr = session.createQuery(query);
            Long numberOfDeliveries = qr.getSingleResult();
            return numberOfDeliveries;
        }
    }


    public static BigDecimal companyIncomesFromTransports(TransportCompany transportCompany) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<BigDecimal> query = cb.createQuery(BigDecimal.class);
            Root<Transport> root = query.from(Transport.class);

            query.where(cb.equal(root.get("transportCompany"),
                    transportCompany)).select(cb.sum(root.get("transport_price"))).getSelection();
            Query<BigDecimal> qr = session.createQuery(query);
            BigDecimal income = qr.getSingleResult();
            return income;
        }
    }


    public static List<Transport> companyTransports(TransportCompany transportCompany) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Transport> cr = cb.createQuery(Transport.class);
            Root<Transport> root = cr.from(Transport.class);
            cr.select(root).where(cb.equal(root.get("transportCompany"), transportCompany));

            Query<Transport> query = session.createQuery(cr);
            List<Transport> numberOfTransports = query.getResultList();
            return numberOfTransports;
        }
    }

    public static List<ClientDTO> transportClients(long transport_id) {
        List<ClientDTO> clients;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            clients = session.createQuery(
                            "select new org.example.dto.ClientDTO(c.client_id, c.firstName, c.lastName) from Client c" +
                                    " join c.transport d " +
                                    "where d.transport_id = :transport_id",
                            ClientDTO.class)
                    .setParameter("transport_id", transport_id)
                    .getResultList();
            transaction.commit();
        }
        return clients;
    }

    public static List<Transport> transportsWithArrivalDateBetween(LocalDate from, LocalDate to) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Transport> cr = cb.createQuery(Transport.class);
            Root<Transport> root = cr.from(Transport.class);
            cr.select(root).where(cb.between(root.get("endDate"), to, from));

            Query<Transport> query = session.createQuery(cr);
            List<Transport> transports = query.getResultList();
            return transports;
        }
    }

    public static List<Transport> transportsWithArrivalDateGreaterThan(LocalDate localDate) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Transport> cr = cb.createQuery(Transport.class);
            Root<Transport> root = cr.from(Transport.class);
            cr.select(root).where(cb.greaterThan(root.get("endDate"), localDate));

            Query<Transport> query = session.createQuery(cr);
            List<Transport> transports = query.getResultList();
            return transports;
        }
    }

    public static List<TransportCompany> companiesWithIncomeBetween(double top, double bottom) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<TransportCompany> cr = cb.createQuery(TransportCompany.class);
            Root<TransportCompany> root = cr.from(TransportCompany.class);
            cr.select(root).where(cb.between(root.get("income"), top, bottom));

            Query<TransportCompany> query = session.createQuery(cr);
            List<TransportCompany> companies = query.getResultList();
            return companies;
        }
    }


    public static void fileCreator(String fileName, List<Transport> deliveries) {
        try (FileWriter file = new FileWriter(fileName)) {
            for (Transport d : deliveries) {
                file.write(d + System.lineSeparator());
            }
        } catch (IOException fileNot) {
            fileNot.printStackTrace();
        }
    }

    public static void readFile(String fileName) {
        String currentLine;
        try (FileReader fis = new FileReader(fileName)) {
            BufferedReader bufferedReader = new BufferedReader(fis);
            while ((currentLine = bufferedReader.readLine()) != null) {
                System.out.println(currentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
