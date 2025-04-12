package com.todo.services;

import com.todo.models.Task;
import com.todo.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService{

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        tasks.sort((task1, task2) -> {
            if (task1.isCompleted() && !task2.isCompleted()) {
                return 1;
            } else if (!task1.isCompleted() && task2.isCompleted()) {
                return -1;
            } else {
                return task1.getId().compareTo(task2.getId());
            }
        });
        return tasks;
    }

    public void addTask(Task task) {
        taskRepository.save(task);
        new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public void deleteTask(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Invalid task ID: " + id);
        }
    }

    public void toggleTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid task ID: " + id));
        task.setCompleted(!task.isCompleted());

        taskRepository.save(task);
    }
}
