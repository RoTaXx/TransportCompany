package org.example.entity;

import org.example.entity.enums.VehicleType;

import javax.persistence.*;

@Entity
@Table
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    VehicleType vehicleType;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    Company company;

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", vehicleType=" + vehicleType +
                ", company=" + company +
                '}';
    }
}
