package tn.saturn.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"tn.saturn.spring","tn.saturn.spring.config","tn.saturn.spring.entity","tn.saturn.spring.repository","tn.saturn.spring.service"})
public class MycroAApplication {

	public static void main(String[] args) {
		SpringApplication.run(MycroAApplication.class, args);
	}

}
