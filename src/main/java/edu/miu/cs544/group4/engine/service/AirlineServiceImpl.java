package edu.miu.cs544.group4.engine.service;

import edu.miu.common.service.BaseReadWriteServiceImpl;
import edu.miu.cs544.group4.engine.model.Airline;
import edu.miu.cs544.group4.engine.repository.AirlineRepository;
import edu.miu.cs544.group4.engine.service.response.AirlineResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirlineServiceImpl extends BaseReadWriteServiceImpl<AirlineResponse, Airline, Integer> implements AirlineService {

    @Autowired
    private AirlineRepository airlineRepository;

    @Override
    public AirlineResponse create(Airline airline) {
        return convertEntityToResponse(baseRepository.save(airline));
    }

    @Override
    public List<AirlineResponse> findAirlinesOutOfAnAirport(String code) {
        return convertEntityListToResponseList(airlineRepository.findAirlinesOutOfAnAirport(code));
    }

    @Override
    public List<Airline> getAllAirline() {
        return airlineRepository.findAll();
    }

    @Override
    public Airline getAirlineById(Integer id) {
        return airlineRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid flight Id: " + id));
    }


    @Override
    public Airline getOneAirlineById(Integer id) {

        return airlineRepository.getOne(id);
    }

    @Override
    public List<Airline> getAirlineByName(String name) {
        return airlineRepository.findByName(name);
    }


    @Override
    public Airline saveAirline(Airline airline) {
        return airlineRepository.save(airline);

    }

    @Override
    public void removeAirlineById(Integer id) {
        airlineRepository.deleteById(id);

    }

    @Override
    public void removeAll() {
        airlineRepository.deleteAll();

    }

    @Override
    public void removeAirlineByName(String name) {
        airlineRepository.deleteByName(name);

    }

    @Override
    public void updateAirlineById(Integer id, Airline airline) {
        airlineRepository.updateAirline(id, airline);
    }

}
