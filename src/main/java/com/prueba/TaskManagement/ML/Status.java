package com.prueba.TaskManagement.ML;

import jakarta.validation.constraints.NotNull;

public class Status {

    @NotNull(message = "La tarea debe tener un status")
    private int Id;

    private String Name;

    public Status() {
    }

    public Status(int Id, String Name) {
        this.Id = Id;
        this.Name = Name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
}
