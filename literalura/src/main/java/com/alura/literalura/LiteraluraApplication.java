package com.alura.literalura;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class LiteraluraApplication {

	public static void viewbooks() {
		String url = "https://gutendex.com/books/";
		
		System.out.print("Cargando ");
		RestTemplate restTemplate = new RestTemplate();
		GutendexResponse response = restTemplate.getForObject(url, GutendexResponse.class);

		if (response != null) {
			for (Book book : response.getResults()) {
				System.out.println();
				System.out.println("Title: " + book.getTitle());
				for (Author author : book.getAuthors()) {
					System.out.println("Author: " + author.getName());
				}
			}
		}
		System.out.println();
	}
	
	public static void viewbooks(String languages) {
		String url = "https://gutendex.com/books/?languages="+languages;
		
		System.out.print("Cargando ");
		RestTemplate restTemplate = new RestTemplate();
		GutendexResponse response = restTemplate.getForObject(url, GutendexResponse.class);

		if (response != null) {
			for (Book book : response.getResults()) {
				System.out.println();
				System.out.println("Title: " + book.getTitle());
				for (Author author : book.getAuthors()) {
					System.out.println("Author: " + author.getName());
				}
			}
		}
		System.out.println();
	}
	
	public static void searchBook(String name) {
        String url = "https://gutendex.com/books/?search=" + name;
        
        RestTemplate restTemplate = new RestTemplate();
        GutendexResponse response = restTemplate.getForObject(url, GutendexResponse.class);

        if (response != null && response.getResults() != null && !response.getResults().isEmpty()) {
            Book book = response.getResults().get(0);
            System.out.println("ID: " + book.getId());
            System.out.println("Title: " + book.getTitle());
            System.out.print("Authors: ");
            for (Author author : book.getAuthors()) {
                System.out.print(author.getName() + " ");
            }
            System.out.println();
        } else {
            System.out.println("No se encontraron libros para el término de búsqueda: " + name);
        }
    }

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
		Scanner sr = new Scanner(System.in);
		boolean start = true;

		System.out.println("\nCargando");
		while (start) {
			System.out.println("Ingresa la opcion deseada");
			System.out.println("1. Búsqueda de libro por título");
			System.out.println("2. Lista de todos los libros");
			System.out.println("3. Búsqueda de libro por autor");
			System.out.println("4. Lista por idioma");
			System.out.println("0. para salir");
			try {
				int op = sr.nextInt();
				sr.nextLine();
				switch (op) {
					case 0:
						start = false;
						System.out.println("Adios");
						break;
					case 1:
						System.out.println("Ingresa el nombre del titulo del libro");
						String name = sr.nextLine();
						searchBook(name);
						break;
					case 2:
						viewbooks();
						break;
					case 3:
						System.out.println("Ingresa el nombre del titulo del libro");
						String nameAutor = sr.nextLine();
						searchBook(nameAutor);
						break;
					case 4:
						System.out.println("Ingresa el idioma solo las siglas en minusculas");
						String lang = sr.nextLine();
						viewbooks(lang);
						break;
					default:
						System.err.println("Digita un numero del menú");
				}				
			} catch (Exception e) {
				System.err.println("Ingresa solo números");
				sr.next();
			}

			System.out.println();
		}
	}

}
