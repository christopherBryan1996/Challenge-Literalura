package com.alura.literalura;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class LiteraluraApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
		Scanner sr = new Scanner(System.in);
		boolean start = true;
		
		while(start) {
			System.out.println("Ingresa la opcion deseada");
			System.out.println("1. Búsqueda de libro por título");
			System.out.println("2. Lista de todos los libros");
			String op = sr.nextLine();			
			System.out.println(op);
			
			String url = "https://gutendex.com/books/";

	        RestTemplate restTemplate = new RestTemplate();
	        GutendexResponse response = restTemplate.getForObject(url, GutendexResponse.class);

	        if (response != null) {
	            for (Book book : response.getResults()) {
	                System.out.println("Title: " + book.getTitle());
	                for (Author author : book.getAuthors()) {
	                    System.out.println("Author: " + author.getName());
	                }
	            }
	        }
		}
	}

}
