package im.komitywa.wspolbiezne.zadanie4;

import im.komitywa.wspolbiezne.zadanie4.api.Client;
import im.komitywa.wspolbiezne.zadanie4.api.Server;
import im.komitywa.wspolbiezne.zadanie4.impl.CEOClient;
import im.komitywa.wspolbiezne.zadanie4.impl.DocumentStorageServer;
import im.komitywa.wspolbiezne.zadanie4.impl.OfficeExecutiveClient;

public class Main {

    private static int liczbaDyrektorow = 10;

    public static void main(String[] args) {
        Server skrytki = new DocumentStorageServer(1);

        Client sekretarka = new OfficeExecutiveClient();
        sekretarka.setServer(skrytki);

        Client dyrektorzy[] = new CEOClient[liczbaDyrektorow];
        for (int i = 0; i < liczbaDyrektorow; i++) {
            dyrektorzy[i] = new CEOClient();
            dyrektorzy[i].setServer(skrytki);
        }

        new Thread(skrytki, "skrytki").start();
        new Thread(sekretarka, "sekretarka").start();
        for (int i = 0; i < liczbaDyrektorow; i++) {
            new Thread(dyrektorzy[i], "dyrektor" + i).start();
        }
    }
}
