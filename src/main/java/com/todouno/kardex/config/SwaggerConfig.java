package com.todouno.kardex.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfig.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

  public static final Contact DEFAULT_CONTACT = new Contact(
      "Rubén Camarena Jáuregui", "https://github.com/rcamarena0796/ms-todouno-kardex-ui.git",
      "rcamaren@everis.com");
  public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
      "Kardex Micro Service", "Micro service used to manage Product and User operations",
      "1.0", "urn:tos",
      DEFAULT_CONTACT.getName(), "Apache 2.0", "http://www.google.com.pe");
  private static Set<String> DEFAULT_PRODUCES_AND_CONSUMES =
      new HashSet<String>(Arrays.asList("application/json", "application/xml"));

  /**
   * Swagger Config.
   */
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(DEFAULT_API_INFO)
        .produces(DEFAULT_PRODUCES_AND_CONSUMES)
        .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
  }
}
