package net.zgm.configuration;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootConfiguration
public class StaticResourcesConfiguration extends WebMvcConfigurerAdapter {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    /*    registry.addResourceHandler("/static/css/**").addResourceLocations("static/css/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("WEB-INF/fonts/");
        registry.addResourceHandler("/static/img/**").addResourceLocations("static/img/");
        registry.addResourceHandler("/tempFiles/**").addResourceLocations("WEB-INF/tempFiles/");*/
        super.addResourceHandlers(registry);
    }
}
