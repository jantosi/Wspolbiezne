package im.komitywa.wspolbiezne.zadanie4;

import im.komitywa.wspolbiezne.zadanie4.api.Client;
import im.komitywa.wspolbiezne.zadanie4.api.Storage;
import im.komitywa.wspolbiezne.zadanie4.impl.CEOClient;
import im.komitywa.wspolbiezne.zadanie4.impl.DocumentStorage;
import im.komitywa.wspolbiezne.zadanie4.impl.OfficeExecutiveClient;

public class Main {

	public static final int INF = 10;

	public static int liczbaDyrektorow = 7;

	public static void main(String[] args) {
		Storage skrytki[] = new Storage[liczbaDyrektorow];

		Client sekretarka = new OfficeExecutiveClient(skrytki);

		Client dyrektorzy[] = new CEOClient[liczbaDyrektorow];
		for (int i = 0; i < liczbaDyrektorow; i++) {
			skrytki[i] = new DocumentStorage(0);
			dyrektorzy[i] = new CEOClient(skrytki[i]);
		}

		for (int i = 0; i < liczbaDyrektorow; i++) {
			new Thread(dyrektorzy[i], "dyrektor " + i).start();
			new Thread(skrytki[i], "skrytka " + i).start();
		}
		new Thread(sekretarka, "sekretarka").start();
	}
}
