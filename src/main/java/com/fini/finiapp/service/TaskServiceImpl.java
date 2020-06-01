package com.fini.finiapp.service;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.util.Assert;

import com.fini.finiapp.model.Difficulty;
import com.fini.finiapp.model.Priority;
import com.fini.finiapp.model.Task;
import com.fini.finiapp.repository.TaskRepository;

public class TaskServiceImpl implements TaskService
{
    LocalDateTime END_OF_DAY = LocalDateTime.now().withHour(23).withMinute(59);

    TaskService    taskService;
    TaskRepository taskRepository;

    TaskServiceImpl(TaskService taskService, TaskRepository taskRepository)
    {
        Assert.notNull(taskService, "TaskService must not be null");
        this.taskService = taskService;

        Assert.notNull(taskRepository, "TaskRespository must not be null");
        this.taskRepository = taskRepository;
    }

    @Override
    public Task saveTask(Task task)
    {
        return taskRepository.save(task);
    }

    @Override
    public Task addSubTaskRelationship(Task subTask, Task task)
    {
        // If subTask does not exist, create it
        if (taskRepository.findById(subTask.getId()) == null)
        {
            taskRepository.save(subTask);
        }

        return taskRepository.addSubTaskRelationship(subTask.getId(), task.getId());
    }

    @Override
    public List<Task> findAllTasks()
    {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> findAllIncompleteTasks()
    {
        return taskRepository.findAllByIsCompleteNotAndIsSubTaskIsNot();
    }

    @Override
    public List<Task> findAllOverdueTasks()
    {
        return taskRepository.findAllByDueAfterAndIsSubTaskIsNot(END_OF_DAY);
    }

    @Override
    public List<Task> findAllTasksDueByToday()
    {
        return taskRepository.findAllByDueBeforeAndIsSubTaskIsNot(END_OF_DAY);
    }

    @Override
    public List<Task> findAllTasksDueByDate(LocalDateTime dateTime)
    {
        return taskRepository.findAllByDueBeforeAndIsSubTaskIsNot(dateTime);
    }

    @Override
    public Set<Task> getSortedDueToday()
    {
        Set<Task> taskList = new HashSet<>();
        List<List<Task>> listToAdd = new ArrayList<>();

        List<Task> highPriorityEasyTasks = taskRepository
            .findAllByPriorityAndDifficultyAndIsSubTaskIsNotAndDueBefore(Priority.HIGH,
                Difficulty.EASY, END_OF_DAY);
        highPriorityEasyTasks.sort((t1, t2) -> t2.getCreated().compareTo(t1.getCreated()));
        listToAdd.add(highPriorityEasyTasks);

        List<Task> highPriorityMediumTasks = taskRepository
            .findAllByPriorityAndDifficultyAndIsSubTaskIsNotAndDueBefore(Priority.HIGH,
                Difficulty.MEDIUM, END_OF_DAY);
        highPriorityMediumTasks.sort((t1, t2) -> t2.getCreated().compareTo(t1.getCreated()));
        listToAdd.add(highPriorityMediumTasks);

        List<Task> highPriorityHardTasks = taskRepository
            .findAllByPriorityAndDifficultyAndIsSubTaskIsNotAndDueBefore(Priority.HIGH,
                Difficulty.HARD, END_OF_DAY);
        highPriorityHardTasks.sort((t1, t2) -> t2.getCreated().compareTo(t1.getCreated()));
        listToAdd.add(highPriorityHardTasks);

        List<Task> mediumPriorityEasyTasks = taskRepository
            .findAllByPriorityAndDifficultyAndIsSubTaskIsNotAndDueBefore(Priority.MEDIUM,
                Difficulty.EASY, END_OF_DAY);
        mediumPriorityEasyTasks.sort((t1, t2) -> t2.getCreated().compareTo(t1.getCreated()));
        listToAdd.add(mediumPriorityEasyTasks);

        List<Task> mediumPriorityMediumTasks = taskRepository
            .findAllByPriorityAndDifficultyAndIsSubTaskIsNotAndDueBefore(Priority.MEDIUM,
                Difficulty.MEDIUM, END_OF_DAY);
        mediumPriorityMediumTasks.sort((t1, t2) -> t2.getCreated().compareTo(t1.getCreated()));
        listToAdd.add(mediumPriorityMediumTasks);

        List<Task> mediumPriorityHardTasks = taskRepository
            .findAllByPriorityAndDifficultyAndIsSubTaskIsNotAndDueBefore(Priority.MEDIUM,
                Difficulty.HARD, END_OF_DAY);
        mediumPriorityHardTasks.sort((t1, t2) -> t2.getCreated().compareTo(t1.getCreated()));
        listToAdd.add(mediumPriorityHardTasks);

        List<Task> lowPriorityEasyTasks = taskRepository
            .findAllByPriorityAndDifficultyAndIsSubTaskIsNotAndDueBefore(Priority.LOW,
                Difficulty.EASY, END_OF_DAY);
        lowPriorityEasyTasks.sort((t1, t2) -> t2.getCreated().compareTo(t1.getCreated()));
        listToAdd.add(lowPriorityEasyTasks);

        List<Task> lowPriorityMediumTasks = taskRepository
            .findAllByPriorityAndDifficultyAndIsSubTaskIsNotAndDueBefore(Priority.LOW,
                Difficulty.MEDIUM, END_OF_DAY);
        lowPriorityMediumTasks.sort((t1, t2) -> t2.getCreated().compareTo(t1.getCreated()));
        listToAdd.add(lowPriorityMediumTasks);

        List<Task> lowPriorityHardTasks = taskRepository
            .findAllByPriorityAndDifficultyAndIsSubTaskIsNotAndDueBefore(Priority.LOW,
                Difficulty.HARD, END_OF_DAY);
        lowPriorityHardTasks.sort((t1, t2) -> t2.getCreated().compareTo(t1.getCreated()));
        listToAdd.add(lowPriorityHardTasks);

        for (List<Task> listOfTasks : listToAdd)
        {
            for (Task task : listOfTasks)
            {
                taskList.add(task);
            }
        }

        return taskList;
    }

}
