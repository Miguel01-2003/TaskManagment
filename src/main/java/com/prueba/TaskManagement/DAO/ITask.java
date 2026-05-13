package com.prueba.TaskManagement.DAO;

import com.prueba.TaskManagement.ML.Result;
import com.prueba.TaskManagement.ML.Task;

public interface ITask {
    
    Result Agregar(Task Task);
    
    Result ObtenerTodo();
    
    Result ObtenerPorId(int IdTask);
    
    Result Actualizar(Task Task);
    
    Result Eliminar(int IdTask);
}
