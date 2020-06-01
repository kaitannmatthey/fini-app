package com.fini.finiapp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.fini.finiapp.model.Task;

public interface TaskService
{
    public Task saveTask(Task task);

    Task addSubTaskRelationship(Task subTask, Task task);

    List<Task> findAllTasks();

    List<Task> findAllIncompleteTasks();

    List<Task> findAllOverdueTasks();

    List<Task> findAllTasksDueByToday();

    List<Task> findAllTasksDueByDate(LocalDateTime dateTime);

    Set<Task> getSortedDueToday();
}
