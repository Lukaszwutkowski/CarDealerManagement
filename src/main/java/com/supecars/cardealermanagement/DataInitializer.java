package com.supecars.cardealermanagement;

import com.supecars.cardealermanagement.dao.*;
import com.supecars.cardealermanagement.model.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

@Component
@Profile("dev")
public class DataInitializer implements CommandLineRunner {

    private CarDao carDao;
    private EmployeeDao employeeDao;
    private CustomerDao customerDao;
    private SaleDao saleDao;
    private ServiceEntryDao serviceEntryDao;
    private InventoryDao inventoryDao;
    public DataInitializer(CarDao carDao, EmployeeDao employeeDao, CustomerDao customerDao, SaleDao saleDao, ServiceEntryDao serviceEntryDao, InventoryDao inventoryDao) {
        this.carDao = carDao;
        this.employeeDao = employeeDao;
        this.customerDao = customerDao;
        this.saleDao = saleDao;
        this.serviceEntryDao = serviceEntryDao;
        this.inventoryDao = inventoryDao;
    }
    @Override
    public void run(String... args) throws Exception {
        createCars();
        createInventories();
        createEmployees();
        createCustomers();
        createSales();
        createServices();
    }
    private void createCars() {
        List<Car> cars = Arrays.asList(
                new Car("VIN12345", "Toyota", "Corolla", "Benzyna", 1.8, 140,"Color1",2020, TransmissionType.AUTOMATIC, false, 50000),
                new Car("VIN12346", "Tesla", "Model S", "Elektryczny", 75.0, 416, "Color2", 2021,TransmissionType.AUTOMATIC, true, 120000),
                new Car("VIN12347", "Ford", "Focus",  "Diesel", 2.0, 150, "Color3", 2019, TransmissionType.MANUAL, false, 130000),
                new Car("VIN12348", "Volkswagen", "Golf", "Diesel", 2.0, 110, "Color4",2018, TransmissionType.MANUAL, false, 40000),
                new Car("VIN12349", "Audi", "A4", "Benzyna", 1.4, 150, "Color5", 2019, TransmissionType.AUTOMATIC, false, 110000),
                new Car("VIN12350", "BMW", "3",  "Hybryda", 2.5, 184, "Color6", 2024, TransmissionType.AUTOMATIC, true, 0),
                new Car("VIN12351", "Mercedes", "C Klasse", "Diesel", 2.2, 170, "Color7", 2017, TransmissionType.AUTOMATIC, false, 260000),
                new Car("VIN12352", "Tesla", "Model Y",  "Elektryczny", 75.0, 440, "Color8", 2024, TransmissionType.AUTOMATIC, true, 0),
                new Car("VIN12353", "Toyota", "Prius Plus", "Hybryda", 1.8, 140, "Color9", 2013,  TransmissionType.AUTOMATIC, false, 165000),
                new Car("VIN12354", "Skoda", "Octavia", "Diesel", 2.0, 204, "Color10", 2021, TransmissionType.MANUAL, false, 34000),
                new Car("VIN12355", "Toyota", "Camry", "Hybryda", 2.0, 170, "Color11", 2024, TransmissionType.AUTOMATIC, true, 0),
                new Car("VIN12356", "Peugeot", "508", "Diesel", 2.0, 130, "Color12", 2021, TransmissionType.MANUAL, false, 25000),
                new Car("VIN12357", "Renault", "Zoe", "Elektryczny", 41.0, 120, "Color13", 2020,  TransmissionType.AUTOMATIC, false, 50000),
                new Car("VIN12358", "Toyota", "Rav4",  "Hybryda", 2.5, 180, "Color14", 2024, TransmissionType.AUTOMATIC, true, 0),
                new Car("VIN12359", "Mercedes", "Model E", "Benzyna", 2.5, 250, "Color15", 2021, TransmissionType.AUTOMATIC, false, 20000)
        );

        carDao.saveAll(cars);
    }

    private void createEmployees() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        employeeDao.save(new Employee("Jan", "Kowalski", "Radhus 1", "Oslo", "0250", "Norwegia", "123456789", "jkowalski@example.com", 1, "Sprzedawca", BigDecimal.valueOf(35000.0), sdf.parse("2021-01-10")));
        employeeDao.save(new Employee("Lars", "Nygaard", "Kjoping 4", "Sandvika", "0290", "Norwegia", "966456789", "lnygaard@example.com", 2, "Sprzedawca", BigDecimal.valueOf(35000.0), sdf.parse("2023-04-05")));
        employeeDao.save(new Employee("Anna", "Nowak", "Radhus 1", "Oslo", "0250", "Norwegia", "987654321", "anowak@example.com", 3, "Kierownik", BigDecimal.valueOf(50000.0), sdf.parse("2020-06-15")));
    }

    private void createCustomers() {
        List<Customer> customers = Arrays.asList(
                new Customer("Lars", "Hansen", "Gamlevei 3", "Bergen", "5003", "Norwegia", "11222333", "lars.hansen@example.com", 1),
                new Customer("Maija", "Vestby", "Nyveien 16", "Oslo", "2003", "Norwegia", "22332433", "m.vest@example.com", 2),
                new Customer("Lucia", "Hammar", "Klovre 4", "Holmestrand", "3080", "Norwegia", "69825343", "lu.hamm@example.com",  3),
                new Customer("Anders", "Roroar", "Sorvei 12B", "Asker", "3045", "Norwegia", "99853412","r.ror@example.com", 4),
                new Customer("Maciej", "Hanicki", "Livvei 323C","Svarstad", "3275", "Norwegia", "98732556", "maciek.hanicki@example.com", 5),
                new Customer("Kamil", "Polski", "Lekia 6", "Sande", "3003", "Norwegia", "09854376", "kami.pol@example.com", 6),
                new Customer("Nina", "Jensen", "Trolltunga 34", "Trondheim", "7010", "Norwegia", "43498556", "nina.jensen@example.com", 7)
        );

        customerDao.saveAll(customers);
    }

    private void createSales() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        saleDao.save(new Sale(1, customerDao.findById(1).orElseThrow(), carDao.findById("VIN12345").orElseThrow(), employeeDao.findById(1).orElseThrow(), sdf.parse("2024-01-15"), new BigDecimal("300000")));
        saleDao.save(new Sale(2, customerDao.findById(2).orElseThrow(), carDao.findById("VIN12346").orElseThrow(), employeeDao.findById(1).orElseThrow(), sdf.parse("2024-01-20"), new BigDecimal("500000")));
        saleDao.save(new Sale(3, customerDao.findById(3).orElseThrow(), carDao.findById("VIN12347").orElseThrow(), employeeDao.findById(2).orElseThrow(), sdf.parse("2024-01-21"), new BigDecimal("200000")));
        saleDao.save(new Sale(4, customerDao.findById(4).orElseThrow(), carDao.findById("VIN12350").orElseThrow(), employeeDao.findById(2).orElseThrow(), sdf.parse("2024-01-24"), new BigDecimal("450000")));
        saleDao.save(new Sale(5, customerDao.findById(5).orElseThrow(), carDao.findById("VIN12351").orElseThrow(), employeeDao.findById(2).orElseThrow(), sdf.parse("2024-01-25"), new BigDecimal("350000")));
        saleDao.save(new Sale(6, customerDao.findById(6).orElseThrow(), carDao.findById("VIN12352").orElseThrow(), employeeDao.findById(2).orElseThrow(), sdf.parse("2024-01-26"), new BigDecimal("550000")));
        saleDao.save(new Sale(6, customerDao.findById(6).orElseThrow(), carDao.findById("VIN12353").orElseThrow(), employeeDao.findById(3).orElseThrow(), sdf.parse("2024-01-27"), new BigDecimal("250000")));
    }

    private void createServices() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


        Car carVIN12354 = carDao.findById("VIN12354").orElseThrow(() -> new RuntimeException("Car not found"));
        Car carVIN12355 = carDao.findById("VIN12355").orElseThrow(() -> new RuntimeException("Car not found"));

        serviceEntryDao.save(new ServiceEntry(0, carVIN12354, sdf.parse("2024-02-05"), "Przegląd techniczny", new BigDecimal("10000")));
        serviceEntryDao.save(new ServiceEntry(0, carVIN12355, sdf.parse("2024-02-10"), "Naprawa silnika", new BigDecimal("15000")));
    }

    private void createInventories() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        inventoryDao.save(new Inventory(0, carDao.findById("VIN12345").orElseThrow(), Status.SOLD, sdf.parse("2023-12-01"), new BigDecimal("250000"), new BigDecimal("300000")));
        inventoryDao.save(new Inventory(0, carDao.findById("VIN12346").orElseThrow(), Status.SOLD, sdf.parse("2023-12-05"), new BigDecimal("450000"), new BigDecimal("500000")));
        inventoryDao.save(new Inventory(0, carDao.findById("VIN12347").orElseThrow(), Status.SOLD, sdf.parse("2023-11-15"), new BigDecimal("150000"), new BigDecimal("200000")));
        inventoryDao.save(new Inventory(0, carDao.findById("VIN12350").orElseThrow(), Status.SOLD, sdf.parse("2023-12-10"), new BigDecimal("400000"), new BigDecimal("450000")));
        inventoryDao.save(new Inventory(0, carDao.findById("VIN12351").orElseThrow(), Status.SOLD, sdf.parse("2023-12-20"), new BigDecimal("300000"), new BigDecimal("350000")));
        inventoryDao.save(new Inventory(0, carDao.findById("VIN12352").orElseThrow(), Status.SOLD, sdf.parse("2024-01-05"), new BigDecimal("500000"), new BigDecimal("550000")));
        inventoryDao.save(new Inventory(0, carDao.findById("VIN12353").orElseThrow(), Status.SOLD, sdf.parse("2024-01-10"), new BigDecimal("200000"), new BigDecimal("250000")));
        inventoryDao.save(new Inventory(0, carDao.findById("VIN12354").orElseThrow(), Status.IN_SERVICE, sdf.parse("2023-11-20"), new BigDecimal("170000"), new BigDecimal("180000")));
        inventoryDao.save(new Inventory(0, carDao.findById("VIN12355").orElseThrow(), Status.IN_SERVICE, sdf.parse("2023-12-01"), new BigDecimal("220000"), new BigDecimal("230000")));
        inventoryDao.save(new Inventory(0, carDao.findById("VIN12348").orElseThrow(), Status.FOR_SALE, sdf.parse("2023-11-25"), new BigDecimal("180000"), new BigDecimal("190000")));
        inventoryDao.save(new Inventory(0, carDao.findById("VIN12349").orElseThrow(), Status.FOR_SALE, sdf.parse("2023-12-15"), new BigDecimal("210000"), new BigDecimal("220000")));
        inventoryDao.save(new Inventory(0, carDao.findById("VIN12356").orElseThrow(), Status.FOR_SALE, sdf.parse("2023-12-30"), new BigDecimal("250000"), new BigDecimal("260000")));
        inventoryDao.save(new Inventory(0, carDao.findById("VIN12357").orElseThrow(), Status.FOR_SALE, sdf.parse("2024-01-15"), new BigDecimal("270000"), new BigDecimal("280000")));
        inventoryDao.save(new Inventory(0, carDao.findById("VIN12358").orElseThrow(), Status.FOR_SALE, sdf.parse("2024-02-01"), new BigDecimal("290000"), new BigDecimal("300000")));
        inventoryDao.save(new Inventory(0, carDao.findById("VIN12359").orElseThrow(), Status.FOR_SALE, sdf.parse("2024-02-15"), new BigDecimal("310000"), new BigDecimal("320000")));
    }

}
