package net.zgm;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(meta())
                .select()
                .apis(RequestHandlerSelectors.basePackage("net.zgm.controller"))//扫描的包路径
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo meta() {
        return new ApiInfoBuilder()
                .title("走国门API")
                .description("王先升新闻模块接口文档")
                .version("0.0.1")
                .build();
    }

}
