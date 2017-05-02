package sap.education;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import sap.education.model.Book;
import sap.education.model.BookStoreRepository;

import static springfox.documentation.builders.PathSelectors.regex;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class BookStoreApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BookStoreApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(final BookStoreRepository repository) {
		return new CommandLineRunner() {
			public void run(String... args) throws Exception {
				if (repository.count() == 0) {
					// Database is still empty. Adding some sample records.The
					// ISBN must be unique !

					repository.save(new Book("Reader1", "123456", "Title1", "Author1", "Description1"));
					repository.save(new Book("Reader2", "123457", "Title2", "Author2", "Description2"));
					repository.save(new Book("Reader3", "123458", "Title3", "Author3", "Description3"));
					
					//Excercise
//					repository.save(new Book("Reader1", "123456", "Title1", "Author1", "Description1","Category 1"));
//					repository.save(new Book("Reader2", "123457", "Title2", "Author2", "Description2","Category 2"));
//					repository.save(new Book("Reader3", "123458", "Title3", "Author3", "Description3","Category 3"));

				}
			}
		};

	}

	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("BookStoreApp").apiInfo(apiInfo()).select()
				.paths(regex("/rest.*")).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("BookStoreAPI")
				.description("BookStoreAPI description with swagger")
				.termsOfServiceUrl("")
				.contact("SAP Education").license("Apache License Version 2.0")
				.licenseUrl("").version("2.0")
				.build();
	}

}
