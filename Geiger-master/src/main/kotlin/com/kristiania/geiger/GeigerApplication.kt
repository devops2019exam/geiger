package com.kristiania.geiger

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import springfox.documentation.builders.PathSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


@SpringBootApplication
@EnableSwagger2
class GeigerApplication {

	@Bean
	fun swaggerApi(): Docket {
		return Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.any())
				.build()
	}
}

fun main(args: Array<String>) {
	SpringApplication.run(GeigerApplication::class.java, *args)
}
