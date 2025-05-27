package org.johancompany.todolister.repositories;

import org.johancompany.todolister.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
