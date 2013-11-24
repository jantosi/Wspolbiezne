package im.komitywa.wspolbiezne.zadanie4.impl;

import im.komitywa.wspolbiezne.zadanie4.Main;
import im.komitywa.wspolbiezne.zadanie4.api.Client;
import im.komitywa.wspolbiezne.zadanie4.api.Server;
import im.komitywa.wspolbiezne.zadanie4.api.Storage;
import im.komitywa.wspolbiezne.zadanie4.api.Task;
import im.komitywa.wspolbiezne.zadanie4.api.TaskResult;

public class CEOClient implements Client {

	private Server server;
	private Storage storage;

	public CEOClient(Storage storage) {
		this.storage = storage;
		// System.out.println("stworzono dyrektora ze skrytka: " + this);
	}

	@Override
	public TaskResult executeTaskOnServer(Task task) {
		if (task instanceof AddDocumentTask) {
			System.out.println("dyrektor " + this + " wykonuje zadanie");
			return task.execute();
		} else {
			System.out.println("dyrektor " + this + " nie potrafi wykonac zadania");
			throw new UnsupportedOperationException("unsupported operation");
		}
	}

	public Server getServer() {
		return server;
	}

	@Override
	public void setServer(Server server) {
		this.server = server;
	}

	@Override
	public void run() {
		System.out.println("dyrektor ze skrytka " + storage + " zaczyna prace");
		for (int i = 0; i < Main.INF; i++) {
			AddDocumentTask addDocumentTask = new AddDocumentTask(storage);
			addDocumentTask.setRunningInstance(server);
			server.addTaskToQueue(addDocumentTask);
			System.out.println("dyrektor ze skrytka " + storage + " dodal zadanie: " + addDocumentTask);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
		}
		System.out.println("dyrektor " + this + " skonczyl prace");
	}

	@Override
	public String toString() {
		return "\"dyrektor " + storage + "\"";
	}
}
