package com.devops.microservicesdocker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MicroservicesDockerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesDockerApplication.class, args);
	}

	@RequestMapping("/")
	public String index() {
		return "Página inicial. Demás rutas: /devops/microservice/clients";
	}
}
