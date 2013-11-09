package im.komitywa.wspolbiezne.zadanie4.impl;

import im.komitywa.wspolbiezne.zadanie4.api.Server;
import im.komitywa.wspolbiezne.zadanie4.api.Task;
import im.komitywa.wspolbiezne.zadanie4.api.TaskResult;

public class RemoveDocumentTask implements Task {
    private Server runningInstance;

    @Override
    public TaskResult execute() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Server getRunningInstance() {
        return runningInstance;
    }

    public void setRunningInstance(Server runningInstance) {
        this.runningInstance = runningInstance;
    }
}
