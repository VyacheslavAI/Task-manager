package com.slava.webinitializer;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

public class XmlWebInitializer {// extends AbstractDispatcherServletInitializer {

    protected WebApplicationContext createServletApplicationContext() {
        final XmlWebApplicationContext context = new XmlWebApplicationContext();
        context.setConfigLocation("/WEB-INF/dispatcherservlet.xml");
        return context;
    }

    protected String[] getServletMappings() {
        return new String[]{"/first/*"};
    }

    protected WebApplicationContext createRootApplicationContext() {
        return null;
    }
}
