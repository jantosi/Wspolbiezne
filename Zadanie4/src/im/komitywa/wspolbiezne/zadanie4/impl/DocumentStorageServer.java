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
		try {
			System.out.println("serwer zaczyna prace");
			for (int i = 0; i < Main.INF; i++) {
				synchronized (documents) {
					if (!taskQueue.isEmpty()) {
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

	public TaskResult addDocument(int CEOClientNumber) {
		synchronized (documents) {
			if (documents.get(CEOClientNumber).isEmpty()) {
				documents.get(CEOClientNumber).add(new Document());
				System.out.println("dodano dokument do skrytki nr " + CEOClientNumber);
				return new AddDocumentTaskResult();
			} else {
				System.out.println("skrytka nr " + CEOClientNumber + " jest pelna");
				return new AddDocumentTaskResult();
			}
		}
	}

	public TaskResult removeDocument(int CEOClientNumber) {
		synchronized (documents) {
			if (documents.get(CEOClientNumber).isEmpty()) {
				System.out.println("nie ma dokumentow w skrytce nr " + CEOClientNumber);
				return new AddDocumentTaskResult();
			} else {
				documents.get(CEOClientNumber).remove(0);
				System.out.println("ususnieto dokument ze skrytki nr " + CEOClientNumber);
				return new AddDocumentTaskResult();
			}
		}
	}
}
