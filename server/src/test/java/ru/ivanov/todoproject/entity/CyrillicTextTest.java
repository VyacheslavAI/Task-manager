package ru.ivanov.todoproject.entity;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test for verifying Cyrillic text support in Task Manager entities
 */
public class CyrillicTextTest {

    @Test
    public void testProjectCyrillicName() {
        Project project = new Project();
        assertEquals("цйупц", project.getName());
        
        // Test setting Cyrillic text
        project.setName("новое имя проекта");
        assertEquals("новое имя проекта", project.getName());
    }

    @Test
    public void testTaskCyrillicName() {
        Task task = new Task();
        assertEquals("цйупц", task.getName());
        
        // Test setting Cyrillic text
        task.setName("новая задача");
        assertEquals("новая задача", task.getName());
    }
    
    @Test
    public void testCyrillicTextLength() {
        Project project = new Project();
        String cyrillicText = project.getName();
        assertTrue("Cyrillic text should have proper length", cyrillicText.length() > 0);
        assertEquals("цйупц should be 5 characters", 5, cyrillicText.length());
    }
}