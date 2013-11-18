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
        synchronized (taskQueue) {
			taskQueue.add(task);
			System.out.println("dodano zadanie");
		}
    }

    @Override
    public TaskResult processTask(Task task) {
        TaskResult result = task.execute();
		System.out.println("wykonano zadanie");
		return result;
    }

    @Override
    public Task getNextTaskFromQueue() {
        synchronized (taskQueue) {
			Task remove = taskQueue.remove();
			System.out.println("usunieto zadanie");
			return remove;
		}
    }

    @Override
    public void run() {
		try {
			System.out.println("serwer zaczyna prace");
			for (int i = 0; i < -1; i++) {
				synchronized (documents) {
					if (!documents.isEmpty()) {
						processTask(getNextTaskFromQueue());
						System.out.println("przetworzono zadanie");
					}
				}
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
