package com.supecars.cardealermanagement.service.impl;

import com.supecars.cardealermanagement.dao.ServiceEntryDao;
import com.supecars.cardealermanagement.model.Car;
import com.supecars.cardealermanagement.model.ServiceEntry;
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
class ServiceEntryServiceImplTest {

    @Mock
    private ServiceEntryDao serviceEntryDao;

    @InjectMocks
    private ServiceEntryServiceImpl serviceEntryService;

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    private Car car1, car2;
    private ServiceEntry serviceEntry1, serviceEntry2, updatedServiceEntry;

    @BeforeEach
    void setUp() throws ParseException{

        Date serviceDate1 = format.parse("2023-12-01");
        Date serviceDate2 = format.parse("2023-09-01");

        car1 = new Car("VIN1", "Brand1", "Model1", "Diesel", 2.4, 165, "Color1", 2023, TransmissionType.AUTOMATIC, new BigDecimal("420000"), true, 50);
        car2 = new Car("VIN2", "Brand2", "Model2", "Electric", 75.0, 150, "Color2", 2021, TransmissionType.AUTOMATIC, new BigDecimal("350000"), false, 52000);

        serviceEntry1 = new ServiceEntry(1, car1, serviceDate1, "Test Service", new BigDecimal("6400.00"));
        serviceEntry2 = new ServiceEntry(2, car2, serviceDate2, "Test Service", new BigDecimal("6400.00"));
        updatedServiceEntry = new ServiceEntry(2, car2, serviceDate1, "Test Service", new BigDecimal("2400.00"));
    }

    @Test
    void it_should_give_all_registered_services() {
        //given
        List<ServiceEntry> expectedServices = Arrays.asList(serviceEntry1, serviceEntry2);
        when(serviceEntryDao.findAll()).thenReturn(expectedServices);

        //when
        List<ServiceEntry> actualServices = serviceEntryService.getAllRegisteredServices();

        //then
        assertNotNull(actualServices);
        assertEquals(2, actualServices.size());
    }

    @Test
    void it_should_give_serviceEntry_by_id() {
        // given
        int id = 1;
        Optional<ServiceEntry> expectedServiceEntry = Optional.of(serviceEntry1);
        when(serviceEntryDao.findById(id)).thenReturn(expectedServiceEntry);

        // when
        Optional<ServiceEntry> actualServiceEntry = serviceEntryService.getServiceEntryById(id);

        // then
        assertTrue(actualServiceEntry.isPresent());
        assertEquals(expectedServiceEntry.get(), actualServiceEntry.get());
    }

    @Test
    void it_should_add_new_serviceEntry() {
        //given
        when(serviceEntryDao.save(any(ServiceEntry.class))).thenReturn(serviceEntry1);

        //when
        serviceEntryService.addNewServiceEntry(serviceEntry1);

        //then
        verify(serviceEntryDao, times(1)).save(serviceEntry1);
    }

    @Test
    void it_should_delete_ServiceEntry_by_given_id() {
        //given
        doNothing().when(serviceEntryDao).deleteById(serviceEntry1.getServiceId());

        //when
        serviceEntryService.deleteServiceEntry(serviceEntry1.getServiceId());

        //then
        verify(serviceEntryDao, times(1)).deleteById(serviceEntry1.getServiceId());
    }

    @Test
    void updateServiceEntry() {
        //given
        when(serviceEntryDao.findById(serviceEntry2.getServiceId())).thenReturn(Optional.of(serviceEntry2));
        when(serviceEntryDao.save(any(ServiceEntry.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        //when
        serviceEntryService.updateServiceEntry(serviceEntry2.getServiceId(), updatedServiceEntry);

        //then
        assertEquals(updatedServiceEntry.getCar(), serviceEntry2.getCar());
        assertEquals(updatedServiceEntry.getServiceDate(), serviceEntry2.getServiceDate());
        assertEquals(updatedServiceEntry.getDescription(), serviceEntry2.getDescription());
        assertEquals(updatedServiceEntry.getCost(), serviceEntry2.getCost());

        verify(serviceEntryDao, times(1)).findById(serviceEntry2.getServiceId());
        verify(serviceEntryDao, times(1)).save(serviceEntry2);
    }
}