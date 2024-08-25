package org.theaz.karabookapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
public class SpringMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        // Додавання обробника для статичних ресурсів (CSS, JS, зображення)
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/"); // Тут мають бути розташовані ваші статичні файли
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        // Налаштування ViewResolver для обробки HTML-сторінок
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");  // Де знаходяться ваші HTML або JSP файли
        viewResolver.setSuffix(".html");  // Розширення файлів, які ви використовуєте для представлень
        registry.viewResolver(viewResolver);
    }

    @Bean
    public InternalResourceViewResolver defaultViewResolver() {
        return new InternalResourceViewResolver();
    }
}
