package org.johancompany.todolister.services;

import jakarta.validation.Valid;
import org.johancompany.todolister.dtos.TaskDTO;
import org.johancompany.todolister.mapper.TaskMapper;
import org.johancompany.todolister.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;


    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    //Methods
    public List<TaskDTO> getAllTasks() throws Exception {
        if (taskRepository.findAll().isEmpty()) {
            throw  new Exception("There are no tasks to show");
        }
        else {
            return taskMapper.mapToDtos(taskRepository.findAll());
        }
    }

    public Optional<TaskDTO> getTaskById(Long id) throws Exception {
        Optional<TaskDTO> taskDTO = taskRepository.findById(id)
                .map(taskMapper::mapToDto);

        if (taskDTO.isPresent()) {
            return taskDTO;
        }
        else {
            throw  new Exception("Task not found");
        }
    }

    public void createTask(@Valid TaskDTO taskDTO) {
        taskRepository.save(taskMapper.mapToTask(taskDTO));
    }

    public List<TaskDTO> getCompletedTasks() {
        List<TaskDTO> tasks = new ArrayList<>();
        taskRepository.findAll().forEach(task -> {
            if (task.isCompleted()) {
                tasks.add(taskMapper.mapToDto(task));
            }
        });
        return tasks;
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public TaskDTO toggleTaskStatus(Long id) throws Exception {
        // 1. Obtener y actualizar el TaskDTO
        TaskDTO taskDTO = getTaskById(id)
                .orElseThrow(() -> new Exception("Tarea no encontrada con ID: " + id));
        taskDTO.setCompleted(!taskDTO.isCompleted());

        // 2. Guardar y retornar directamente como DTO
        return taskMapper.mapToDto(taskRepository.save(taskMapper.mapToTask(taskDTO)));
    }


}
