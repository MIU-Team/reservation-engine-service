//package edu.miu.cs544.group4.engine.Configuration;
//
//import org.apache.maven.model.Model;
//import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
//import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.ApiKey;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
////
////	@Bean
////	public Docket api() {
////
////		// @formatter:off
////		// Register the controllers to swagger
////		// Also it is configuring the Swagger Docket
////		return new Docket(DocumentationType.SWAGGER_2)
////				.apiInfo(apiInfo())
////				.select()
////				.apis(RequestHandlerSelectors.any())
////				.paths(PathSelectors.any())
////				.build();
////	}
//
//
//	@Bean
//	public Docket api() throws IOException, XmlPullParserException {
//		MavenXpp3Reader reader = new MavenXpp3Reader();
//		Model model = reader.read(new FileReader("pom.xml"));
//		return new Docket(DocumentationType.SWAGGER_2)
//				.select()
//				.apis(RequestHandlerSelectors.basePackage(""))
//				.paths(PathSelectors.any())
//				.build()
//				.securitySchemes(new ArrayList<>(Arrays.asList(new ApiKey("Bearer", "Authorization", "Header"))))
//				.apiInfo((apiInfo()));
//	}
//
//	private ApiInfo apiInfo() {
//		return new ApiInfoBuilder()
//				.version("1.0.0")
//				.title("Reservation Engine Microservice")
//				.description("Enterprise Architecture Project")
//				.termsOfServiceUrl("")
//				.contact(new Contact("Group 4","https://github.com/MIU-Team/reservation-security-service",""))
//				.license("")
//				.licenseUrl("")
//				.version("1.0.0")
//				.build();
//	}
//
////	@Override
////	public void addResourceHandlers(ResourceHandlerRegistry registry) {
////		// enabling swagger-ui part for visual documentation
////		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
////		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
////	}
//}
