package com.aja.stock.heroku;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.aja.stock.heroku")
public class Application {

   @RequestMapping("/")
   @ResponseBody
   String home() {
      return "Hello World!";
   }

   public static void main(String[] args) {
      System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,TLSv1");
      SpringApplication.run(Application.class, args);
   }
}
