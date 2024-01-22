package org.example.entity;

import org.example.entity.enums.VehicleType;
import javax.persistence.*;


@Entity
@Table
public class Vehicle {

    @Id
    @Column(name = "vehicle_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long vehicle_id;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @ManyToOne (fetch = FetchType.LAZY)
    private TransportCompany transportCompany;

    public Vehicle() {
    }

    public Vehicle(long vehicle_id, VehicleType vehicleType, TransportCompany transportCompany) {
        this.vehicle_id = vehicle_id;
        this.vehicleType = vehicleType;
        this.transportCompany = transportCompany;
    }

    public Vehicle(long vehicle_id, VehicleType vehicleType) {
        this.vehicle_id = vehicle_id;
        this.vehicleType = vehicleType;
    }

    public long getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(long vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public TransportCompany getTransportCompany() {
        return transportCompany;
    }

    public void setTransportCompany(TransportCompany transportCompany) {
        this.transportCompany = transportCompany;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + vehicle_id +
                ", vehicleType=" + vehicleType +
                ", company=" + transportCompany +
                '}';
    }
}
