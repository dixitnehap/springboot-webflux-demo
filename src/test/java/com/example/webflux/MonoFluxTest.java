package com.example.webflux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {

  @Test
  public void testMono(){
    Mono<String> monoString = Mono.just("Webflux demo").log();
    Mono<?> monoStringException = Mono.just("Webflux demo")
        .then(Mono.error(new RuntimeException("Exception Occured"))).log();//forcefully throwing exception to test workflow

    monoString.subscribe(System.out::println);
    monoStringException.subscribe(System.out::println, (e) -> System.out.println(e.getMessage()));

  }

  @Test
  public void testFlux(){
    Flux<String> fluxString = Flux.just("Spring","Spring boot","Hibernate", "Microservices")
        .concatWithValues("AWS")
        .concatWith(Flux.error(new RuntimeException(("Exception occurred in Flux"))))
        .concatWithValues("Cloud")
        .log();
    fluxString.subscribe(System.out::println);
  }

}
