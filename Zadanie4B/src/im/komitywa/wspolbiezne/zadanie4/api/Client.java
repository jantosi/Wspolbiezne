package im.komitywa.wspolbiezne.zadanie4.api;

public interface Client extends Runnable {
	boolean executeTaskOnServer(Task task);
}
