package org.example.entity;

import jakarta.validation.constraints.*;
import org.example.entity.enums.EmployeeQualification;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @Column(name = "employee_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long employee_id;

    @Column(name="firstName", nullable = false)
    @NotBlank(message = "Employee first name cannot be blank!")
    @Size(max = 20, message = "Employee first name has to be within 20 characters!")
    @Pattern(regexp = "^([A-Z]).*", message = "Employee first name has to start with capital letter!")
    private String firstName;

    @Column(name="lastName", nullable = false)
    @NotBlank(message = "Client last name cannot be blank!")
    @Size(max = 20, message = "Client last name has to be within 20 characters!")
    @Pattern(regexp = "^([A-Z]).*", message = "Client last name has to start with capital letter!")
    private String lastName;

    @Column(name = "employee_qualification", nullable = false)
    @Enumerated(EnumType.STRING)
    private EmployeeQualification employeeQualification;

    @Positive
    @Digits(integer = 5, fraction = 2, message = "Salary has to be with 5 digits before and 2 digits after the decimal separator")
    @Column(name="employee_salary", nullable = false)
    private BigDecimal employee_salary;

    @ManyToOne (fetch = FetchType.LAZY)
    private TransportCompany transportCompany;

    @OneToMany (fetch = FetchType.LAZY)
    private List<Transport> transports;

    public Employee(long employee_id, List<Transport> transports) {
        this.employee_id = employee_id;
        this.transports = transports;
    }

    public Employee(long employee_id, String firstName, String lastName, EmployeeQualification employeeQualification, BigDecimal employee_salary, TransportCompany transportCompany, List<Transport> transports) {
        this.employee_id = employee_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeQualification = employeeQualification;
        this.employee_salary = employee_salary;
        this.transportCompany = transportCompany;
        this.transports = transports;
    }

    public Employee() {

    }

    public long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(long employee_id) {
        this.employee_id = employee_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public EmployeeQualification getEmployeeQualification() {
        return employeeQualification;
    }

    public void setEmployeeQualification(EmployeeQualification employeeQualification) {
        this.employeeQualification = employeeQualification;
    }

    public BigDecimal getEmployee_salary() {
        return employee_salary;
    }

    public void setEmployee_salary(BigDecimal employee_salary) {
        this.employee_salary = employee_salary;
    }

    public TransportCompany getTransportCompany() {
        return transportCompany;
    }

    public void setTransportCompany(TransportCompany transportCompany) {
        this.transportCompany = transportCompany;
    }

    public List<Transport> getTransports() {
        return transports;
    }

    public void setTransports(List<Transport> transports) {
        this.transports = transports;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employee_id=" + employee_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", employeeQualification=" + employeeQualification +
                ", employee_salary=" + employee_salary +
                ", transportCompany=" + transportCompany +
                '}';
    }
}
