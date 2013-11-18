package im.komitywa.wspolbiezne.zadanie4.impl;

import im.komitywa.wspolbiezne.zadanie4.Main;
import im.komitywa.wspolbiezne.zadanie4.api.Client;
import im.komitywa.wspolbiezne.zadanie4.api.Server;
import im.komitywa.wspolbiezne.zadanie4.api.Task;
import im.komitywa.wspolbiezne.zadanie4.api.TaskResult;

public class CEOClient implements Client {

	private Server server;

	@Override
	public TaskResult executeTaskOnServer(Task task) {
		throw new UnsupportedOperationException("not implemented yet");
	}

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

	@Override
	public void run() {
		System.out.println("dyrektor zaczyna prace");
		for (int i = 0; i < Main.INF; i++) {
			AddDocumentTask addDocumentTask = new AddDocumentTask();
			addDocumentTask.setRunningInstance(getServer());
			getServer().addTaskToQueue(addDocumentTask);
			System.out.println("dyrektor dodaje komunikat");
		}
	}
}
