package org.example;

import org.example.dao.*;
import org.example.entity.*;
import org.example.entity.enums.EmployeeQualification;
import org.example.entity.enums.TransportationType;
import org.example.entity.enums.VehicleType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        TransportCompany company1 = new TransportCompany(1, "Nestle", BigDecimal.valueOf(1000));
        TransportCompanyDAO.saveTransportCompany(company1);

        TransportCompany company2 = new TransportCompany(2, "Daf", BigDecimal.valueOf(1000));
        TransportCompanyDAO.saveTransportCompany(company2);

        TransportCompany company3 = new TransportCompany("Crop", BigDecimal.valueOf(2300));
        TransportCompanyDAO.saveOrUpdateTransportCompany(company3);


        TransportCompanyDAO.getTransportCompanies();
        TransportCompanyDAO.getTransportCompany(3);

        EmployeeDAO.getEmployees();

        Employee teo = new Employee(1, "Teo", "Georgiev", EmployeeQualification.TOXIC_LOAD,BigDecimal.valueOf(10000),company1);
        EmployeeDAO.saveEmployee(teo);

        EmployeeDAO.getEmployee(1);

        Employee ceco = new Employee(2, "Ceco", "Georgiev", EmployeeQualification.MORE_THAN_12_PEOPLE,BigDecimal.valueOf(10000),company2);
        EmployeeDAO.saveEmployee(ceco);

        Employee sasho = new Employee(2, "Sasho", "Georgiev", EmployeeQualification.FLAMABLE_LOAD,BigDecimal.valueOf(10000),company3);
        EmployeeDAO.saveEmployee(sasho);

        Client client1 = new Client(1, "Client","Georgiev", true);
        List<Client> clientList = new ArrayList(10);
        clientList.add(client1);

        Vehicle vehicle1 = new Vehicle(1, VehicleType.BUS, company1);
        VehicleDAO.getVehicle(1);
        Vehicle vehicle2 = new Vehicle(2, VehicleType.TRUCK, company1);
        Vehicle vehicle3 = new Vehicle(3, VehicleType.CISTERN, company1);

        VehicleDAO.saveVehicle(vehicle1);
        VehicleDAO.saveVehicle(vehicle2);
        VehicleDAO.saveVehicle(vehicle3);
        VehicleDAO.getVehicles();

        Transport transport1 = new Transport(1, "Sofia", "Samokov", LocalDate.of( 2022 , 1 , 13 ), LocalDate.of( 2022 , 1 , 14 ),BigDecimal.valueOf(700),BigDecimal.valueOf(14050), company1, TransportationType.GOODS, ceco, clientList, vehicle2);

        TransportDAO.saveTransport(transport1);
        TransportDAO.getTransports();

        Client slav = new Client(3,"Slav", "Mihaylov", true);
        //ClientDAO.saveOrUpdateClient(slav);

        slav.setHasPaid(false);
        //ClientDAO.saveOrUpdateClient(slav);

        List<TransportCompany> sortingCompanies = new ArrayList<>(10);
        sortingCompanies.add(company1);
        sortingCompanies.add(company2);
        sortingCompanies.add(company3);

        sortingCompanies = TransportCompanyDAO.sortTransportCompaniesByNameAndIncome();
        sortingCompanies.stream().forEach(System.out::println);

        sortingCompanies = TransportCompanyDAO.sortTransportCompaniesByNameASC();
        sortingCompanies.stream().forEach(System.out::println);

        sortingCompanies = TransportCompanyDAO.sortTransportCompaniesByIncome();
        sortingCompanies.stream().forEach(System.out::println);

        List<Transport> transports = new ArrayList(10);
        transports.add(transport1);
        String fileName = "files/Transports.txt";
        TransportDAO.fileCreator(fileName, transports);
        TransportDAO.readFile(fileName);

        for(TransportCompany com: sortingCompanies){
            System.out.println(com.getName() + " = " + TransportDAO.companyTransports(com) + " deliveries.");
        }
    }

}