package ru.ivanov.todoproject;

import java.util.ArrayList;
import java.util.List;

public class Operation {

    public static final List<String> commands = new ArrayList<>();

    public static final String HELP = "help";

    public static final String PROJECT_CREATE = "create project";

    public static final String PROJECT_READ = "read project";

    public static final String PROJECT_UPDATE = "update project";

    public static final String PROJECT_DELETE = "delete project";

    public static final String PROJECT_SHOW = "show project";

    public static final String TASK_CREATE = "create task";

    public static final String TASK_READ = "read task";

    public static final String TASK_UPDATE = "update task";

    public static final String TASK_DELETE = "delete task";

    public static final String TASK_SHOW = "show task";

    public static final String BIN_SAVE = "save bin";

    public static final String BIN_LOAD = "load bin";

    public static final String XML_SAVE = "save xml";

    public static final String XML_LOAD = "load xml";

    public static final String JSON_SAVE = "save json";

    public static final String JSON_LOAD = "loadjson";

    public static final String EXIT = "exit";

    static {
        commands.add(HELP);
        commands.add(PROJECT_CREATE);
        commands.add(PROJECT_READ);
        commands.add(PROJECT_UPDATE);
        commands.add(PROJECT_DELETE);
        commands.add(PROJECT_SHOW);
        commands.add(TASK_CREATE);
        commands.add(TASK_READ);
        commands.add(TASK_UPDATE);
        commands.add(TASK_DELETE);
        commands.add(TASK_SHOW);
        commands.add(BIN_SAVE);
        commands.add(BIN_LOAD);
        commands.add(EXIT);
    }

}
