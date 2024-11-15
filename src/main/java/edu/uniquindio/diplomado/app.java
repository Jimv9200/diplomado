package edu.uniquindio.diplomado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration(proxyBeanMethods=false)
public class app {
// DiplomadoProjectApplication
	public static void main(String[] args) {
		SpringApplication.run(app.class, args);
	}

}
