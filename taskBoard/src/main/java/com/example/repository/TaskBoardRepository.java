package com.example.repository;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.model.TaskBoard;

@Repository
public interface TaskBoardRepository extends MongoRepository<TaskBoard, String> {
}
