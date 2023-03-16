package com.codurance.training.tasks.service.Impl;

import com.codurance.training.tasks.DateHelper;
import com.codurance.training.tasks.Task;
import com.codurance.training.tasks.service.ITaskViewService;

import java.io.PrintWriter;
import java.util.*;

public class TaskViewServiceImpl implements ITaskViewService {
    private Map<String, List<Task>> tasks;
    private PrintWriter out;

    public TaskViewServiceImpl(Map<String, List<Task>> tasks, PrintWriter out) {
        this.tasks = tasks;
        this.out = out;
    }

    @Override
    public void viewByDate() {
        Comparator<Task> compareByDate = Comparator.comparing(p -> DateHelper.parseDate(p.getCreatedDate()));
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            List<Task> newTasks = project.getValue();
            Collections.sort(newTasks, compareByDate);
            for (Task task : newTasks) {
                out.printf("    [%c] %s: %s, deadline: %s, createdDate:%s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription(), task.getDeadline(), task.getCreatedDate());
            }
            out.println();
        }
    }

    @Override
    public void viewByDeadline() {
        Comparator<Task> compareByDate = Comparator.comparing(p -> DateHelper.parseDate(p.getDeadline()));

        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            List<Task> newTasks = project.getValue();
            Collections.sort(newTasks, compareByDate);
            for (Task task : newTasks) {
                out.printf("    [%c] %s: %s, deadline: %s, createdDate:%s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription(), task.getDeadline(), task.getCreatedDate());
            }
            out.println();
        }
    }

    @Override
    public void viewByProject() {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                out.printf("    [%c] %s: %s, deadline: %s, createdDate:%s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription(), task.getDeadline(), task.getCreatedDate());
            }
            out.println();
        }
    }

}
