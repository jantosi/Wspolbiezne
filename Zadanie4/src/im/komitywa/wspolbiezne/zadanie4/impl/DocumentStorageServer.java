package im.komitywa.wspolbiezne.zadanie4.impl;

import im.komitywa.wspolbiezne.zadanie4.Main;
import im.komitywa.wspolbiezne.zadanie4.api.Server;
import im.komitywa.wspolbiezne.zadanie4.api.Task;
import im.komitywa.wspolbiezne.zadanie4.api.TaskResult;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class DocumentStorageServer implements Server {
    private Queue<Task> taskQueue;
    private ArrayList<ArrayList<Document>> documents;

    public DocumentStorageServer(int numberOfStorages) {
        taskQueue = new ConcurrentLinkedQueue<Task>();
        documents = new ArrayList<ArrayList<Document>>();
        for (int i = 0; i < numberOfStorages; i++) {
			documents.add(new ArrayList<Document>());
		}
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
			if (taskQueue.isEmpty()) {
				return null;
			} else {
				Task remove = taskQueue.remove();
				System.out.println("usunieto zadanie");
				return remove;
			}
		}
	}

	@Override
	public void run() {
		System.out.println("serwer zaczyna prace");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new UnsupportedOperationException("not implemented yet");
	}

	public boolean addDocument(int clientNumber, Document document) {
		synchronized (documents) {
			if (documents.get(clientNumber).isEmpty()) {
				documents.get(clientNumber).add(document);
				return true;
			} else {
				return false;
			}
		}
	}

	public boolean removeDocument(int clientNumber, Document document) {
		synchronized (documents) {
			if (!documents.get(clientNumber).isEmpty()) {
				documents.get(clientNumber).remove(document);
				return true;
			} else {
				return false;
			}
		}
	}
}
