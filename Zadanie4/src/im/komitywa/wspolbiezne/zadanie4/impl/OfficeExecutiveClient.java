package im.komitywa.wspolbiezne.zadanie4.impl;

import im.komitywa.wspolbiezne.zadanie4.api.Client;
import im.komitywa.wspolbiezne.zadanie4.api.Server;
import im.komitywa.wspolbiezne.zadanie4.api.Task;
import im.komitywa.wspolbiezne.zadanie4.api.TaskResult;

public class OfficeExecutiveClient implements Client{
    private Server server;

    @Override
    public TaskResult executeTaskOnServer(Task task) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }
}
