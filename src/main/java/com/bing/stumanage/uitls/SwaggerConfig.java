package com.bing.stumanage.uitls;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
//@EnableWebMvc
//public class SwaggerConfig extends WebMvcConfigurationSupport {
public class SwaggerConfig {

	@Bean
	public Docket customDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
                .apis(RequestHandlerSelectors.any())
                .build()
//				.pathMapping(PathSelectors.any().toString())
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
	        //页面标题
	        .title("Service API接口验证工具")
	        //创建人
//	        .contact(new Contact("SAT", "http://conf1.travelsky.com", ""))
			//版本号
	        .version("1.0")
	        //描述
	        .description("Service API接口验证工具")
	        .build();
	}

}