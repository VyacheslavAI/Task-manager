package ru.ivanov.todoproject.webinitializer;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import ru.ivanov.todoproject.config.JpaConfig;
import ru.ivanov.todoproject.config.RootApplicationConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebAppInitializerForJSF implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        final AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(RootApplicationConfig.class);
        context.register(JpaConfig.class);
        context.setServletContext(servletContext);
        servletContext.addListener(new ContextLoaderListener(context));
        servletContext.addListener(new RequestContextListener());
        final ServletRegistration.Dynamic dynamic = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        dynamic.addMapping("/web");
        dynamic.setLoadOnStartup(1);
    }
}