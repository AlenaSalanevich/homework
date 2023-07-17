package com.cisco.training.homeworkapplication.configuration;

import com.cisco.training.homeworkapplication.controller.LexicalController;
import com.cisco.training.homeworkapplication.repository.ContentRepository;
import com.cisco.training.homeworkapplication.service.ContentService;
import com.cisco.training.homeworkapplication.service.LexicalService;
import com.cisco.training.homeworkapplication.service.impl.ContentServiceImpl;
import com.cisco.training.homeworkapplication.service.impl.LexicalServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class ApplicationContextConfiguration {

    @Bean
    public LexicalService lexicalService() {
        return new LexicalServiceImpl();
    }

    @Bean
    public ContentService contentService(ContentRepository repository) {
        return new ContentServiceImpl(repository);
    }


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(LexicalController.class.getPackageName()))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder().title("Small demo application").build());
    }
}
