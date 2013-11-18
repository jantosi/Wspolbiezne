package im.komitywa.wspolbiezne.zadanie4.impl;

import im.komitywa.wspolbiezne.zadanie4.api.Client;
import im.komitywa.wspolbiezne.zadanie4.api.Server;
import im.komitywa.wspolbiezne.zadanie4.api.Task;
import im.komitywa.wspolbiezne.zadanie4.api.TaskResult;

public class OfficeExecutiveClient implements Client{
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
		System.out.println("sekretarka zaczyna prace");
		for (int i = 0; i < -1; i++) {
			getServer().addTaskToQueue(new RemoveDocumentTask());
			System.out.println("sekretarka usuwa komunikat");
		}
	}
}
