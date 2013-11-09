package im.komitywa.wspolbiezne.zadanie4;

public interface Server extends Runnable {
    void addTaskToQueue(Task task);
    TaskResult processTask(Task task);
    Task getNextTaskFromQueue();
}
