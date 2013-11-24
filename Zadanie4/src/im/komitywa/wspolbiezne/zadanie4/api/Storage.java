package im.komitywa.wspolbiezne.zadanie4.api;

import im.komitywa.wspolbiezne.zadanie4.impl.Document;

public interface Storage {

	boolean isEmpty();

	boolean remove(Document document);

	boolean add(Document document);

	int getStorageNumber();

}
