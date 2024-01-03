package com.example.webflux.service;

import com.example.webflux.dao.CustomerDao;
import com.example.webflux.dto.Customer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class CustomerService {

  @Autowired
  private CustomerDao customerDao;

  public List<Customer> loadAllCustomer(){
    long start = System.currentTimeMillis();
    List<Customer> customers = customerDao.getCustomers();
    long end = System.currentTimeMillis();
    System.out.println("Total execution time: " + (end - start));
    return customers;
  }

  public Flux<Customer> loadAllCustomerStream(){
    long start = System.currentTimeMillis();
    Flux<Customer> customers = customerDao.getCustomersStream();
    long end = System.currentTimeMillis();
    System.out.println("Total execution time: " + (end - start));
    return customers;
  }

}
