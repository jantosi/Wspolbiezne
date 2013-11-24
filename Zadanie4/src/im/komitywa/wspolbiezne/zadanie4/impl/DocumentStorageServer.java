package im.komitywa.wspolbiezne.zadanie4.impl;

import im.komitywa.wspolbiezne.zadanie4.Main;
import im.komitywa.wspolbiezne.zadanie4.api.Server;
import im.komitywa.wspolbiezne.zadanie4.api.Storage;
import im.komitywa.wspolbiezne.zadanie4.api.Task;
import im.komitywa.wspolbiezne.zadanie4.api.TaskResult;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class DocumentStorageServer implements Server {
	private Queue<Task> taskQueue;
	private ArrayList<DocumentStorage> documents;

	public DocumentStorageServer(int numberOfStorages) {
		taskQueue = new ConcurrentLinkedQueue<Task>();
		documents = new ArrayList<DocumentStorage>();
		for (int i = 0; i < numberOfStorages; i++) {
			documents.add(new DocumentStorage(i));
		}
		// System.out.println("stworzono serwer");
	}

	@Override
	public void addTaskToQueue(Task task) {
		synchronized (taskQueue) {
			taskQueue.add(task);
			System.out.println("serwer dodal zadanie");
		}
	}

	@Override
	public TaskResult processTask(Task task) {
		System.out.println("serwer wykonuje zadanie...");
		TaskResult result = task.execute();
		return result;
	}

	@Override
	public Task getNextTaskFromQueue() {
		synchronized (taskQueue) {
			if (taskQueue.isEmpty()) {
				System.out.println("serwer nie ma zadan");
				return null;
			} else {
				Task task = taskQueue.poll();
				System.out.println("serwer usunal zadanie");
				return task;
			}
		}
	}

	@Override
	public void run() {
		System.out.println("serwer zaczyna prace");
		for (int i = 0; i < Main.INF * Main.liczbaDyrektorow * 2;) {
			Task taskFromQueue = getNextTaskFromQueue();
			if (taskFromQueue != null) {
				TaskResult taskResult = processTask(taskFromQueue);
				if (taskResult == null) {
					System.out.println("serwer nie wykonal zadania: " + taskFromQueue);
					addTaskToQueue(taskFromQueue);
				} else {
					System.out.println("serwer wykonal zadanie: " + taskFromQueue);
					i++;
				}
				System.out.println("liczba pozostalych zadan: " + taskQueue.size() + " (" + taskQueue + ")");
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
		synchronized (taskQueue) {
			System.out.println("serwer skonczyl prace - pozostale zadania: " + taskQueue);
		}
	}

	boolean addDocument(Storage storage, Document document) {
		synchronized (documents) {
			if (storage.isEmpty()) {
				storage.add(document);
				System.out.println("serwer dodal dokument - skrytka " + storage + " byla pusta");
				return true;
			} else {
				System.out.println("serwer nie dodal dokumentu - skrytka " + storage + " jest pelna");
				return false;
			}
		}
	}

	boolean removeDocument(Storage storage, Document document) {
		synchronized (documents) {
			if (!storage.isEmpty()) {
				storage.remove(document);
				System.out.println("serwer usunal dokument - skrytka " + storage + " byla pelna");
				return true;
			} else {
				System.out.println("serwer nie usunal dokumentu - skrytka " + storage + " jest pusta");
				return false;
			}
		}
	}

	public DocumentStorage getSkrytka(int i) {
		return documents.get(i);
	}

	public ArrayList<DocumentStorage> getSkrytki() {
		return documents;
	}
}
