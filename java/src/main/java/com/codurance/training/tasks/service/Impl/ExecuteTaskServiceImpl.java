package com.codurance.training.tasks.service.Impl;

import com.codurance.training.tasks.Task;
import com.codurance.training.tasks.service.*;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class ExecuteTaskServiceImpl implements IExecuteTaskService {
    private Map<String, List<Task>> tasks;
    private BufferedReader in;
    private PrintWriter out;
    private IAddingService addingService;
    private ICheckService checkService;
    private IShowService showService;
    private IHelpService helpService;
    private ITodayDueTasks todayDueTasks;
    private IDeletingService deletingService;
    private ITaskViewService taskViewService;

    public ExecuteTaskServiceImpl(Map<String, List<Task>> tasks, BufferedReader in, PrintWriter out) {
        this.tasks = tasks;
        this.in = in;
        this.out = out;
        addingService = new AddingServiceImpl(tasks,this.out);
        checkService = new CheckServiceImpl(tasks,this.out);
        showService = new ShowServiceImpl(tasks, this.out);
        helpService = new HelpServiceImpl(this.out);
        todayDueTasks = new TodayDueTasksImpl(tasks, this.out);
        deletingService = new DeletingServiceImpl(tasks, this.out);
        taskViewService = new TaskViewServiceImpl(tasks, this.out);
    }

    @Override
    public void execute(String commandLine) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                showService.show();
                break;
            case "add":
                addingService.add(commandRest[1]);
                break;
            case "check":
                checkService.check(commandRest[1]);
                break;
            case "uncheck":
                checkService.uncheck(commandRest[1]);
                break;
            case "today":
                todayDueTasks.showTodayDueTasks();
                break;
            case "delete":
                deletingService.deleteTask(commandRest[1]);
                break;
            case "view":
                String viewBy = commandRest[1];
                switch (viewBy){
                    case "byDate":
                        taskViewService.viewByDate();
                        break;
                    case "byDeadline":
                        taskViewService.viewByDeadline();
                        break;
                    case "byProject":
                        taskViewService.viewByProject();
                        break;
                }
                break;
            case "help":
                helpService.help();
                break;
            default:
                error(command);
                break;
        }
    }
    private void error(String command) {
        out.printf("I don't know what the command \"%s\" is.", command);
        out.println();
    }
}
