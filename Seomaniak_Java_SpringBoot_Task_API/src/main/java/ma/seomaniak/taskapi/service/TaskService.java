package ma.seomaniak.taskapi.service;

import ma.seomaniak.taskapi.exception.TaskNotFoundException;
import ma.seomaniak.taskapi.model.Task;
import ma.seomaniak.taskapi.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Task findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

    public Task create(Task task) {
        task.setId(null);
        return repository.save(task);
    }

    public Task update(Long id, Task input) {
        Task existing = findById(id);
        existing.setTitle(input.getTitle());
        existing.setDescription(input.getDescription());
        existing.setCompleted(input.isCompleted());
        return repository.save(existing);
    }

    public void delete(Long id) {
        Task existing = findById(id);
        repository.delete(existing);
    }
}
