package com.example.webflux.router;

import com.example.webflux.handler.CustomerHandler;
import com.example.webflux.handler.CustomerStreamHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

  @Autowired
  private CustomerHandler customerHandler;

  @Autowired
  private CustomerStreamHandler streamHandler;

  @Bean
  public RouterFunction<ServerResponse> routerFunction(){
    return RouterFunctions.route()
        .GET("/router/customers", customerHandler::loadCustomers)
        .GET("/router/customers/stream", streamHandler::getCustomers)
        .GET("/router/customers/{input}", customerHandler::findCustomer)
        .POST("/router/customers/save", customerHandler::saveCustomer)
        .build();
  }

}
