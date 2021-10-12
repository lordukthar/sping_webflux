package com.aja.stock.heroku;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address
{
   private String street;
   private String suite;
   private String city;
   private String zipcode;
   private Geo geo;

   @JsonCreator
   public Address( @JsonProperty("street") String street,
         @JsonProperty("suite") String suite,
         @JsonProperty("city") String city,
         @JsonProperty("zipcode") String zipcode,
         @JsonProperty("geo") Geo geo)
   {
      this.street = street;
      this.suite = suite;
      this.city = city;
      this.zipcode = zipcode;
      this.geo = geo;
   }

   public String getStreet()
   {
      return street;
   }

   public String getSuite()
   {
      return suite;
   }

   public String getCity()
   {
      return city;
   }

   public String getZipcode()
   {
      return zipcode;
   }

   public Geo getGeo()
   {
      return geo;
   }
}
