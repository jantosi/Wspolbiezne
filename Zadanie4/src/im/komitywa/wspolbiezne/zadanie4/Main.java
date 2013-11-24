package im.komitywa.wspolbiezne.zadanie4;

import im.komitywa.wspolbiezne.zadanie4.api.Client;
import im.komitywa.wspolbiezne.zadanie4.api.Server;
import im.komitywa.wspolbiezne.zadanie4.impl.CEOClient;
import im.komitywa.wspolbiezne.zadanie4.impl.DocumentStorageServer;
import im.komitywa.wspolbiezne.zadanie4.impl.OfficeExecutiveClient;

public class Main {

	public static final int INF = 3;

	public static int liczbaDyrektorow = 1;

	public static void main(String[] args) {
		Server skrytki = new DocumentStorageServer(liczbaDyrektorow);

		Client sekretarka = new OfficeExecutiveClient(((DocumentStorageServer) skrytki).getSkrytki());
		sekretarka.setServer(skrytki);

		Client dyrektorzy[] = new CEOClient[liczbaDyrektorow];
		for (int i = 0; i < liczbaDyrektorow; i++) {
			dyrektorzy[i] = new CEOClient(((DocumentStorageServer) skrytki).getSkrytka(i));
			dyrektorzy[i].setServer(skrytki);
		}

		new Thread(sekretarka, "sekretarka").start();
		for (int i = 0; i < liczbaDyrektorow; i++) {
			new Thread(dyrektorzy[i], "dyrektor " + i).start();
		}
		new Thread(skrytki, "skrytki").start();
	}
}
