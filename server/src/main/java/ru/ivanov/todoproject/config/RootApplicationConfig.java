package ru.ivanov.todoproject.config;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import ru.ivanov.todoproject.dto.ProjectDTO;
import ru.ivanov.todoproject.dto.TaskDTO;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Task;

import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
@ImportResource(locations = "classpath*:config.xml")
@ComponentScan("ru.ivanov")
public class RootApplicationConfig {

    @Bean
    public BoundMapperFacade<Task, TaskDTO> getTaskBoundMapperFacade() {
        final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(Task.class, TaskDTO.class)
                .field("project.id", "projectId")
                .byDefault()
                .register();
        return mapperFactory.getMapperFacade(Task.class, TaskDTO.class);
    }

    @Bean
    public BoundMapperFacade<Project, ProjectDTO> getProjectBoundMapperFacade() {
        final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(Project.class, ProjectDTO.class)
                .byDefault()
                .register();
        return mapperFactory.getMapperFacade(Project.class, ProjectDTO.class);
    }
}
