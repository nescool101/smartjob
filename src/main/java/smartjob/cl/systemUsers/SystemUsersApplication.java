package smartjob.cl.systemUsers;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition
public class SystemUsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(SystemUsersApplication.class, args);
	}

	@Bean
	public OpenAPI custonOpenAPI(){

		return new OpenAPI()
				.info(new Info()
						.title("Users system API")
						.version("1.0.0")
						.description("sample API to user management")
						.termsOfService("http://swagger.io/terms/")
						.license(new License().name("Apache 2.0")
								.url("http://springdoc.org"))
				);

	}

}
