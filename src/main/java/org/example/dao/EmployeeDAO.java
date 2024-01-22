package org.example.dao;

import org.example.configuration.SessionFactoryUtil;
import org.example.dto.TransportDTO;
import org.example.entity.Employee;
import org.example.entity.enums.EmployeeQualification;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.hibernate.query.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.sql.Driver;
import java.util.List;

public class EmployeeDAO {
    public static void saveEmployee(Employee employee) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        }
    }

    public static void saveOrUpdateEmployee(Employee employee) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(employee);
            transaction.commit();
        }
    }

    public static Employee getEmployee(int id) {
        Transaction transaction;
        Employee employee;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            employee = session.get(Employee.class, id);
            transaction.commit();
        }
        return employee;
    }

    public static void saveEmployees(List<Employee> employeeList) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employeeList.stream().forEach((com) -> session.save(com));
            transaction.commit();
        }
    }

    public static List<Employee> getEmployees() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT a FROM Employee a", Employee.class).list();
        }
    }

    public static Employee loadEmployee (int id) {
        Transaction transaction;
        Employee employee;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            employee = session.load(Employee.class, id);
            transaction.commit();
        }
        return employee;
    }

    public static Employee getEmployeeById (int id) {
        Transaction transaction;
        Employee employee;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            employee = session.byId(Employee.class).getReference(id);
            transaction.commit();
        }
        return employee;
    }

    public static List<Employee> sortEmployeesByQualificationAndSalary() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Employee> cr = cb.createQuery(Employee.class);
            Root<Employee> root = cr.from(Employee.class);
            cr.select(root).orderBy(cb.asc(root.get("employeeQualification")), cb.desc(root.get("employee_salary")));

            Query<Employee> query = session.createQuery(cr);
            List<Employee> employees = query.getResultList();
            return employees;
        }
    }

    public static List<Employee> sortEmployeesByQualification() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Employee> cr = cb.createQuery(Employee.class);
            Root<Employee> root = cr.from(Employee.class);
            cr.select(root).orderBy(cb.asc(root.get("employeeQualification")));

            Query<Employee> query = session.createQuery(cr);
            List<Employee> employees = query.getResultList();
            return employees;
        }
    }

    public static List<Employee> sortEmployeesBySalary() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Employee> cr = cb.createQuery(Employee.class);
            Root<Employee> root = cr.from(Employee.class);
            cr.select(root).orderBy(cb.desc(root.get("employee_salary")));

            Query<Employee> query = session.createQuery(cr);
            List<Employee> employees = query.getResultList();
            return employees;
        }
    }

    public static List<Employee> filterEmployeesByQualification(EmployeeQualification employeeQualification) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Employee> cr = cb.createQuery(Employee.class);
            Root<Employee> root = cr.from(Employee.class);
            cr.select(root).where(cb.equal(root.get("employeeQualification"), employeeQualification));

            Query<Employee> query = session.createQuery(cr);
            List<Employee> employees = query.getResultList();
            return employees;
        }
    }

    public static List<Employee> filterEmployeesBySalary(BigDecimal employeeSalary) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Employee> cr = cb.createQuery(Employee.class);
            Root<Employee> root = cr.from(Employee.class);
            cr.select(root).where(cb.equal(root.get("employee_salary"), employeeSalary));

            Query<Employee> query = session.createQuery(cr);
            List<Employee> employees = query.getResultList();
            return employees;
        }
    }

/*    public static List<TransportDTO> getEmployeeTransports(long id) {
        List<TransportDTO> transports;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            transports = session.createQuery(
                            "select new dto.TransportDTO(t.transport_id, " +
                                    "t.startingPoint, t.destinationPoint, " +
                                    "t.startDate, t.endDate, " +
                                    "t.transport_weight, t.transport_price, t.transportationType) from Transport t" +
                                    "join t.employee e " +
                                    "where e.employee_id = :id",
                            TransportDTO.class)
                    .setParameter("employee_id", id)
                    .getResultList();
            transaction.commit();
        }

        return transports;
    }*/


}
