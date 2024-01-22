package org.example.dto;

import org.example.entity.TransportCompany;
import org.example.entity.enums.EmployeeQualification;

import java.math.BigDecimal;


public class EmployeeDTO {
    private long employee_id;
    private String firstName;
    private String lastName;
    private EmployeeQualification employeeQualification;
    private BigDecimal employee_salary;

    private TransportCompany transportCompany;

    public EmployeeDTO() {
    }

    public EmployeeDTO(long employee_id, String firstName, String lastName, EmployeeQualification employeeQualification, BigDecimal employee_salary, TransportCompany transportCompany) {
        this.employee_id = employee_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeQualification = employeeQualification;
        this.employee_salary = employee_salary;
        this.transportCompany = transportCompany;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "employee_id=" + employee_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", employeeQualification=" + employeeQualification +
                ", employee_salary=" + employee_salary +
                ", transportCompany=" + transportCompany +
                '}';
    }
}
