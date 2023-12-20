package com.supecars.cardealermanagement.service.impl;

import com.supecars.cardealermanagement.dao.SaleDao;
import com.supecars.cardealermanagement.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class SaleServiceImplTest {

    @Mock
    private SaleDao saleDao;

    @InjectMocks
    private SaleServiceImpl saleService;

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    private Sale sale1, sale2, updatedSale;
    private Customer customer1, customer2;
    private Car car1, car2;
    private Employee employee1, employee2;

    @BeforeEach
    void setUp() throws ParseException {

        customer1 = new Customer("Adam", "Nowak", "Ny veien", "Tonsberg", "3230", "Norway", "45632474", "a.nowak@email.com", 1);
        customer2 = new Customer("Julie", "Nygaard", "Vest byen", "Holmestrand", "3080", "Norway", "79632874", "julie@email.com", 2);

        car1 = new Car("VIN1", "Brand1", "Model1", "Diesel", 2.4, 165, "Color1", 2023, TransmissionType.AUTOMATIC, new BigDecimal("420000"), true, 50);
        car2 = new Car("VIN2", "Brand2", "Model2", "Electric", 75.0, 150, "Color2", 2021, TransmissionType.AUTOMATIC, new BigDecimal("350000"), false, 52000);

        Date hireDate1 = format.parse("2022-04-01");
        Date hireDate2 = format.parse("2020-01-01");
        employee1 = new Employee("John", "Smith", "Main street", "Holmestrand", "8080", "Norway", "557734527", "john.smith@example.com", 1, "Sales advisor", new BigDecimal("50000"), hireDate1);
        employee2 = new Employee("Jane", "Summer", "New street", "Holmestrand", "8080", "Norway", "556677890", "jane.summer@example.com", 2, "Sales Manager", new BigDecimal("70000"), hireDate2);

        Date saleDate1 = format.parse("2023-03-01");
        Date saleDate2 = format.parse("2023-03-15");
        BigDecimal salePrice1 = new BigDecimal("420000");
        BigDecimal salePrice2 = new BigDecimal("320000");

        sale1 = new Sale(1, customer1, car1, employee1, saleDate1, salePrice1);
        sale2 = new Sale(2, customer2, car2, employee2, saleDate2, salePrice2);
        updatedSale = new Sale(1, customer2, car1, employee1, saleDate1, salePrice1);
    }

    @Test
    void it_should_give_all_sales() {
        //given
        List<Sale> expectedSales = Arrays.asList(sale1, sale2);
        when(saleDao.findAll()).thenReturn(expectedSales);

        //when
        List<Sale> actualSales = saleService.getAllSales();

        //then
        assertNotNull(actualSales);
        assertEquals(expectedSales.size(), actualSales.size());
        verify(saleDao, times(1)).findAll();
    }

    @Test
    void it_should_give_sale_by_chosen_id() {
        //given
        int saleId = sale1.getSaleId();
        when(saleDao.findById(saleId)).thenReturn(Optional.of(sale1));

        //when
        Optional<Sale> foundSale = saleService.getSaleById(saleId);

        //then
        assertTrue(foundSale.isPresent());
        assertEquals(sale1, foundSale.get());
        verify(saleDao, times(1)).findById(saleId);
    }

    @Test
    void it_should_add_new_sale() {
        //given
        when(saleDao.save(any(Sale.class))).thenReturn(sale1);

        //when
        saleService.addNewSale(sale1);

        //then
        verify(saleDao, times(1)).save(sale1);
    }

    @Test
    void it_should_delete_sale_by_given_id() {
        //given
        int saleId = sale1.getSaleId();
        doNothing().when(saleDao).deleteById(saleId);

        //when
        saleService.deleteSale(saleId);

        //then
        verify(saleDao, times(1)).deleteById(saleId);
    }

    @Test
    void it_should_update_sale_by_given_id() {
        //given
        when(saleDao.findById(sale1.getSaleId())).thenReturn(Optional.of(sale1));
        when(saleDao.save(any(Sale.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        //when
        saleService.updateSale(sale1.getSaleId(), updatedSale);

        //then
        assertEquals(updatedSale.getCustomer(), sale1.getCustomer());
        assertEquals(updatedSale.getCar(), sale1.getCar());
        assertEquals(updatedSale.getEmployee(), sale1.getEmployee());
        assertEquals(updatedSale.getSaleDate(), sale1.getSaleDate());
        assertEquals(updatedSale.getSalePrice(), sale1.getSalePrice());

        verify(saleDao, times(1)).findById(sale1.getSaleId());
        verify(saleDao, times(1)).save(sale1);
    }
}