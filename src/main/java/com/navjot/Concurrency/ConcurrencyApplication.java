package com.navjot.Concurrency;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.ModelMap;

@SpringBootApplication
public class ConcurrencyApplication {

	@Bean("modelMapper")
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}



	public static void main(String[] args) {
		SpringApplication.run(ConcurrencyApplication.class, args);
	}

}
