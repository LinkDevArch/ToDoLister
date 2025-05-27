package org.johancompany.todolister.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;
import java.util.Date;

@Data
public class TaskDTO {

    private Long id;

    @NotBlank(message = "Tittle cannot be blank")
    @Size(max = 200, message = "Tittle size cannot exceed 200 characters")
    private String title;

    @NotBlank(message = "description cannot be blank")
    private String description;

    @NotNull(message = "dueDate cannot be blank")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    private boolean completed = false;

}
