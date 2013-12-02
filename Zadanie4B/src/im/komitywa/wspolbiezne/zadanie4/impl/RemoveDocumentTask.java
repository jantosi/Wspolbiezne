package im.komitywa.wspolbiezne.zadanie4.impl;

import im.komitywa.wspolbiezne.zadanie4.api.Task;

class RemoveDocumentTask implements Task {
	private DocumentStorage storage;

	RemoveDocumentTask() {
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
		return "\"usuwanie ze skrytki " + storage + "\"";
	}
}
