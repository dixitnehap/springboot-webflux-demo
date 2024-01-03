package com.example.webflux.handler;

import com.example.webflux.dao.CustomerDao;
import com.example.webflux.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerStreamHandler {

  @Autowired
  private CustomerDao customerDao;
  public Mono<ServerResponse> getCustomers(ServerRequest serverRequest){
    Flux<Customer> customerFlux = customerDao.getCustomersStream();
    return ServerResponse.ok()
        .contentType(MediaType.TEXT_EVENT_STREAM)
        .body(customerFlux,Customer.class);
  }
}
