package org.example.dto;
import org.example.entity.TransportCompany;
import org.example.entity.enums.VehicleType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class VehicleDTO {
    private long vehicle_id;

    private VehicleType vehicleType;

    private TransportCompany transportCompany;

    public VehicleDTO() {
    }

    public VehicleDTO(long vehicle_id, VehicleType vehicleType, TransportCompany transportCompany) {
        this.vehicle_id = vehicle_id;
        this.vehicleType = vehicleType;
        this.transportCompany = transportCompany;
    }

    public VehicleDTO(long vehicle_id, VehicleType vehicleType) {
        this.vehicle_id = vehicle_id;
        this.vehicleType = vehicleType;
    }

    @Override
    public String toString() {
        return "VehicleDTO{" +
                "vehicle_id=" + vehicle_id +
                ", vehicleType=" + vehicleType +
                ", transportCompany=" + transportCompany +
                '}';
    }
}
