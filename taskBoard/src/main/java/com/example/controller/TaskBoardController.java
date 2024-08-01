package com.example.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.model.TaskBoard;
import com.example.repository.TaskBoardRepository;
import com.example.service.TaskBoardService;

@RestController
@RequestMapping("/api/taskBoard")  // Base URL for this controller
public class TaskBoardController {

    
    //private TaskBoardService taskBoardService;
    
    

//    @PostMapping("/")
//    public ResponseEntity<TaskBoard> createTask(@RequestBody TaskBoard taskBoard) {
//        TaskBoard savedTask = taskBoardService.save(taskBoard);
//        return ResponseEntity.ok(savedTask);  // Respond with the created task
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<TaskBoard> getTask(@PathVariable String id) {
//        return taskBoardService.getTaskById(id)
//                .map(task -> ResponseEntity.ok(task))
//                .orElse(ResponseEntity.notFound().build());
//    }
    
    
//    @GetMapping("/{id}")
//    public ResponseEntity<TaskBoard> getTask(@PathVariable String id) {
//        return taskBoardService.getTaskById(id)
//                .map(task -> ResponseEntity.ok(task))
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    // Endpoint to create a new task
//    @PostMapping("/")
//    public ResponseEntity<TaskBoard> createTask(@RequestBody TaskBoard taskBoard) {
//        TaskBoard savedTask = taskBoardService.saveTask(taskBoard);
//        return ResponseEntity.ok(savedTask);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
//        taskBoardService.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }

    // Additional endpoints can be added here
	
	@Autowired
	TaskBoardRepository repo;
	
	@GetMapping("/")
	public ResponseEntity<List<TaskBoard>> getAllTaskBoard(){
		try {
			return new ResponseEntity<List<TaskBoard>>(repo.findAll(),HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TaskBoard> getAllTaskBoardById(@PathVariable String id){
		try {
			return new ResponseEntity<TaskBoard>(repo.findById(id).get(),HttpStatus.OK);
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseEntity(null,HttpStatus.OK);
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<TaskBoard> createTaskBoard(@RequestBody TaskBoard taskBoard){
		try {
			return new ResponseEntity<TaskBoard>(repo.save(taskBoard), HttpStatus.CREATED);
		}catch(Exception e) {
			e.printStackTrace();
			return new  ResponseEntity(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//Update Operation
	@PutMapping("{id}")
	public  ResponseEntity<TaskBoard> updateTaskBoard(@RequestBody TaskBoard taskBoard){
		Optional<TaskBoard> taskBoardData=repo.findById(taskBoard.getId());
		if(taskBoardData.isPresent()) {
			TaskBoard updatedTaskBoard=new TaskBoard();
			updatedTaskBoard.setTaskId(taskBoard.getTaskId());
			updatedTaskBoard.setDate(taskBoard.getDate());
			updatedTaskBoard.setTaskType(taskBoard.getTaskType());
			updatedTaskBoard.setProjectName(taskBoard.getProjectName());
			updatedTaskBoard.setProjectOwnerId(taskBoard.getProjectOwnerId());
			updatedTaskBoard.setEmployeeId(taskBoard.getEmployeeId());
			updatedTaskBoard.setResourceName(taskBoard.getResourceName());
			updatedTaskBoard.setTitle(taskBoard.getTitle());
			updatedTaskBoard.setTitleDescription(taskBoard.getTitleDescription());
			updatedTaskBoard.setTaskStatus(taskBoard.getTaskStatus());
			updatedTaskBoard.setEstimationTime(taskBoard.getEstimationTime());
			updatedTaskBoard.setActualTime(taskBoard.getActualTime());
			updatedTaskBoard.setComments(taskBoard.getComments());
			return new ResponseEntity<TaskBoard>(repo.save(updatedTaskBoard),HttpStatus.OK);
		}
		return new ResponseEntity(null,HttpStatus.NOT_FOUND);
	}
	
	//Delete Operation
	@DeleteMapping("{id}")
	public ResponseEntity<HttpStatus> deleteTaskBoard(@PathVariable String id){
		Optional<TaskBoard> taskBoardData=repo.findById(id);
		if(taskBoardData.isPresent()) {
			repo.deleteById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		return new ResponseEntity(null,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
