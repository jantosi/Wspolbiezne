package im.komitywa.wspolbiezne.zadanie4;

import im.komitywa.wspolbiezne.zadanie4.api.Client;
import im.komitywa.wspolbiezne.zadanie4.api.Server;
import im.komitywa.wspolbiezne.zadanie4.impl.CEOClient;
import im.komitywa.wspolbiezne.zadanie4.impl.DocumentStorageServer;
import im.komitywa.wspolbiezne.zadanie4.impl.OfficeExecutiveClient;

public class Main {

    public static void main(String[] args) {
        Server skrytki = new DocumentStorageServer(1);

        Client sekretarka = new OfficeExecutiveClient();
        sekretarka.setServer(skrytki);
        Client dyrektorPierwszy = new CEOClient();
        dyrektorPierwszy.setServer(skrytki);
        Client dyrektorDrugi = new CEOClient();
        dyrektorDrugi.setServer(skrytki);


    }
}
