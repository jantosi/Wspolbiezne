package im.komitywa.wspolbiezne.zadanie4.api;

public interface Server extends Runnable {
    void addTaskToQueue(Task task);
    TaskResult processTask(Task task);
    Task getNextTaskFromQueue();
}
