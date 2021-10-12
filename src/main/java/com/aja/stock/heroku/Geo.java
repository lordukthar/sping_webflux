package com.aja.stock.heroku;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Geo
{
   private String lat;
   private String lng;

   @JsonCreator
   public Geo(@JsonProperty("lat") String lat, @JsonProperty("lng") String lng)
   {
      this.lat = lat;
      this.lng = lng;
   }

   public String getLat()
   {
      return lat;
   }

   public String getLng()
   {
      return lng;
   }
}
