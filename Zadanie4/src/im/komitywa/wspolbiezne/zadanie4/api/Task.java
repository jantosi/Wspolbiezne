package im.komitywa.wspolbiezne.zadanie4.api;

public interface Task {
	TaskResult execute();

	Server getRunningInstance();

	void setRunningInstance(Server server);
}
