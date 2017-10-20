package tech.rundeep.conf;

import org.springframework.context.annotation.Bean;  
import org.springframework.context.annotation.ComponentScan;  
import org.springframework.context.annotation.Configuration;  
import org.springframework.web.servlet.config.annotation.EnableWebMvc;  
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;  
  
  
import springfox.documentation.builders.ApiInfoBuilder;  
import springfox.documentation.builders.PathSelectors;  
import springfox.documentation.builders.RequestHandlerSelectors;  
import springfox.documentation.service.ApiInfo;  
import springfox.documentation.spi.DocumentationType;  
import springfox.documentation.spring.web.plugins.Docket;  
import springfox.documentation.swagger2.annotations.EnableSwagger2;  
/* 
 * Restful API 访问路径: 
 * http://IP:port/{context-path}/swagger-ui.html 
 * eg:http://localhost:8080/jeecg/swagger-ui.html 
 */  
@EnableWebMvc  
@EnableSwagger2  
@ComponentScan(basePackages = {"tech.rundeep.api"})  
@Configuration  
public class SwaggerConfig extends WebMvcConfigurationSupport{  
  
    @Bean  
    public Docket createRestApi() {  
        return new Docket(DocumentationType.SWAGGER_2)  
                .apiInfo(apiInfo())  
                .select()  
                .apis(RequestHandlerSelectors.basePackage("tech.rundeep.api"))  
                .paths(PathSelectors.any())  
                .build();  
    }  
  
    private ApiInfo apiInfo() {  
        return new ApiInfoBuilder()  
                .title("51Thesisi RESTful APIs")  
                .termsOfServiceUrl("http://rundeep.tech")  
                .contact("韩旭东")
                .version("1.0")  
                .build();  
    }  
} 