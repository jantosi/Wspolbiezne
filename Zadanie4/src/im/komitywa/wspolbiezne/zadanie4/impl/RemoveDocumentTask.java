package im.komitywa.wspolbiezne.zadanie4.impl;

import im.komitywa.wspolbiezne.zadanie4.api.Server;
import im.komitywa.wspolbiezne.zadanie4.api.Task;
import im.komitywa.wspolbiezne.zadanie4.api.TaskResult;

class RemoveDocumentTask implements Task {
	private Server runningInstance;
	private DocumentStorage storage;

	RemoveDocumentTask(DocumentStorage storage) {
		this.storage = storage;
		// System.out.println("stworzono zadanie: " + this);
	}

	@Override
	public TaskResult execute() {
		System.out.println("zadanie " + this + " jest wykonywane");
		boolean success = ((DocumentStorageServer) runningInstance).removeDocument(storage, new Document());
		return success ? new RemoveDocumentTaskResult() : null;
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
		return "\"usuwanie ze skrytki " + storage + "\"";
	}
}
