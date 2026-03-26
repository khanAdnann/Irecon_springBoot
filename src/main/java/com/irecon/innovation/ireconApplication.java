package com.irecon.innovation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.irecon.innovation")  
public class ireconApplication  extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(ireconApplication.class, args);
	}

	   @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(ireconApplication.class);
	    }
}
