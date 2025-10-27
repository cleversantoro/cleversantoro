package br.edu.infnet.cleversantoro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CleversantoroApplication {

	public static void main(String[] args) {
		SpringApplication.run(CleversantoroApplication.class, args);
	}

}
