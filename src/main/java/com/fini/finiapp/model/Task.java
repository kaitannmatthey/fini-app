package com.fini.finiapp.model;

import java.time.LocalDateTime;
import java.util.List;

import org.neo4j.ogm.annotation.*;

import lombok.*;

@Data
@NoArgsConstructor
@NodeEntity
public class Task
{
    @Id
    @GeneratedValue
    private Long id;

    @Required
    @Property
    private Difficulty difficulty;

    @Required
    @Property
    private Priority priority;

    @Property
    private LocalDateTime due;

    @Property
    private Double estimatedTime;

    @Property
    private LocalDateTime created;

    @Required
    @Property
    private String description;

    @Property
    private Boolean isComplete = false;

    @Property
    private LocalDateTime completionDate;

    @Property
    private Boolean repeat = false;

    @Property
    private List<LocalDateTime> datesToRepeat;

    @Property
    private Boolean isSubTask = false;

    @Relationship(type = IsSubTaskOfRelation.TYPE, direction = Relationship.INCOMING)
    private List<Task> subTasks;

    public Task(Difficulty difficulty, Priority priority, LocalDateTime due, Double estimatedTime,
        LocalDateTime created, String description, Boolean isComplete, LocalDateTime completionDate,
        Boolean repeat, List<LocalDateTime> datesToRepeat, Boolean isSubTask)
    {
        this.difficulty = difficulty;
        this.priority = priority;
        this.due = due;
        this.estimatedTime = estimatedTime;
        this.created = created;
        this.description = description;
        this.isComplete = isComplete;
        this.completionDate = completionDate;
        this.repeat = repeat;
        this.datesToRepeat = datesToRepeat;
        this.isSubTask = isSubTask;
    }

    public Task(Difficulty difficulty, Priority priority, LocalDateTime due, Double estimatedTime,
        LocalDateTime created, String description, Boolean isComplete, LocalDateTime completionDate,
        Boolean repeat, List<LocalDateTime> datesToRepeat, Boolean isSubTask, List<Task> subTasks)
    {
        this.difficulty = difficulty;
        this.priority = priority;
        this.due = due;
        this.estimatedTime = estimatedTime;
        this.created = created;
        this.description = description;
        this.isComplete = isComplete;
        this.completionDate = completionDate;
        this.repeat = repeat;
        this.datesToRepeat = datesToRepeat;
        this.isSubTask = isSubTask;
        this.subTasks = subTasks;
    }
}
