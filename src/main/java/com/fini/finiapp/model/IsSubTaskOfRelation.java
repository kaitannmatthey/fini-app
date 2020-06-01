package com.fini.finiapp.model;

import org.neo4j.ogm.annotation.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@RelationshipEntity
public class IsSubTaskOfRelation
{
    public static final String TYPE = "IS_SUB_TASK_OF";

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private Task subTask;

    @EndNode
    private Task task;

    public IsSubTaskOfRelation(Task subTask, Task task)
    {
        this.subTask = subTask;
        this.task = task;
    }
}
