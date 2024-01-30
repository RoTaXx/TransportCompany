package org.example.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "transportCompany")
public class TransportCompany {

    @Id
    @Column(name = "company_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long company_id;

    @Column(name="company_name", nullable = false)
    @NotBlank(message = "Transport company name cannot be blank!")
    @Size(max = 20, message = "Transport company name has to be within 20 characters!")
    @Pattern(regexp = "^([A-Z]).*", message = "Transport company name has to start with capital letter!")
    private String name;

    @Column(name="income", nullable = false)
    private BigDecimal income;

    @OneToMany(mappedBy = "transportCompany", cascade = CascadeType.ALL)
    private List<Vehicle> vehicles;

    @OneToMany(mappedBy = "transportCompany", cascade = CascadeType.ALL)
    private List<Employee> employees;

    @OneToMany(mappedBy = "transportCompany", cascade = CascadeType.ALL)
    private List<Transport> transports;

    @ManyToMany
    private List<Client> clients;

    public TransportCompany() {

    }

    public TransportCompany(long company_id, String name, BigDecimal income) {
        this.company_id = company_id;
        this.name = name;
        this.income = income;
    }

    public TransportCompany(String name, BigDecimal income) {
        this.name = name;
        this.income = income;
    }

    public TransportCompany(long company_id, String name, BigDecimal income, List<Vehicle> vehicles, List<Employee> employees, List<Transport> transports, List<Client> clients) {
        this.company_id = company_id;
        this.name = name;
        this.income = income;
        this.vehicles = vehicles;
        this.employees = employees;
        this.transports = transports;
        this.clients = clients;
    }

    public long getCompany_id() {
        return company_id;
    }

    public void setCompany_id(long company_id) {
        this.company_id = company_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Transport> getTransports() {
        return transports;
    }

    public void setTransports(List<Transport> transports) {
        this.transports = transports;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return "TransportCompany{" +
                "company_id=" + company_id +
                ", name='" + name + '\'' +
                ", income=" + income +
                ", vehicles=" + vehicles +
                ", employees=" + employees +
                ", transports=" + transports +
                ", clients=" + clients +
                '}';
    }
}
