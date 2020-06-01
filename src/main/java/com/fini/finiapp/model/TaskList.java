package com.fini.finiapp.model;

import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@NodeEntity
public class TaskList
{
    @Id
    @GeneratedValue
    private Long id;

    @Property
    private List<Task> tasks;

    public TaskList(List<Task> tasks)
    {
        this.tasks = tasks;
    }
}
