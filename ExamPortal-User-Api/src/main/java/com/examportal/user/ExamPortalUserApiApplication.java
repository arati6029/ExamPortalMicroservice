package com.examportal.user;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ExamPortalUserApiApplication {
//	@Bean
//	public RestTemplate restTemplate() {
//		return new RestTemplate();
//	}
	@Bean
	public ModelMapper myMapper()
	{
		ModelMapper mapper=new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return mapper;
	}
	public static void main(String[] args) {
		SpringApplication.run(ExamPortalUserApiApplication.class, args);
	}

}
