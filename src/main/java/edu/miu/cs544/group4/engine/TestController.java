package edu.miu.cs544.group4.engine;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/")
public class TestController {
	
    @GetMapping(value = "admin")
    private String adminPoint(){
        return "This is a message from the reservation enginee service, ADMIN privilege";
    }
    
    @GetMapping(value = "passenger")
    private String passengerPoint(){
        return "This is a message from the reservation enginee service, PASSENGER privilege";
    }
    
    @GetMapping(value = "agent")
    private String agentPoint(){
        return "This is a message from the reservation enginee service, AGENT privilege";
    }
    
    @GetMapping(value = "test")
    private String everyOnePoint(){
        return "This is a message from the reservation enginee service, for all";
    }
    
    
}
