package org.example.dto;

import org.example.entity.Transport;

public class ClientDTO {
    private long client_id;

    private String firstName;
    private String lastName;
    private boolean hasPaid;

    private Transport transport;

    public ClientDTO() {
    }

    public ClientDTO(long client_id, String firstName, String lastName, boolean hasPaid, Transport transport) {
        this.client_id = client_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hasPaid = hasPaid;
        this.transport = transport;
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
                "client_id=" + client_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", hasPaid=" + hasPaid +
                ", transport=" + transport +
                '}';
    }
}
