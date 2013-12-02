package im.komitywa.wspolbiezne.zadanie4.impl;

import im.komitywa.wspolbiezne.zadanie4.Main;
import im.komitywa.wspolbiezne.zadanie4.api.Client;
import im.komitywa.wspolbiezne.zadanie4.api.Storage;
import im.komitywa.wspolbiezne.zadanie4.api.Task;

public class CEOClient implements Client {

	private Storage storage;
	private int liczbaDodanychZadan = 0;

	public CEOClient(Storage storage) {
		this.storage = storage;
		// System.out.println("stworzono dyrektora ze skrytka: " + this);
	}

	@Override
	public boolean executeTaskOnServer(Task task) {
		if (task instanceof AddDocumentTask) {
			System.out.println("dyrektor " + this + " wykonuje zadanie");
			return task.execute();
		} else {
			System.out.println("dyrektor " + this + " nie potrafi wykonac zadania");
			throw new UnsupportedOperationException("unsupported operation");
		}
	}

	@Override
	public void run() {
		System.out.println("dyrektor ze skrytka " + storage + " zaczyna prace");
		for (int i = 0; i < Main.INF; i++) {
			storage.addTask(new AddDocumentTask());
			System.out.println("dyrektor ze skrytka " + storage + " dodal zadanie: ");
			liczbaDodanychZadan++;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
		System.out.println("dyrektor " + this + " skonczyl prace - liczba dodanych zadan: " + liczbaDodanychZadan);
	}

	@Override
	public String toString() {
		return "\"dyrektor " + storage + "\"";
	}
}
