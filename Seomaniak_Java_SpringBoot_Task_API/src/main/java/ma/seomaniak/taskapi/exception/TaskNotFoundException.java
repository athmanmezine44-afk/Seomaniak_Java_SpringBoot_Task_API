package ma.seomaniak.taskapi.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long id) {
        super("Aucune tâche trouvée avec l'identifiant " + id);
    }
}
