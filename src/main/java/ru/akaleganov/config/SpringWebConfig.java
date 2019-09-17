package ru.akaleganov.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * прописываем настройки mvc они аналогичны настройкам, которые мы прописывали в xml
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "ru.akaleganov.container")
public class SpringWebConfig implements WebMvcConfigurer {
    /**
     * регистрируем путь к ресурсам
     * это не те ресурсы, которые лежат в ресурсах, это ресурсы, которые лежат в папке
     * webapp
     * @param registry регистрация ресурсов
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
       registry.addResourceHandler("/resources/**")
               .addResourceLocations("/resources");

    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
