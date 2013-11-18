package im.komitywa.wspolbiezne.zadanie4.impl;

import im.komitywa.wspolbiezne.zadanie4.api.Server;
import im.komitywa.wspolbiezne.zadanie4.api.Task;
import im.komitywa.wspolbiezne.zadanie4.api.TaskResult;

public class AddDocumentTask implements Task {
    private Server runningInstance;
    private int CEOClientNumber;

	@Override
    public TaskResult execute() {
		return ((DocumentStorageServer)getRunningInstance()).addDocument(getCEOClientNumber());
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
	public int getCEOClientNumber() {
		return CEOClientNumber;
	}

	@Override
	public void setCEOClientNumber(int CEOClientNumber) {
		this.CEOClientNumber = CEOClientNumber;
	}
}
