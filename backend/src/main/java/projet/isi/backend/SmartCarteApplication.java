package projet.isi.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmartCarteApplication {

	public static void main(String[] args) {
		System.out.println("Test du message dans la console !");

		SpringApplication.run(SmartCarteApplication.class, args);
	}

}
