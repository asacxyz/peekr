package com.asacxyz.peekr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asacxyz.peekr.model.Filter;
import com.asacxyz.peekr.model.Task;
import com.asacxyz.peekr.repository.TaskRepository;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> tasksByFilters(List<Filter> filters) {
        return this.taskRepository.tasksByFilters(filters);
    }
}
