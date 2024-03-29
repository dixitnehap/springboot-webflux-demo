package com.example.webflux.controller;

import com.example.webflux.dto.Customer;
import com.example.webflux.service.CustomerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/customers")
public class CustomerController {

  @Autowired
  CustomerService customerService;
  @GetMapping("/")
  public List<Customer> getAllCustomers(){
    return customerService.loadAllCustomer();
  }

  @GetMapping(value = "/stream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<Customer> getAllCustomersStream(){
    return customerService.loadAllCustomerStream();
  }
}
