package org.example.dto;

import org.example.entity.Employee;
import org.example.entity.TransportCompany;
import org.example.entity.enums.TransportationType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransportDTO {

    private long transport_id;

    private String startingPoint;
    private String destinationPoint;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal transport_weight;
    private BigDecimal transport_price;
    private TransportCompany transportCompany;
    private TransportationType transportationType;
    private Employee employee;

    public TransportDTO(long transport_id, String startingPoint, String destinationPoint, LocalDate startDate, LocalDate endDate, BigDecimal transport_weight, BigDecimal transport_price, TransportCompany transportCompany, TransportationType transportationType, Employee employee) {
        this.transport_id = transport_id;
        this.startingPoint = startingPoint;
        this.destinationPoint = destinationPoint;
        this.startDate = startDate;
        this.endDate = endDate;
        this.transport_weight = transport_weight;
        this.transport_price = transport_price;
        this.transportCompany = transportCompany;
        this.transportationType = transportationType;
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "TransportDTO{" +
                "transport_id=" + transport_id +
                ", startingPoint='" + startingPoint + '\'' +
                ", destinationPoint='" + destinationPoint + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", transport_weight=" + transport_weight +
                ", transport_price=" + transport_price +
                ", transportCompany=" + transportCompany +
                ", transportationType=" + transportationType +
                ", employee=" + employee +
                '}';
    }
}
