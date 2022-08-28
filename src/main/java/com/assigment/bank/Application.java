package com.assigment.bank;

import com.assigment.bank.config.PersistenceJPAConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.DefaultPathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableWebMvc
@EnableSwagger2
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		new SpringApplicationBuilder(
				Application.class,
				PersistenceJPAConfig.class
		)
				.build(args)
				.run();
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.pathProvider(new DefaultPathProvider())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.assigment.bank.controller"))
				.paths(PathSelectors.any())
				.build();
	}
}
