package edu.miu.cs544.group4.engine.airline.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import edu.miu.cs544.group4.engine.model.Address;
import edu.miu.cs544.group4.engine.model.Airline;
import edu.miu.cs544.group4.engine.model.History;
import edu.miu.cs544.group4.engine.repository.AirlineRepository;
import edu.miu.cs544.group4.engine.service.AirlineService;
import edu.miu.cs544.group4.engine.service.AirlineServiceImpl;
import lombok.SneakyThrows;

@SpringBootTest
public class AirlineServiceMockTest {
	
   
 
    
    @Mock
    private  AirlineRepository airlineRepository; 

    @InjectMocks
    private AirlineService airlineService = new AirlineServiceImpl();

   
    Airline airline1 = new Airline();
    Airline airline2 = new Airline();
    Airline airline3 = new Airline();
    private List<Airline> airlines = Arrays.asList(airline1, airline2, airline3);


    @Test
    @SneakyThrows
    void testGetAllAirline() {
        when(airlineRepository.findAll()).thenReturn(airlines);
        assertEquals(airlines, airlineService.getAllAirline());
        verify(airlineRepository).findAll();
    }

    
    @Test
    void testSaveAirline() {
        when(airlineRepository.save(airlines.get(1))).thenReturn(airlines.get(1));
        assertEquals(airlines.get(1), airlineService.saveAirline(airlines.get(1)));
        verify(airlineRepository).save(airlines.get(1));
    }   
    
    @Test
    void testGetAirline() {
    when(airlineRepository.getOne(airlines.get(1).getId())).thenReturn(airlines.get(1));
        assertEquals(airlines.get(1), airlineService.getOneAirlineById(airlines.get(1).getId()));
        verify(airlineRepository).getOne(airlines.get(1).getId());
    }

    
    
    void initializeData() {
    	
    	//first data
    	History history1 = new History();
    	    history1.setDescription("There is nothing such history");
    	    
    	    Address address1 = new Address();
    	    address1.setCity("Dallas");
    	    address1.setState("Texas");
    	    address1.setZip("2343");
    	    address1.setStreet("6789 Well done");

    	  
    	    airline1.setAddress(address1);
    	    airline1.setCode("UA1");
    	    airline1.setName("My Airline");
    	    airline1.setHistory(history1);
    	    
    	    //second data
    	    History history2 = new History();
    	    history2.setDescription("There is nothing such history");
    	    
    	    Address address2 = new Address();
    	    address2.setCity("Dallas");
    	    address2.setState("Texas");
    	    address2.setZip("2343");
    	    address2.setStreet("6789 Well done");

    	  
    	    airline2.setAddress(address2);
    	    airline2.setCode("UA2");
    	    airline2.setName("My Airline");
    	    airline2.setHistory(history2);
    	    
    	    //third data
    	    History history3 = new History();
    	    history3.setDescription("There is nothing such history");
    	    
    	    Address address3 = new Address();
    	    address3.setCity("Dallas");
    	    address3.setState("Texas");
    	    address3.setZip("2343");
    	    address3.setStreet("6789 Well done");

    	  
    	    airline3.setAddress(address3);
    	    airline3.setCode("UA2");
    	    airline3.setName("My Airline");
    	    airline3.setHistory(history3);
    	    
    }
}
