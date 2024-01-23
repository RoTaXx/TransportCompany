package org.example.entity;

import jakarta.validation.constraints.*;
import org.example.entity.enums.TransportationType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "transport")
public class Transport {

    @Id
    @Column(name = "transport_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transport_id;

    @NotBlank(message = "Starting point cannot be blank!")
    @Size(max = 20, message = "Starting point has to be within 20 charaters!")
    @Pattern(regexp = "^([A-Z]).*", message = "Starting point has to start with capital letter!")
    @Column(name = "starting_point", nullable = false)
    private String startingPoint;

    @NotBlank(message = "Destination point cannot be blank!")
    @Size(max = 20, message = "Destination point has to be within 20 charaters!")
    @Pattern(regexp = "^([A-Z]).*", message = "Destination point has to start with capital letter!")
    @Column(name = "destination_point", nullable = false)
    private String destinationPoint;

    @Column(name = "startdate", nullable = false)
    private LocalDate startDate;

    @Column(name = "enddate", nullable = false)
    private LocalDate endDate;

    public void setEndDate(LocalDate endDate) {
        if (endDate != null && startDate != null && endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date must be after start date");
        }
        this.endDate = endDate;
    }

    @Positive
    @DecimalMax(value = "100000.00", message = "Cargo weight has to be less than 100000.00 kg (100 Tons)")
    @Column(name = "transport_weight")
    private BigDecimal transport_weight;

    @Positive
    @Column(name = "transport_price", nullable = false)
    private BigDecimal transport_price;

    @ManyToOne (fetch = FetchType.LAZY)
    private TransportCompany transportCompany;

    @Column(name = "transportation_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransportationType transportationType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;

    @OneToMany(mappedBy = "transport")
    private List<Client> clients;

    @ManyToOne(fetch = FetchType.LAZY)
    private Vehicle vehicle;




    public Transport() {

    }

    public Transport(long transport_id, String startingPoint, String destinationPoint, LocalDate startDate, LocalDate endDate, BigDecimal transport_weight, BigDecimal transport_price, TransportCompany transportCompany, TransportationType transportationType, Employee employee, List<Client> clients, Vehicle vehicle) {
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
        this.clients = clients;
        this.vehicle = vehicle;
    }

    public long getTransport_id() {
        return transport_id;
    }

    public void setTransport_id(long transport_id) {
        this.transport_id = transport_id;
    }

    public String getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(String startingPoint) {
        this.startingPoint = startingPoint;
    }

    public String getDestinationPoint() {
        return destinationPoint;
    }

    public void setDestinationPoint(String destinationPoint) {
        this.destinationPoint = destinationPoint;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public BigDecimal getTransport_weight() {
        return transport_weight;
    }

    public void setTransport_weight(BigDecimal transport_weight) {
        this.transport_weight = transport_weight;
    }

    public BigDecimal getTransport_price() {
        return transport_price;
    }

    public void setTransport_price(BigDecimal transport_price) {
        this.transport_price = transport_price;
    }

    public TransportCompany getTransportCompany() {
        return transportCompany;
    }

    public void setTransportCompany(TransportCompany transportCompany) {
        this.transportCompany = transportCompany;
    }

    public TransportationType getTransportationType() {
        return transportationType;
    }

    public void setTransportationType(TransportationType transportationType) {
        this.transportationType = transportationType;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return "Transport{" +
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
                ", clients=" + clients +
                ", vehicle=" + vehicle +
                '}';
    }
}
