package amt.gamificationproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "amt.sbjparestguide.application" })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

