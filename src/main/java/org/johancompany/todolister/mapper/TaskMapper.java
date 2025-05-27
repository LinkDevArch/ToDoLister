package org.johancompany.todolister.mapper;

import org.johancompany.todolister.domain.Task;
import org.johancompany.todolister.dtos.TaskDTO;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    //Map Object
    TaskDTO mapToDto(Task task);
    Task mapToTask(TaskDTO taskDTO);


    //Map Object List
    List<TaskDTO> mapToDtos(List<Task> tasks);
    List<Task> mapToTasks(List<TaskDTO> taskDTOs);
}
