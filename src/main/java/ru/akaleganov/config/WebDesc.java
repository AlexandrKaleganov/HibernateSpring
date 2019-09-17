package ru.akaleganov.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import ru.akaleganov.filter.AuthFilter;

import javax.servlet.Filter;

public class WebDesc extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringRootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringWebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/", "/*/", "/*/*", "/*/*/*"};
    }


    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{new AuthFilter()};
    }
}
