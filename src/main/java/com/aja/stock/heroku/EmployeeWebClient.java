package com.aja.stock.heroku;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.http.codec.ClientCodecConfigurer;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import static org.springframework.http.MediaType.TEXT_HTML;


@Service
public class EmployeeWebClient
{
   WebClient client = WebClient.create("http://jsonplaceholder.typicode.com/users/");

   public Mono<User> getUser(String id) {



         throw new GlobalException(HttpStatus.INTERNAL_SERVER_ERROR, "request param city is ERROR");


       /*  Mono<User> response = client.get()
               .uri(id).accept(MediaType.APPLICATION_JSON)
               .retrieve()
               .bodyToMono(User.class)
               .switchIfEmpty(Mono.error(new ResponseStatusException(UNPROCESSABLE_ENTITY)))
               .log();
                response.doOnNext(user -> log("AP" + user.toString()));
         return response;
*/
      /*   Mono<ServerResponse> r = client.get()
               .uri(id).accept(MediaType.APPLICATION_JSON)
               .retrieve()
               .bodyToMono(User.class)
           .flatMap(s -> ServerResponse.ok()
               .contentType(MediaType.TEXT_PLAIN)
               .bodyValue(s))
               .onErrorResume(e -> Mono.just("Error " + e.getMessage())
                     .flatMap(s -> ServerResponse.unprocessableEntity()
                           .contentType(MediaType.TEXT_PLAIN)
                           .bodyValue(s)));
*/



        // return Mono.empty();


   }


   public Flux<User> getUsers() {

      try
      {
         Flux<User> response = client.get()
               .accept(MediaType.APPLICATION_JSON)
               .retrieve()
               .bodyToFlux(User.class).log();

         // ObjectMapper mapper = new ObjectMapper();

         //User user = Arrays.stream(objects.subscribe()).map(object -> mapper.convertValue(object, User.class)).findFirst().orElse(null);

         response.subscribe((s) -> System.out.println("Jonas "+ s));

         return response;
      } catch ( Exception ex){
         System.out.println("jdflsahl" + ex.getMessage());
      }


      return null;
   }


   public CloseableHttpClient getHttpClient() throws NoSuchAlgorithmException, KeyManagementException
   {
      SSLContext sslContext = SSLContexts.custom().build();
      SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext,
            new String[]{"TLSv1.2", "TLSv1.1"}, null, SSLConnectionSocketFactory.getDefaultHostnameVerifier());
      return HttpClients.custom()
            .setSSLSocketFactory(sslConnectionSocketFactory)
            .build();
   }


   public Mono<String> getUsersAsJson(String stockId) {
      //com.gargoylesoftware.htmlunit.WebClient webClient =  new com.gargoylesoftware.htmlunit.WebClient();
      WebClient webClient = WebClient.create("https://finance.yahoo.com/quote/TSLA?p=TSLA&.tsrc=fin-srch");
      try
      {

         Mono<String> r = webClient.get()
               .accept(TEXT_HTML)
               .retrieve()
               .bodyToMono(String.class);//.log();


          r.subscribe(System.out::println);

         return r;
      } catch ( Exception ex){
         System.out.println("Stock rate error:" + ex.getMessage());
      }
      return Mono.empty();
   }

   private void log(String log) {
      System.out.println("AJA:" + log);
   }


   private Rate splitAndParseRateAlphaString(String message) {
      String[] split = message
            .split("price_info");

      String[] splitter = split[1].substring(24).split(",");
      String[] ratePercentage = splitter[21].split(":");
      String[] rateAmount = splitter[19].split(":");
      String changeRate = rateAmount[2] + " (" + ratePercentage[1] + "%)";

      return new Rate(Float.valueOf(splitter[0]), changeRate);
   }
   private Rate splitAndParseRateString(String message) {
      String[] split = message
            .split("data-reactid=\"14\">");
      String[] splitAgain = split[4].split("data-reactid=\"16\">");
      return new Rate(Float.valueOf(parseCurrentRate(split[4])), parseCurrentRate(splitAgain[1]) );
   }

   private String parseCurrentRate(String htmlString) {
      int endPosRate = htmlString.indexOf("</");
      return htmlString.substring(0, endPosRate);
   }

   private void acceptedCodecs(ClientCodecConfigurer clientCodecConfigurer) {
      clientCodecConfigurer.customCodecs().encoder(new Jackson2JsonEncoder(new ObjectMapper(), TEXT_HTML));
      clientCodecConfigurer.customCodecs().decoder(new Jackson2JsonDecoder(new ObjectMapper(), TEXT_HTML));
   }

}
