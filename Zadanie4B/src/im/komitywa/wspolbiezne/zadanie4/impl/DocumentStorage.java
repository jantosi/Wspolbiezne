package im.komitywa.wspolbiezne.zadanie4.impl;

import im.komitywa.wspolbiezne.zadanie4.Main;
import im.komitywa.wspolbiezne.zadanie4.api.Storage;
import im.komitywa.wspolbiezne.zadanie4.api.Task;

import java.util.ArrayList;

public class DocumentStorage implements Storage {

	private ArrayList<Task> removeTasks;
	private ArrayList<Task> addTasks;

	private ArrayList<Document> documents;

	private int storageNumber;

	public DocumentStorage(int number) {
		storageNumber = number;
		documents = new ArrayList<Document>();
		removeTasks = new ArrayList<Task>();
		addTasks = new ArrayList<Task>();
	}

	@Override
	public boolean add(Document document) {
		synchronized (documents) {
			boolean result;
			if (documents.isEmpty()) {
				result = documents.add(document);
			} else {
				result = false;
			}
			if (result) {
				System.out.println("skrytka dodala dokument");
			} else {
				System.out.println("skrytka nie dodala dokumentu");
			}
			return result;
		}
	}

	@Override
	public int getStorageNumber() {
		return storageNumber;
	}

	@Override
	public boolean isEmpty() {
		synchronized (documents) {
			return documents.isEmpty();
		}
	}

	@Override
	public boolean remove(Document document) {
		synchronized (document) {
			boolean result;
			if (!documents.isEmpty()) {
				result = documents.remove(0) != null;
			} else {
				result = false;
			}
			if (result) {
				System.out.println("skrytka usunela dokument");
			} else {
				System.out.println("skrytka nie usunela dokumentu");
			}
			return result;
		}
	}

	@Override
	public String toString() {
		return "\"skrytka nr " + getStorageNumber() + " (" + documents.size() + ")\"";
	}

	@Override
	public void addTask(Task task) {
		if (task instanceof AddDocumentTask) {
			addTasks.add(task);
		} else if (task instanceof RemoveDocumentTask) {
			removeTasks.add(task);
		} else {
			throw new UnsupportedOperationException("not implemented yet");
		}
	}

	@Override
	public void run() {
		for (int i = 0; i < 2 * Main.INF * Main.liczbaDyrektorow;) {
			synchronized (documents) {
				if (documents.isEmpty()) {
					if (addTasks.isEmpty()) {
					} else {
						addTasks.remove(0);
						documents.add(new Document());
						System.out.println("dodano dokument");
						i++;
					}
				} else if (documents.size() == 1) {
					if (removeTasks.isEmpty()) {
					} else {
						removeTasks.remove(0);
						documents.remove(0);
						System.out.println("usunieto dokument");
						i++;
					}
				}
			}
		}
		System.out.println("skrytka skonczyla prace.");
	}
}
