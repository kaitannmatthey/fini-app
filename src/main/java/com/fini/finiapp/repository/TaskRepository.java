package com.fini.finiapp.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.fini.finiapp.model.Difficulty;
import com.fini.finiapp.model.Priority;
import com.fini.finiapp.model.Task;

public interface TaskRepository extends Neo4jRepository<Task, Long>
{
    Task findTaskByDescriptionContains(String description);

    Task findTaskByDescriptionContainsAndIsCompleteNot(String description);

    List<Task> findAll();

    List<Task> findAllByIsCompleteNotAndIsSubTaskIsNot();

    List<Task> findAllByDueBeforeAndIsSubTaskIsNot(LocalDateTime dateTime);

    List<Task> findAllByDueAfterAndIsSubTaskIsNot(LocalDateTime dateTime);

    List<Task> findAllByPriorityAndDifficultyAndIsSubTaskIsNotAndDueBefore(Priority priority,
        Difficulty difficulty, LocalDateTime dateTime);

    @Query("MATCH (s:Task)-[:IS_SUB_TASK_OF]->(t:Task {id: {0}}) RETURN s")
    List<Task> findAllSubTasksByTaskId(Long taskId);

    @Query(
        "MATCH (s:Task {id: {0}}), (t:Task {id: {1}}) CREATE (s)-[:IS_SUB_TASK_OF]->(t) RETURN s")
    Task addSubTaskRelationship(Long subTaskId, Long taskId);
}
