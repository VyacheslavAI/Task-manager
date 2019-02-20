package ru.ivanov.todoproject.util;

import org.primefaces.model.SortOrder;
import ru.ivanov.todoproject.entity.Project;

import java.util.Comparator;

public class ProjectSorter implements Comparator<Project> {

    private String sortField;

    private SortOrder sortOrder;

    public ProjectSorter(final String sortField, final SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    @Override
    public int compare(Project project1, Project project2) {
        try {
            final Object value1 = Project.class.getField(this.sortField).get(project1);
            final Object value2 = Project.class.getField(this.sortField).get(project2);

            final int value = ((Comparable) value1).compareTo(value2);

            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        } catch (Exception e) {
            throw new RuntimeException("Error into compare method");
        }
    }
}
