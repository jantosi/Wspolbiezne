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
	private int liczbaWykonanychZadan = 0;
	private int liczbaPrzyjetychZadanDodawania = 0;
	private int liczbaPrzyjetychZadanUsuwania = 0;
	private int liczbaWykonanychZadanDodawania = 0;
	private int liczbaWykonanychZadanUsuwania = 0;

	public DocumentStorageServer() {
		taskQueue = new ConcurrentLinkedQueue<Task>();
		documents = new ArrayList<DocumentStorage>();
		for (int i = 0; i < Main.liczbaDyrektorow; i++) {
			documents.add(new DocumentStorage(i));
		}
		// System.out.println("stworzono serwer");
	}

	@Override
	public void addTaskToQueue(Task task) {
		synchronized (taskQueue) {
			if (task instanceof AddDocumentTask) {
				liczbaPrzyjetychZadanDodawania++;
			}
			if (task instanceof RemoveDocumentTask) {
				liczbaPrzyjetychZadanUsuwania++;
			}
			taskQueue.add(task);
			System.out.println("serwer dodal zadanie");
			System.out.println("liczba pozostalych zadan: " + taskQueue.size() + " (" + taskQueue + ")");
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
			Task result;
			if (taskQueue.isEmpty()) {
				System.out.println("serwer nie ma zadan");
				result = null;
			} else {
				Task task = taskQueue.poll();
				System.out.println("serwer usunal zadanie");
				result = task;
			}
			System.out.println("liczba pozostalych zadan: " + taskQueue.size() + " (" + taskQueue + ")");
			return result;
		}
	}

	@Override
	public void run() {
		System.out.println("serwer zaczyna prace");
		for (int i = 0; i < Main.INF * Main.liczbaDyrektorow * 2; /* i++ */) {
			Task taskFromQueue = getNextTaskFromQueue();
			if (taskFromQueue != null) {
				TaskResult taskResult = processTask(taskFromQueue);
				if (taskResult == null) {
					System.out.println("serwer nie wykonal zadania: " + taskFromQueue);
					addTaskToQueue(taskFromQueue);
				} else {
					System.out.println("serwer wykonal zadanie: " + taskFromQueue);
					liczbaWykonanychZadan++;
					if (taskFromQueue instanceof AddDocumentTask) {
						liczbaWykonanychZadanDodawania++;
					}
					if (taskFromQueue instanceof RemoveDocumentTask) {
						liczbaWykonanychZadanUsuwania++;
					}
					i++;
				}
				// System.out.println("liczba pozostalych zadan: " + taskQueue.size() + " (" +
				// taskQueue + ")");
				// System.out.println("liczba przyjetych zadan dodawania: " +
				// liczbaPrzyjetychZadanDodawania);
				// System.out.println("liczba przyjetych zadan usuwania: " +
				// liczbaPrzyjetychZadanUsuwania);
				// System.out.println("liczba wykonanych zadan dodawania: " +
				// liczbaWykonanychZadanDodawania);
				// System.out.println("liczba wykonanych zadan usuwania: " +
				// liczbaWykonanychZadanUsuwania);
				// System.out.println("skrytki: " + documents);
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
			}
		}
		synchronized (taskQueue) {
			System.out.println("serwer skonczyl prace");
			System.out.println("liczba pozostalych zadan: " + taskQueue.size() + " (" + taskQueue + ")");
			System.out.println("liczba przyjetych zadan dodawania: " + liczbaPrzyjetychZadanDodawania);
			System.out.println("liczba przyjetych zadan usuwania: " + liczbaPrzyjetychZadanUsuwania);
			System.out.println("liczba wykonanych zadan dodawania: " + liczbaWykonanychZadanDodawania);
			System.out.println("liczba wykonanych zadan usuwania: " + liczbaWykonanychZadanUsuwania);
			System.out.println("skrytki: " + documents);
		}
	}

	boolean addDocument(Storage storage, Document document) {
		synchronized (documents) {
			if (storage.isEmpty()) {
				boolean result = storage.add(document);
				System.out.println("serwer dodal dokument - skrytka " + storage + " byla pusta");
				return result;
			} else {
				System.out.println("serwer nie dodal dokumentu - skrytka " + storage + " jest pelna");
				return false;
			}
		}
	}

	boolean removeDocument(Storage storage, Document document) {
		synchronized (documents) {
			if (!storage.isEmpty()) {
				boolean result = storage.remove(document);
				System.out.println("serwer usunal dokument - skrytka " + storage + " byla pelna");
				return result;
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
