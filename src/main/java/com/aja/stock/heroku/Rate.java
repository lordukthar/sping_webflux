package com.aja.stock.heroku;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Rate
{
   private String goodbye;
   private float hello;

   @JsonCreator
   public Rate(@JsonProperty("hello")  float hello, @JsonProperty("goodbye")  String rateAsPercentage)
   {
      this.goodbye = rateAsPercentage;
      this.hello = hello;
   }

   public String getGoodbye()
   {
      return goodbye;
   }

   public float getHello()
   {
      return hello;
   }
}
