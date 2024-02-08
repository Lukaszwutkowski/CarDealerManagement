package com.supecars.cardealermanagement.service.impl;

import com.supecars.cardealermanagement.dao.InventoryDao;
import com.supecars.cardealermanagement.model.Car;
import com.supecars.cardealermanagement.model.Inventory;
import com.supecars.cardealermanagement.model.Status;
import com.supecars.cardealermanagement.model.TransmissionType;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InventoryServiceImplTest {

    @InjectMocks
    private InventoryServiceImpl inventoryService;

    @Mock
    private InventoryDao inventoryDao;

    private Inventory inventory1, inventory2, updatedInventory;

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @BeforeEach
    void setUp() throws ParseException {
        Date acquisitionDate1 = format.parse("2023-12-01");
        Date acquisitionDate2 = format.parse("2023-06-12");

        Car car1 = new Car("VIN1", "Brand1", "Model1", "Diesel", 2.4, 165, "Color1", 2023, TransmissionType.AUTOMATIC, true, 50);
        Car car2 = new Car("VIN2", "Brand2", "Model2", "Electric", 75.0, 150, "Color2", 2021, TransmissionType.AUTOMATIC, false, 52000);

        BigDecimal acquisitionPrice1 = new BigDecimal("300000.00");
        BigDecimal acquisitionPrice2 = new BigDecimal("15000.00");
        BigDecimal price1 = new BigDecimal("350000.00");
        BigDecimal price2 = new BigDecimal("20000.00");

        Status status1 = Status.FOR_SALE;
        Status status2 = Status.SOLD;
        Status updatedStatus = Status.SOLD;

        inventory1 = new Inventory(1, car1, status1, acquisitionDate1, acquisitionPrice1, price1);
        inventory2 = new Inventory(2, car2, status2, acquisitionDate2, acquisitionPrice2, price2);
        updatedInventory = new Inventory(1, car1, updatedStatus, acquisitionDate1, acquisitionPrice1, price1);
    }

    @Test
    void it_should_give_all_inventories() {
        //given
        List<Inventory> expectedInventories = Arrays.asList(inventory1, inventory2);
        when(inventoryDao.findAll()).thenReturn(expectedInventories);

        //when
        List<Inventory> actualInventories = inventoryService.getAllInventories();

        //then
        assertNotNull(actualInventories);
        assertEquals(2, actualInventories.size());
    }

    @Test
    void it_should_give_inventory_by_id() {
        // given
        int id = 1;
        Optional<Inventory> expectedInventory = Optional.of(inventory1);
        when(inventoryDao.findById(id)).thenReturn(expectedInventory);

        // when
        Optional<Inventory> actualInventory = inventoryService.getInventoryById(id);

        // then
        assertTrue(actualInventory.isPresent());
        assertEquals(expectedInventory.get(), actualInventory.get());
    }

    @Test
    void it_should_add_new_inventory() {
        //given
        when(inventoryDao.save(any(Inventory.class))).thenReturn(inventory1);

        //when
        inventoryService.addNewInventory(inventory1);

        //then
        verify(inventoryDao, times(1)).save(inventory1);
    }

    @Test
    void it_should_delete_inventory_by_given_id() {
        //given
        doNothing().when(inventoryDao).deleteById(inventory1.getInventoryId());

        //when
        inventoryService.deleteInventory(inventory1.getInventoryId());

        //then
        verify(inventoryDao, times(1)).deleteById(inventory1.getInventoryId());
    }

    @Test
    void it_should_update_inventory_by_given_id() {
        //given
        when(inventoryDao.findById(inventory1.getInventoryId())).thenReturn(Optional.of(inventory1));
        when(inventoryDao.save(any(Inventory.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        //when
        inventoryService.updateInventory(inventory1.getInventoryId(), updatedInventory);

        //then
        assertEquals(updatedInventory.getCar(), inventory1.getCar());
        assertEquals(updatedInventory.getAcquisitionDate(), inventory1.getAcquisitionDate());
        assertEquals(updatedInventory.getAcquisitionPrice(), inventory1.getAcquisitionPrice());
        assertEquals(updatedInventory.getStatus(), inventory1.getStatus());

        verify(inventoryDao, times(1)).findById(inventory1.getInventoryId());
        verify(inventoryDao, times(1)).save(inventory1);
    }
}