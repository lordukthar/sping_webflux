package com.aja.stock.heroku;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class User
{
   private int id;
   private String name;
   private String username;
   private String phone;
   private String website;
   private Address address;
   private Company company;

   @JsonCreator
   public User(
         @JsonProperty("id") int id,
         @JsonProperty("name") String name,
         @JsonProperty("username") String username,
         @JsonProperty("phone") String phone,
         @JsonProperty("website") String website,
         @JsonProperty("address") Address address,
         @JsonProperty("company") Company company)
   {
      this.id = id;
      this.name = name;
      this.username = username;
      this.phone = phone;
      this.website = website;
      this.address = address;
      this.company = company;
   }

   public int getId()
   {
      return id;
   }

   public String getName()
   {
      return name;
   }

   public String getUsername()
   {
      return username;
   }

   public String getPhone()
   {
      return phone;
   }

   public String getWebsite()
   {
      return website;
   }

   public Address getAddress()
   {
      return address;
   }

   public Company getCompany()
   {
      return company;
   }

   @Override
   public String toString()
   {
      return "User{" + "id=" + id + ", name='" + name + '\'' + ", username='" + username + '\'' + ", phone='" + phone + '\''
            + ", website='" + website + '\'' + ", address=" + address + ", company=" + company + '}';
   }
}
