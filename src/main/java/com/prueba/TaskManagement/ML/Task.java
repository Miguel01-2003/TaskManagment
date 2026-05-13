package com.prueba.TaskManagement.ML;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

public class Task {

    private int Id;

    @NotEmpty(message = "El titulo no puede estar vacio")
    private String Title;

    @Max(value = 250, message = "La descripcion de be contener maximo 250 caracteres")
    private String Description;

    @NotEmpty(message = "El Status no puede estar vacio")
    public Status Status;

    private LocalDateTime CreationDate;

    public Task() {
    }

    public Task(int Id, String Title, String Description, Status Status, LocalDateTime CreationDate) {
        this.Id = Id;
        this.Title = Title;
        this.Description = Description;
        this.Status = Status;
        this.CreationDate = CreationDate;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Status getStatus() {
        return Status;
    }

    public void setStatus(Status Status) {
        this.Status = Status;
    }

    public LocalDateTime getCreationDate() {
        return CreationDate;
    }

    public void setCreationDate(LocalDateTime CreationDate) {
        this.CreationDate = CreationDate;
    }
}
