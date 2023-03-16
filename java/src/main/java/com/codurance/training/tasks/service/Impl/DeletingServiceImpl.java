package com.codurance.training.tasks.service.Impl;

import com.codurance.training.tasks.Task;
import com.codurance.training.tasks.service.IDeletingService;

import java.io.PrintWriter;
import java.util.*;

public class DeletingServiceImpl implements IDeletingService {
    private Map<String, List<Task>> tasks ;
    private PrintWriter out;

    public DeletingServiceImpl(Map<String, List<Task>> tasks, PrintWriter out) {
        this.tasks = tasks;
        this.out = out;
    }

    @Override
    public void deleteTask(String taskId) {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            if(project.getValue().removeIf(task -> task.getId().equals(taskId))){
                out.printf("Deleted task with id: %s", taskId);
            }
            else{
                out.printf("Not found task with id: %s", taskId);
            }
        }
        out.println();
    }
}
