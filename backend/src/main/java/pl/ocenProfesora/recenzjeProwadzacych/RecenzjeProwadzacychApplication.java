package pl.ocenProfesora.recenzjeProwadzacych;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "pl.ocenProfesora.recenzjeProwadzacych.mappings")
public class RecenzjeProwadzacychApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecenzjeProwadzacychApplication.class, args);
	}

}
