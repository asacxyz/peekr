package com.asacxyz.peekr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.asacxyz.peekr.model.Filter;
import com.asacxyz.peekr.model.Task;
import com.asacxyz.peekr.service.TaskService;

@Controller
public class TaskController {
    @Autowired
    private TaskService taskService;

    @QueryMapping
    public List<Task> tasksByFilters(@Argument("filters") List<Filter> filters) {
        return this.taskService.tasksByFilters(filters);
    }
}
