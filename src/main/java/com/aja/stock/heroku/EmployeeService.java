package com.aja.stock.heroku;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeService
{
   @Autowired
   EmployeeWebClient employeeWebClient;

   public Mono<User> getUser(String id) {
      return employeeWebClient.getUser(id);
   }

   public Flux<User> getUsers() {
      return employeeWebClient.getUsers();
   }

   public Mono<String> getUsersJson(String stockId) {
      return employeeWebClient.getUsersAsJson(stockId);
   }

}
