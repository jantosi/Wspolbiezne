package im.komitywa.wspolbiezne.zadanie4.impl;

import im.komitywa.wspolbiezne.zadanie4.Main;
import im.komitywa.wspolbiezne.zadanie4.api.Client;
import im.komitywa.wspolbiezne.zadanie4.api.Storage;
import im.komitywa.wspolbiezne.zadanie4.api.Task;

public class OfficeExecutiveClient implements Client {
	private Storage[] storages;
	private int liczbaDodanychZadan = 0;

	public OfficeExecutiveClient(Storage[] skrytki) {
		storages = skrytki;
		// System.out.println("stworzono sekretarke: " + this);
	}

	@Override
	public boolean executeTaskOnServer(Task task) {
		if (task instanceof RemoveDocumentTask) {
			System.out.println("sekretarka wykonuje zadanie: " + task);
			return task.execute();
		} else {
			System.out.println("sekretarka nie potrafi wykonac zadania: " + task);
			throw new UnsupportedOperationException("not implemented yet");
		}
	}

	@Override
	public void run() {
		System.out.println("sekretarka zaczyna prace");
		for (int i = 0; i < Main.INF; i++) {
			for (int j = 0; j < Main.liczbaDyrektorow; j++) {
				storages[j].addTask(new RemoveDocumentTask());
				System.out.println("sekretarka dodala zadanie: ");
				liczbaDodanychZadan++;
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
				}
			}
		}
		System.out.println("sekretarka " + this + " skonczyla prace - liczba dodanych zadan: " + liczbaDodanychZadan);
	}

	@Override
	public String toString() {
		return "\"sekretarka\"";
	}
}
