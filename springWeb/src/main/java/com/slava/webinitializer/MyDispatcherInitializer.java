package com.slava.webinitializer;

import com.slava.config.WebConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class MyDispatcherInitializer {//implements WebApplicationInitializer {

//    @Override
    public void onStartup(final ServletContext servletContext) throws ServletException {
//        final AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//        context.register(WebConfig.class);
//        context.refresh();

        final XmlWebApplicationContext context = new XmlWebApplicationContext();
        context.setConfigLocation("/WEB-INF/dispatcherservlet.xml");

        final ServletRegistration.Dynamic registration = servletContext.addServlet("dispInit", new DispatcherServlet(context));
        registration.setLoadOnStartup(1);
        registration.addMapping("/dispInit/*");
    }
}
