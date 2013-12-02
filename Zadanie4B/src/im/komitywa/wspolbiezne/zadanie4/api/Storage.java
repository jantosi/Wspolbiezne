package im.komitywa.wspolbiezne.zadanie4.api;

import im.komitywa.wspolbiezne.zadanie4.impl.Document;

public interface Storage extends Runnable {

	boolean isEmpty();

	boolean remove(Document document);

	boolean add(Document document);

	int getStorageNumber();

	void addTask(Task task);
}
