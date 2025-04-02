package siq.BestMealsAPI;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// definições para o swagger-ui
@OpenAPIDefinition(info = @Info(title = "BestMeals API", version = "1.0.0", description = "API para avaliação de restaurantes"))
@SpringBootApplication
public class BestMealsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BestMealsApiApplication.class, args);
	}

}
