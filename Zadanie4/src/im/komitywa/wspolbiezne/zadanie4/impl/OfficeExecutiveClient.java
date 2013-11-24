package im.komitywa.wspolbiezne.zadanie4.impl;

import im.komitywa.wspolbiezne.zadanie4.Main;
import im.komitywa.wspolbiezne.zadanie4.api.Client;
import im.komitywa.wspolbiezne.zadanie4.api.Server;
import im.komitywa.wspolbiezne.zadanie4.api.Task;
import im.komitywa.wspolbiezne.zadanie4.api.TaskResult;

import java.util.ArrayList;

public class OfficeExecutiveClient implements Client {
	private Server server;
	private ArrayList<DocumentStorage> storages;

	public OfficeExecutiveClient(ArrayList<DocumentStorage> storages) {
		this.storages = storages;
		// System.out.println("stworzono sekretarke: " + this);
	}

	@Override
	public TaskResult executeTaskOnServer(Task task) {
		if (task instanceof RemoveDocumentTask) {
			System.out.println("sekretarka wykonuje zadanie: " + task);
			return task.execute();
		} else {
			System.out.println("sekretarka nie potrafi wykonac zadania: " + task);
			throw new UnsupportedOperationException("not implemented yet");
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
		System.out.println("sekretarka zaczyna prace");
		for (int i = 0; i < Main.INF; i++) {
			for (int j = 0; j < Main.liczbaDyrektorow; j++) {
				RemoveDocumentTask removeDocumentTask = new RemoveDocumentTask(storages.get(j));
				removeDocumentTask.setRunningInstance(server);
				server.addTaskToQueue(removeDocumentTask);
				System.out.println("sekretarka dodala zadanie: " + removeDocumentTask);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
				}
			}
		}
		System.out.println("sekretarka " + this + " skonczyla prace");
	}

	@Override
	public String toString() {
		return "\"sekretarka\"";
	}
}
