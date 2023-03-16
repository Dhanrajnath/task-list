package com.codurance.training.tasks.service.Impl;

import com.codurance.training.tasks.Task;
import com.codurance.training.tasks.service.ITaskViewService;

import java.io.PrintWriter;
import java.util.*;

public class TaskViewByProject implements ITaskViewService {
    private Map<String, List<Task>> tasks;
    private PrintWriter out;

    public TaskViewByProject(Map<String, List<Task>> tasks, PrintWriter out) {
        this.tasks = tasks;
        this.out = out;
    }

    @Override
    public void view() {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                out.printf("    [%c] %s: %s, deadline: %s, createdDate:%s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription(), task.getDeadline(), task.getCreatedDate());
            }
            out.println();
        }
    }
}
