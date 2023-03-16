package com.codurance.training.tasks.service;

import java.util.Date;

public interface IAddingTaskService {
    void addTask(String taskId, String project, String description, Date deadline);
}
