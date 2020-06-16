package edu.miu.cs544.group4.engine.airline.repository;
/*
 * 
 * @author Dawed Nesru Muzeyen
 * @since 1.0
 * @created 13.06.2020
 * @copy right by me so far, lol
 * 
 * @description: This is the Airline Repository Testing class against all CRUD
 * */

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.miu.cs544.group4.engine.model.Address;
import edu.miu.cs544.group4.engine.model.Airline;
import edu.miu.cs544.group4.engine.model.History;
import edu.miu.cs544.group4.engine.repository.AirlineRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class AirlineRepositoryTesting {

 
  @Autowired
  AirlineRepository airlineRepository;
 
  @Test
  public void testRepository() {
    History history = new History();
    history.setDescription("There is nothing such history");
    Address address = new Address();
    address.setCity("Dallas");
    address.setState("Texas");
    address.setZip("2343");
    address.setStreet("6789 Well done");

    Airline airline = new Airline();
    airline.setAddress(address);
    airline.setCode("UA");
    airline.setName("My Airline");
    airline.setHistory(history);
    
    //This tests if the database saves the airline data
    airlineRepository.save(airline);
  
    
    //this tests if the databases fetches data properly
    Optional<Airline> airlineTestData = airlineRepository.findById(19);
    assertEquals("Dawed", airlineTestData.get().getName());
    
    
  }

  }

