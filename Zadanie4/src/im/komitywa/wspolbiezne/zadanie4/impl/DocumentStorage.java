package im.komitywa.wspolbiezne.zadanie4.impl;

import im.komitywa.wspolbiezne.zadanie4.api.Storage;

import java.util.ArrayList;

class DocumentStorage implements Storage {

	private ArrayList<Document> documents;

	private int storageNumber;

	DocumentStorage(int number) {
		storageNumber = number;
		documents = new ArrayList<Document>();
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

}
