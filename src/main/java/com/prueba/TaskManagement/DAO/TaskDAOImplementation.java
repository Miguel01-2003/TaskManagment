package com.prueba.TaskManagement.DAO;

import com.prueba.TaskManagement.JPA.Task;
import com.prueba.TaskManagement.ML.Result;
import com.prueba.TaskManagement.JPA.Status;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TaskDAOImplementation implements ITask {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public Result Agregar(com.prueba.TaskManagement.ML.Task Task) {
        Result Resultado = new Result();

        try {

            Task.setCreationDate(LocalDateTime.now());

            Task task = modelMapper.map(Task, Task.class);

            entityManager.persist(task);

            Resultado.correct = true;
        } catch (Exception ex) {
            Resultado.correct = false;
            Resultado.ErrorMessage = ex.getLocalizedMessage();
            Resultado.ex = ex;
        }

        return Resultado;
    }

    @Override
    public Result ObtenerTodo() {
        Result Resultado = new Result();
        try {

            TypedQuery<Task> queryTask = entityManager.createQuery("FROM Task", Task.class);
            List<Task> Tasks = queryTask.getResultList();

            Resultado.objects = new ArrayList<>();

            for (Task task : Tasks) {
                com.prueba.TaskManagement.ML.Task TaskML = modelMapper.map(task, com.prueba.TaskManagement.ML.Task.class);
                Resultado.objects.add(TaskML);
            }

            Resultado.correct = true;
        } catch (Exception ex) {
            Resultado.correct = false;
            Resultado.ErrorMessage = ex.getLocalizedMessage();
            Resultado.ex = ex;
        }
        return Resultado;
    }

    @Override
    public Result ObtenerPorId(int IdTask) {
        Result Resultado = new Result();
        try {

            Task task = entityManager.find(Task.class, IdTask);
            com.prueba.TaskManagement.ML.Task TaskML = modelMapper.map(task, com.prueba.TaskManagement.ML.Task.class);
            Resultado.object = TaskML;
            Resultado.correct = true;
        } catch (Exception ex) {
            Resultado.correct = false;
            Resultado.ErrorMessage = ex.getLocalizedMessage();
            Resultado.ex = ex;
        }
        return Resultado;
    }

    @Override
    @Transactional
    public Result Actualizar(com.prueba.TaskManagement.ML.Task task) {
        Result Resultado = new Result();

        try {
            
            Task taskBd = entityManager.find(Task.class, task.getId());

            Task tasktest = modelMapper.map(task, Task.class);
            tasktest.setCreationDate(taskBd.getCreationDate());
            entityManager.merge(tasktest);

            Resultado.correct = true;
        } catch (Exception ex) {
            Resultado.correct = false;
            Resultado.ErrorMessage = ex.getLocalizedMessage();
            Resultado.ex = ex;
        }

        return Resultado;
    }

    @Override
    @Transactional
    public Result Eliminar(int IdTask) {
        Result Resultado = new Result();

        try {

            Task task = entityManager.find(Task.class, IdTask);
            entityManager.remove(task);

            Resultado.correct = true;
        } catch (Exception ex) {
            Resultado.correct = false;
            Resultado.ErrorMessage = ex.getLocalizedMessage();
            Resultado.ex = ex;
        }

        return Resultado;
    }
}
