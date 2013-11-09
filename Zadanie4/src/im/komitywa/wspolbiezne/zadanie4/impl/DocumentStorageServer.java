package im.komitywa.wspolbiezne.zadanie4.impl;

import im.komitywa.wspolbiezne.zadanie4.api.Server;
import im.komitywa.wspolbiezne.zadanie4.api.Task;
import im.komitywa.wspolbiezne.zadanie4.api.TaskResult;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class DocumentStorageServer implements Server {
    private Queue<Task> taskQueue;
    private ArrayList<ArrayList<Document>> documents;


    public DocumentStorageServer(int capacity) {
        taskQueue = new ConcurrentLinkedQueue<Task>();
        documents = new ArrayList<ArrayList<Document>>(capacity);
    }

    @Override
    public void addTaskToQueue(Task task) {
        taskQueue.add(task);
    }

    @Override
    public TaskResult processTask(Task task) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Task getNextTaskFromQueue() {
        return taskQueue.remove();
    }

    @Override
    public void run() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
