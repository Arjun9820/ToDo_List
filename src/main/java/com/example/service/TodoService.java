package com.example.service;

import com.example.model.Project;
import com.example.model.Todo;
import com.example.repository.ProjectRepository;
import com.example.repository.TodoRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Setter
@Getter
public class TodoService {

    @Autowired
    private final TodoRepository todoRepository;
    private final ProjectRepository projectRepository;

    public TodoService(TodoRepository todoRepository, ProjectRepository projectRepository) {
        this.todoRepository = todoRepository;
        this.projectRepository = projectRepository;
    }

    public Todo addTodoToProject(Long projectId, Todo todo) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            todo.setProject(project);
            projectRepository.save(project);
            return todoRepository.save(todo);
        } else {
            throw new IllegalArgumentException("Project with id " + projectId + " not found");
        }
    }

    public Todo getTodoById(Long todoId) {
        return todoRepository.findById(todoId)
                .orElseThrow(() -> new IllegalArgumentException("Todo with id " + todoId + " not found"));
    }

    public Todo updateTodo(Long todoId, Todo updatedTodo) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new IllegalArgumentException("Todo with id " + todoId + " not found"));
        return todoRepository.save(todo);
    }

    public void deleteTodo(Long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new IllegalArgumentException("Todo with id " + todoId + " not found"));
        todoRepository.delete(todo);
    }
}
