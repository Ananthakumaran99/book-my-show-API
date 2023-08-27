package com.jsp.CloneAPIBookMyShow.util;

import java.util.ArrayList;
import java.util.List;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
public class ApplicationConfig {

	public Docket getDocket() { // non-parameterized method
		Contact contact = new Contact("Anandh", null, "anandabimanyu1999@gmail.com");
		List<VendorExtension> extensions = new ArrayList<VendorExtension>();
		ApiInfo apiInfo = new ApiInfo("BookMyShowCLoneApi", "it is used to book a ticket online", null, null, null,
				null, null);

		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("CloneApiBookMyShowApplication.java")).build()
				.apiInfo(apiInfo).useDefaultResponseMessages(false);

	}

}
