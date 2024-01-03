package com.example.webflux.handler;

import com.example.webflux.dao.CustomerDao;
import com.example.webflux.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.rsocket.RSocketProperties.Server;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

  @Autowired
  private CustomerDao customerDao;


  public Mono<ServerResponse> loadCustomers(ServerRequest serverRequest){
    Flux<Customer> customerFlux = customerDao.getCustomersList();
    return ServerResponse.ok().body(customerFlux,Customer.class);
  }

  public Mono<ServerResponse> findCustomer(ServerRequest serverRequest){
    int customerId = Integer.valueOf(serverRequest.pathVariable("input"));
    Mono<Customer> customerMono = customerDao.getCustomersList().filter(c -> c.getId()==customerId)
        .take(1).single();
        //.next()

    return ServerResponse.ok().body(customerMono,Customer.class);
  }

  public Mono<ServerResponse> saveCustomer(ServerRequest serverRequest){
    Mono<Customer> customerMono = serverRequest.bodyToMono(Customer.class);
    Mono<String> saveResponse = customerMono.map(customer -> customer.getId() + " : " + customer.getName());

    return ServerResponse.ok().body(saveResponse, String.class);
  }

}
