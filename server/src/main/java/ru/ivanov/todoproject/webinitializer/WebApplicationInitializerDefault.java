package ru.ivanov.todoproject.webinitializer;

import ru.ivanov.todoproject.config.JpaConfig;
import ru.ivanov.todoproject.config.WebApplicationConfig;

public class WebApplicationInitializerDefault {//extends AbstractAnnotationConfigDispatcherServletInitializer {

    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{JpaConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebApplicationConfig.class};
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
