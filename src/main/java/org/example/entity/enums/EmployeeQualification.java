package org.example.entity.enums;

public enum EmployeeQualification {
    FLAMABLE_LOAD("FLAMMABLE LOAD"),
    TOXIC_LOAD("TOXIC LOAD"),
    MORE_THAN_12_PEOPLE("MORE THAN 12 PEOPLE");

    private final String name;

    EmployeeQualification(String name) {
        this.name = name;
    }
}
