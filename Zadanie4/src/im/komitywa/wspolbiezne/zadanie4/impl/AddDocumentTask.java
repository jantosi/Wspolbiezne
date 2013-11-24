package im.komitywa.wspolbiezne.zadanie4.impl;

import im.komitywa.wspolbiezne.zadanie4.api.Server;
import im.komitywa.wspolbiezne.zadanie4.api.Storage;
import im.komitywa.wspolbiezne.zadanie4.api.Task;
import im.komitywa.wspolbiezne.zadanie4.api.TaskResult;

class AddDocumentTask implements Task {
	private Server runningInstance;
	private Storage storage;

	AddDocumentTask(Storage storage) {
		this.storage = storage;
		// System.out.println("stworzono zadanie: " + this);
	}

	@Override
	public TaskResult execute() {
		System.out.println("zadanie " + this + " jest wykonywane");
		boolean success = ((DocumentStorageServer) runningInstance).addDocument(storage, new Document());
		return success ? new AddDocumentTaskResult() : null;
	}

	@Override
	public Server getRunningInstance() {
		return runningInstance;
	}

	@Override
	public void setRunningInstance(Server runningInstance) {
		this.runningInstance = runningInstance;
	}

	@Override
	public String toString() {
		return "\"dodawanie do skrytki " + storage + "\"";
	}
}
