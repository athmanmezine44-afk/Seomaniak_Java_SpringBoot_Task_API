package ma.seomaniak.taskapi.repository;

import ma.seomaniak.taskapi.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
