package com.codurance.training.tasks.service.Impl;

import com.codurance.training.tasks.DateHelper;
import com.codurance.training.tasks.Task;
import com.codurance.training.tasks.service.ITodayDueTasks;

import java.io.PrintWriter;
import java.util.*;

public class TodayDueTasksImpl implements ITodayDueTasks {
    private Map<String, List<Task>> tasks;
    private PrintWriter out;

    public TodayDueTasksImpl(Map<String, List<Task>> tasks, PrintWriter out) {
        this.tasks = tasks;
        this.out = out;
    }

    @Override
    public void showTodayDueTasks() {
        Date today = new Date();
        for (Map.Entry<String, List<Task>> project: tasks.entrySet()){
            for (Task task: project.getValue()){
                if (task.getDeadline() != null && DateHelper.parseDate(task.getDeadline()).equals(DateHelper.parseDate(today))) {
                    out.printf("    [%c] %d: %s , deadline: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription(), task.getDeadline());
                }
            }
            out.println();
        }
    }
}
