package com.fini.finiapp.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.fini.finiapp.model.Task;
import com.fini.finiapp.model.TaskList;

public interface TaskListRepository extends Neo4jRepository<TaskList, Long>
{

}
