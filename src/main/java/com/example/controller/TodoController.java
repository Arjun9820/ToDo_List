package com.example.controller;

import com.example.model.Project;
import com.example.model.Todo;
import com.example.repository.ProjectRepository;
import com.example.repository.TodoRepository;
import com.example.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class TodoController {

        private final TodoService todoService;

        public TodoController(TodoService todoService) {
            this.todoService = todoService;
        }

        @PostMapping("/{projectId}")
        public ResponseEntity<Todo> addTodoToProject(@PathVariable Long projectId, @RequestBody Todo todo) {
            Todo savedTodo = todoService.addTodoToProject(projectId, todo);
            return ResponseEntity.ok(savedTodo);
        }

        @GetMapping("/{todoId}")
        public ResponseEntity<Todo> getTodoById(@PathVariable Long todoId) {
            Todo todo = todoService.getTodoById(todoId);
            return ResponseEntity.ok(todo);
        }

        @PutMapping("/{todoId}")
        public ResponseEntity<Todo> updateTodo(@PathVariable Long todoId, @RequestBody Todo updatedTodo) {
            Todo todo = todoService.updateTodo(todoId, updatedTodo);
            return ResponseEntity.ok(todo);
        }

        @DeleteMapping("/{todoId}")
        public ResponseEntity<Void> deleteTodo(@PathVariable Long todoId) {
            todoService.deleteTodo(todoId);
            return ResponseEntity.noContent().build();
        }
    }




