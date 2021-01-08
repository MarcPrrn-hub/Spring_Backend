package com.emse.spring.faircorp.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DummyUserService implements UserService {

    private GreetingService greetingService;
    @Autowired
    public  DummyUserService(GreetingService greetingService){
        this.greetingService = greetingService;
    }
    String names[] = {"Elodie", "Charles"};

    @Override
    public void greetAll() {
        for (String name : names ) {
            this.greetingService.greet(name);
        }

    }

}