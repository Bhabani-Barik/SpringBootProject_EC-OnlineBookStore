package com.nareshIt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
        title = "Ecommerc Book Store Management",
        version = "1.0",
        description = "Spring Boot E-Commerce Book Store Management",
        contact = @Contact(name ="Naresh I Technology",email = "bhabani@gmail.com")))

@SpringBootApplication
@EnableCaching
public class SpringBootProjectEcOnlineBookStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootProjectEcOnlineBookStoreApplication.class, args);
	}

}
