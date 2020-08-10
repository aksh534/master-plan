package com.demo.assignment;

import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import com.demo.assignment.model.Activity;
import com.demo.assignment.repository.ActivityRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class Application implements CommandLineRunner {
	
	private static final String headersFilePath = "./src/main/resources/file_headers";
		
	@Autowired
	ActivityRepository activityRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	public static String getHeadersFilePath() {
		return headersFilePath;
	}

	@Override
	public void run(String... args) throws Exception {
		activityRepository.deleteAll();
		String path = "./src/main/resources/data.json";
		
		ObjectMapper objectMapper = new ObjectMapper();
		Activity root = objectMapper.readValue(new File(path), Activity.class);
		activityRepository.save(root);		
	}
	
}
