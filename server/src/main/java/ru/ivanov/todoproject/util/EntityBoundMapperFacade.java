package ru.ivanov.todoproject.util;

import ma.glasnost.orika.BoundMapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import ru.ivanov.todoproject.dto.ProjectDTO;
import ru.ivanov.todoproject.dto.TaskDTO;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Task;

import java.util.ArrayList;
import java.util.List;

public class EntityBoundMapperFacade {

    @Autowired
    private BoundMapperFacade<Task, TaskDTO> taskBoundMapper;

    @Autowired
    private BoundMapperFacade<Project, ProjectDTO> projectBoundMapper;

    public TaskDTO getTaskDTO(final Task task) {
        return taskBoundMapper.map(task);
    }

    public ProjectDTO getProjectDTO(final Project project) {
        return projectBoundMapper.map(project);
    }

    public List<TaskDTO> getListTaskDTO(final List<Task> tasks) {
        final List<TaskDTO> taskDTOList = new ArrayList<>();
        for (final Task task : tasks) {
            final TaskDTO taskDTO = taskBoundMapper.map(task);
            taskDTOList.add(taskDTO);
        }
        return taskDTOList;
    }

    public List<ProjectDTO> getListProjectDTO(final List<Project> projects) {
        final List<ProjectDTO> projectDTOList = new ArrayList<>();
        for (final Project project : projects) {
            final ProjectDTO projectDTO = projectBoundMapper.map(project);
            projectDTOList.add(projectDTO);
        }
        return projectDTOList;
    }

    public Task getTaskFromDTO(final TaskDTO taskDTO) {
        return taskBoundMapper.mapReverse(taskDTO);
    }
}
