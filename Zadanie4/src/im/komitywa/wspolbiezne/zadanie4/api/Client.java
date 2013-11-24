package im.komitywa.wspolbiezne.zadanie4.api;

public interface Client extends Runnable {
	void setServer(Server server);

	TaskResult executeTaskOnServer(Task task);
}
