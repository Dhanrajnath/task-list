package com.codurance.training.tasks.service.Impl;

import com.codurance.training.tasks.Task;
import com.codurance.training.tasks.service.IAddingService;
import com.codurance.training.tasks.service.IAddingTaskService;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddingServiceImpl implements IAddingService, IAddingTaskService {
    private Map<String, List<Task>> tasks = new LinkedHashMap<>();
    private PrintWriter out;

    private long lastId = 0;

    public AddingServiceImpl(Map<String, List<Task>> tasks, PrintWriter out) {
        this.tasks = tasks;
        this.out = out;
    }

    private long nextId() {
        return ++lastId;
    }

    private boolean isValidIdentifier(String id) {
        if(id.contains(" "))
            return false;

        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcher = pattern.matcher(id);
        return !(matcher.find());
    }

    @Override
    public void add(String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            addProject(subcommandRest[1]);
        } else if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split(" ");
            if (projectTask.length == 4){
                try {
                    addTask(projectTask[1], projectTask[0], projectTask[2], new SimpleDateFormat("dd-MM-yyyy").parse(projectTask[3]));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                addTask(projectTask[1], projectTask[0], projectTask[2], null);
            }
        }
    }

    @Override
    public void addProject(String name) {
        tasks.put(name, new ArrayList<Task>());
    }

    @Override
    public void addTask(String taskId,String project, String description, Date deadline) {
        List<Task> projectTasks = tasks.get(project);
        if (projectTasks == null) {
            out.printf("Could not find a project with the name \"%s\".", project);
            out.println();
            return;
        }
        if (isValidIdentifier(taskId)){
            projectTasks.add(new Task(taskId, description, false, deadline, new Date()));
        }
        else{
            out.printf("Identifier ID is not valid:", taskId);
            out.printf("Using the auto-increment value by default...", String.valueOf(nextId()));
            projectTasks.add(new Task(String.valueOf(nextId()), description, false, deadline, new Date()));
        }
    }
}
