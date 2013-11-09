package im.komitywa.wspolbiezne.zadanie4.api;

public interface Client {
    void setServer(Server server);
    TaskResult executeTaskOnServer(Task task);
}
