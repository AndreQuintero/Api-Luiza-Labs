package com.desafioapilabs;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;




@SpringBootApplication
public class DesafioapilabsApplication {

	@Bean
	public RestTemplate getRestTemplate() {
	    return new RestTemplate();
	 }
	
	
	public static void main(String[] args) {
		SpringApplication.run(DesafioapilabsApplication.class, args);
	}
	
	

}
