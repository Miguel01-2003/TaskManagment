package com.prueba.TaskManagement.RestController;

import com.prueba.TaskManagement.DAO.TaskDAOImplementation;
import com.prueba.TaskManagement.ML.Result;
import com.prueba.TaskManagement.ML.Task;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/tasks")
public class TaskRestController {
    
    @Autowired
    private TaskDAOImplementation taskDAOImplementation;
    
    @GetMapping
    public ResponseEntity ObtenerTodo(){
        List<Task> Tasks = new ArrayList<>();
        
        Result Resultado = taskDAOImplementation.ObtenerTodo();
        
        if(Resultado.correct){
            
            if(Resultado.objects.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(Resultado);
        }else{
            return ResponseEntity.badRequest().body(Resultado);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity ObtenerPorId(@PathVariable("id") int IdTask){
        Result Resultado = taskDAOImplementation.ObtenerPorId(IdTask);
        
        if(Resultado.correct){
            
            if(Resultado.object == null){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(Resultado.object);
        }else{
            return ResponseEntity.badRequest().body(Resultado);
        }
    }
    
    @PostMapping
    public ResponseEntity Agregar(@Valid @RequestBody Task task){
        
        Result Resultado = taskDAOImplementation.Agregar(task);
        
        if(Resultado.correct){
            return ResponseEntity.ok(Resultado);
        }else{
            return ResponseEntity.badRequest().body(Resultado);
        }
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity Actualizar(@Valid @RequestBody Task task, @PathVariable("id") int IdTask){
        task.setId(IdTask);
        Result Resultado = taskDAOImplementation.Actualizar(task);
        
        if(Resultado.correct){
            
            return ResponseEntity.ok(Resultado);
            
        }else{
            return ResponseEntity.badRequest().build();
        }
        
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity Eliminar(@PathVariable("id") int IdTask){
        Result Resultado = taskDAOImplementation.Eliminar(IdTask);
        
        if(Resultado.correct){
            
            return ResponseEntity.ok(Resultado);
        }else{
            return ResponseEntity.badRequest().body(Resultado);
        }
    }
    
    
    @PostMapping("/prueba")
    public ResponseEntity Prueba(@Valid @RequestBody Task task){
        Result Resultado = new Result();
        
        Resultado.object = task;
        Resultado.correct = true;
        return ResponseEntity.ok(Resultado);
    }
}
