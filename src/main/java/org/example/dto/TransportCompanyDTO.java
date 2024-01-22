package org.example.dto;

import java.math.BigDecimal;

public class TransportCompanyDTO {
    private long company_id;
    private String name;
    private BigDecimal income;

    public TransportCompanyDTO(long company_id, String name, BigDecimal income) {
        this.company_id = company_id;
        this.name = name;
        this.income = income;
    }

    @Override
    public String toString() {
        return "TransportCompanyDTO{" +
                "company_id=" + company_id +
                ", name='" + name + '\'' +
                ", income=" + income +
                '}';
    }
}
