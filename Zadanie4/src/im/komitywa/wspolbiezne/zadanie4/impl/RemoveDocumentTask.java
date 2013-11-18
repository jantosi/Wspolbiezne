package im.komitywa.wspolbiezne.zadanie4.impl;

import java.util.ArrayList;

import im.komitywa.wspolbiezne.zadanie4.api.Server;
import im.komitywa.wspolbiezne.zadanie4.api.Task;
import im.komitywa.wspolbiezne.zadanie4.api.TaskResult;

public class RemoveDocumentTask implements Task {
    private Server runningInstance;

    @Override
    public TaskResult execute() {
    	throw new UnsupportedOperationException("not implemented yet");
    }

	@Override
    public Server getRunningInstance() {
        return runningInstance;
    }

	@Override
    public void setRunningInstance(Server runningInstance) {
        this.runningInstance = runningInstance;
    }

	@Override
	public String toString() {
		return "Remove";
	}
}
