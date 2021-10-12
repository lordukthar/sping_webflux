package com.aja.stock.heroku;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Controller
public class EmployeeController
{
   @Autowired
   EmployeeRepository employeeRepository;

   @Autowired
   EmployeeService employeeService;

   @RequestMapping("/")
   @ResponseBody
   String home() {
      return "Hello World!";
   }

   @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
   @ResponseBody
   Mono<Employee> getEmployee(@PathVariable("id") String employeeId) {
      return
            employeeRepository.findEmployeeById(employeeId);
   }

   @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
   @ResponseBody
   Mono<User> getUser(@PathVariable("id") String employeeId) {
      return
            employeeService.getUser(employeeId);
   }

   @RequestMapping(value = "/users/", method = RequestMethod.GET)
   @ResponseBody
   Flux<User> getUsers() {
      return
            employeeService.getUsers();
   }

   @GetMapping(value = "/employees")
   @ResponseBody
   Flux<Employee> getEmployees() {
      return
           employeeRepository.findAllEmployees();
   }

   @GetMapping(value = "/rates/{id}")
   @ResponseBody
   Mono<String> getEmployeesAsJson(@PathVariable("id")  String stockId) {
      return
            employeeService.getUsersJson(stockId);
   }
}
