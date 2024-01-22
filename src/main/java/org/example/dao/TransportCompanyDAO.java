package org.example.dao;

import org.example.configuration.SessionFactoryUtil;
import org.example.dto.ClientDTO;
import org.example.dto.EmployeeDTO;
import org.example.dto.TransportDTO;
import org.example.dto.VehicleDTO;
import org.example.entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class TransportCompanyDAO {
    public static void saveTransportCompany(TransportCompany transportCompany) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(transportCompany);
            transaction.commit();
        }
    }

    public static void saveOrUpdateTransportCompany(TransportCompany transportCompany) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(transportCompany);
            transaction.commit();
        }
    }

    public static void saveTransportCompanies(List<TransportCompany> transportCompanyList) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            transportCompanyList.stream().forEach(session::save);
            transaction.commit();
        }
    }

    public static List<TransportCompany> getTransportCompanies() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT c FROM TransportCompany c", TransportCompany.class).getResultList();
        }
    }

    public static TransportCompany getTransportCompany(long id) {
        TransportCompany transportCompany;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            transportCompany = session.get(TransportCompany.class, id);
            transaction.commit();
        }
        return transportCompany;
    }

    public static void deleteCompany(TransportCompany transportCompany) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(transportCompany);
            transaction.commit();
        }
    }

    public static List<ClientDTO> getTransportCompanyClients(long id) {
        List<ClientDTO> clients;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            clients = session.createQuery(
                            "select new org.example.dto.ClientDTO(e.client_id, e.firstName, e.lastName, e.hasPaid) from Client e" +
                                    " join e.transportCompanies c " +
                                    "where c.id = :id",
                            ClientDTO.class)
                    .setParameter("id", id)
                    .getResultList();
            transaction.commit();
        }
        return clients;
    }


    public static List<EmployeeDTO> getCompanyEmployees(long id) {
        List<EmployeeDTO> employees;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employees = session.createQuery(
                            "select new org.example.dto.EmployeeDTO(e.employee_id, e.firstName, e.lastName, e.employeeQualification, e.employee_salary) from Employee e" +
                                    " join e.transportCompany c " +
                                    "where c.id = :id",
                            EmployeeDTO.class)
                    .setParameter("id", id)
                    .getResultList();
            transaction.commit();
        }
        return employees;
    }


    public static List<VehicleDTO> getCompanyVehicles(int id) {
        List<VehicleDTO> vehicles;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            vehicles = session.createQuery(
                            "select new org.example.dto.VehicleDTO(e.vehicle_id, e.vehicleType) from Vehicle e" +
                                    " join e.transportCompany c " +
                                    "where c.id = :id",
                            VehicleDTO.class)
                    .setParameter("id", id)
                    .getResultList();
            transaction.commit();
        }
        return vehicles;
    }

    public static List<TransportDTO> getCompanyTransports(long id) {
        List<TransportDTO> transports;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            transports = session.createQuery(
                            "select new org.example.dto.TransportDTO(t.transport_id, t.startingPoint, t.destinationPoint, t.startDate," +
                                    " t.endDate ,t.transport_weight, t.transport_price, t.cost, t.transportationType ) from Transport t" +
                                    " join t.transportCompany c " +
                                    "where c.id = :id",
                            TransportDTO.class)
                    .setParameter("id", id)
                    .getResultList();
            transaction.commit();
        }
        return transports;
    }




    public static List<TransportCompany> sortTransportCompaniesByNameAndIncome(){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<TransportCompany> cr = cb.createQuery(TransportCompany.class);
            Root<TransportCompany> root = cr.from(TransportCompany.class);
            cr.select(root).orderBy(cb.asc(root.get("name")), cb.desc(root.get("income")));

            Query<TransportCompany> query = session.createQuery(cr);
            List<TransportCompany> transportCompanies = query.getResultList();
            return transportCompanies;
        }
    }

    public static List<TransportCompany> sortTransportCompaniesByNameASC() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<TransportCompany> cr = cb.createQuery(TransportCompany.class);
            Root<TransportCompany> root = cr.from(TransportCompany.class);
            cr.select(root).orderBy(cb.asc(root.get("name")));

            Query<TransportCompany> query = session.createQuery(cr);
            List<TransportCompany> transportCompanies = query.getResultList();
            return transportCompanies;
        }
    }

    public static List<TransportCompany> sortTransportCompaniesByIncome() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<TransportCompany> cr = cb.createQuery(TransportCompany.class);
            Root<TransportCompany> root = cr.from(TransportCompany.class);
            cr.select(root).orderBy(cb.asc(root.get("income")));

            Query<TransportCompany> query = session.createQuery(cr);
            List<TransportCompany> transportCompanies = query.getResultList();
            return transportCompanies;
        }
    }
/*
    public static void WriteFile(String FileName, long id){

        Company company = CompanyDAO.getCompany((int) id);
        try (FileWriter fout = new FileWriter(FileName, false)){
            fout.append(company.getName() + ": " + CompanyDAO.getCompany(id) + System.lineSeparator() + company.getName() + " Income: " + CompanyDAO.CalculateCompanyIncome(id) + System.lineSeparator());
            fout.append("Employees of " + company.getName() + ": " + System.lineSeparator() + CompanyDAO.getCompanyEmployeesDTO(id));
            fout.append(System.lineSeparator() + company.getName() + " Clients: " + System.lineSeparator() + CompanyDAO.getCompanyClientsDTO(id));
            fout.append(System.lineSeparator() + company.getName() + " Total Income from transports: " + CompanyDAO.TotalSumOfCompletedTransportsofCompany(id) + System.lineSeparator());


        } catch (IOException e) {
            e.printStackTrace();
        }


    }*/
/*
    public static void ReadFromFile(String FilePath){

        try (BufferedReader br = new BufferedReader(new FileReader(FilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/
}
