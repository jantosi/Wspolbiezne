package im.komitywa.wspolbiezne.zadanie4.impl;

import im.komitywa.wspolbiezne.zadanie4.api.Storage;

import java.util.ArrayList;

class DocumentStorage extends ArrayList<Document> implements Storage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2498893325349636489L;

	private int storageNumber;

	@Override
	public int getStorageNumber() {
		return storageNumber;
	}

	DocumentStorage(int number) {
		storageNumber = number;
	}

	@Override
	public boolean remove(Document document) {
		return super.remove(document);
	}

	@Override
	public String toString() {
		return "\"skrytka nr " + getStorageNumber() + "\"";
	}

}
