package com.anderson.testing_deploy;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "testing-deploy",
		version = "1",
		description = "project created to test deploy")
)
public class TestingDeployApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestingDeployApplication.class, args);
	}

}
