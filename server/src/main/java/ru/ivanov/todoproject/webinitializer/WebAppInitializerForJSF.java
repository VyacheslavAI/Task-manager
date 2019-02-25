package ru.ivanov.todoproject.webinitializer;

import com.sun.faces.config.FacesInitializer;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import ru.ivanov.todoproject.config.JpaConfig;
import ru.ivanov.todoproject.config.RootApplicationConfig;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebAppInitializerForJSF extends FacesInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        final AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(RootApplicationConfig.class);
        context.register(JpaConfig.class);
        context.setServletContext(servletContext);
        servletContext.addListener(new ContextLoaderListener(context));
        servletContext.addListener(new RequestContextListener());
        final ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        final ServletRegistration.Dynamic cxf = servletContext.addServlet("cxf", new CXFServlet());
        final ServletRegistration.Dynamic faces = servletContext.addServlet("faces", new FacesServlet());
        dispatcher.addMapping("/disp/*");
        cxf.addMapping("/ws/*");
        faces.addMapping("/faces/*");
        dispatcher.setLoadOnStartup(1);
        cxf.setLoadOnStartup(1);
        faces.setLoadOnStartup(1);
    }
}