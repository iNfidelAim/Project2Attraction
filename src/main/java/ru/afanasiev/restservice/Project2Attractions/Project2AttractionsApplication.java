package ru.afanasiev.restservice.Project2Attractions;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Project2AttractionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(Project2AttractionsApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapperCity() {
		return new ModelMapper();
	}

	@Bean
	public ModelMapper modelMapperAttraction() {
		return new ModelMapper();
	}
}
