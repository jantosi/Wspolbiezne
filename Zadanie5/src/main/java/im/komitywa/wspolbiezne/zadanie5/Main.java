package im.komitywa.wspolbiezne.zadanie5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Kuba
 * Date: 03.12.13
 * Time: 22:47
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static final int MAX_NEEDS = 100;
    static List<Client> clients;
    static Server server;

    public static void main(String[] args) throws InterruptedException {


        server = new BankerServer(50000);
        clients = new ArrayList<Client>();
        for (int i = 0; i < 15; i++) {
            clients.add(new BorrowerClient(){{
                setMoneyNeeds(new Random().nextInt(MAX_NEEDS));
                setServer(server);
            }});
        }

        Thread serverThread = new Thread(server);
        List<Thread> clientThreads = new ArrayList<Thread>(clients.size());
        for (Client client : clients) {
            client.setServer(server);
            clientThreads.add(new Thread(client));
        }

        serverThread.start();

        for (Thread clientThread : clientThreads) {
            clientThread.start();
        }





    }
}
