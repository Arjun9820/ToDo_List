package com.example.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Setter
@Getter
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String status;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;


    public void setProject(Project project) {
    }
}
