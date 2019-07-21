package com.yunhe.todo.service;

import com.yunhe.todo.model.Task;
import com.yunhe.todo.repos.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public List<Task> getTasks() {
        return repository.findAll(new Sort(DESC, "id"));
    }

    public Task getTask(int id) {
        return repository.findById(id).get();
    }

    public Task saveTask(Task task) {
        return repository.save(task);
    }

    public void deleteTask(int id) {
        repository.deleteById(id);
    }

    public List<Task> getTasksByName(String name) {
        return repository.findByNameLike("%"+name+"%");
    }

}
