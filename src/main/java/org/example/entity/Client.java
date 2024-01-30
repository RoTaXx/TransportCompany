package org.example.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @Column(name = "client_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long client_id;

    @Column(name="firstName", nullable = false)
    @NotBlank(message = "Client first name cannot be blank!")
    @Size(max = 20, message = "Client first name has to be within 20 characters!")
    @Pattern(regexp = "^([A-Z]).*", message = "Client first name has to start with capital letter!")
    private String firstName;

    @Column(name="lastName", nullable = false)
    @NotBlank(message = "Client last name cannot be blank!")
    @Size(max = 20, message = "Client last name has to be within 20 characters!")
    @Pattern(regexp = "^([A-Z]).*", message = "Client last name has to start with capital letter!")
    private String lastName;

    @Column(name = "hasPaid", nullable = false)
    private boolean hasPaid;

    @ManyToMany(mappedBy = "clients")
    private List<TransportCompany> transportCompanies;

    @ManyToOne(fetch = FetchType.LAZY)
    private Transport transport;

    public Client(long client_id, String firstName, String lastName, boolean hasPaid, List<TransportCompany> transportCompanies, Transport transport) {
        this.client_id = client_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hasPaid = hasPaid;
        this.transportCompanies = transportCompanies;
        this.transport = transport;
    }

    public Client(long client_id, String firstName, String lastName, boolean hasPaid) {
        this.client_id = client_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hasPaid = hasPaid;
    }

    public Client() {

    }

    public long getClient_id() {
        return client_id;
    }

    public void setClient_id(long client_id) {
        this.client_id = client_id;
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

    public boolean isHasPaid() {
        return hasPaid;
    }

    public void setHasPaid(boolean hasPaid) {
        this.hasPaid = hasPaid;
    }

    public List<TransportCompany> getTransportCompanies() {
        return transportCompanies;
    }

    public void setTransportCompanies(List<TransportCompany> transportCompanies) {
        this.transportCompanies = transportCompanies;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    @Override
    public String toString() {
        return "Client{" +
                "client_id=" + client_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", hasPaid=" + hasPaid +
                ", transportCompanies=" + transportCompanies +
                ", transport=" + transport +
                '}';
    }
}
