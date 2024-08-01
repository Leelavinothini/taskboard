package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.TaskBoard;
import com.example.repository.TaskBoardRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskBoardService {
    
    @Autowired
    private TaskBoardRepository repository;
    
    public Optional<TaskBoard> getTaskById(String id) {
        return repository.findById(id);
    }

    public TaskBoard saveTask(TaskBoard taskBoard) {
        return repository.save(taskBoard);
    }
    
    public List<TaskBoard> findAll() {
        return repository.findAll();
    }
    
//    public Optional<TaskBoard> findById(String id) {
//        return repository.findById(id);
//    }
    
    public void deleteById(String id) {
        repository.deleteById(id);
    }
    
    // Other business logic methods if needed
}

