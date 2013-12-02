package im.komitywa.wspolbiezne.zadanie4.impl;

import im.komitywa.wspolbiezne.zadanie4.api.Storage;
import im.komitywa.wspolbiezne.zadanie4.api.Task;

class AddDocumentTask implements Task {
	private Storage storage;

	AddDocumentTask() {
		// this.storage = storage;
		// System.out.println("stworzono zadanie: " + this);
	}

	@Override
	public boolean execute() {
		// System.out.println("zadanie " + this + " jest wykonywane");
		throw new UnsupportedOperationException("not implemented yet");
	}

	@Override
	public String toString() {
		return "\"dodawanie do skrytki " + storage + "\"";
	}
}
