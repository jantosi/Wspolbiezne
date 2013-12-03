package im.komitywa.wspolbiezne.zadanie5;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: rafal
 * Date: 27.11.13
 * Time: 16:06
 * To change this template use File | Settings | File Templates.
 */
public class MainTest {
    public static final int MAX_NEEDS = 100;
    List<Client> clients;
    Server server;

    @Before
    public void setUp() throws Exception {
        server = new BankerServer(50000);
        clients = new ArrayList<Client>();
        for (int i = 0; i < 15; i++) {
            clients.add(new BorrowerClient(){{
                setMoneyNeeds(new Random().nextInt(MAX_NEEDS));
                setServer(server);
            }});
        }
    }

    @Test
    public void testMain() throws Exception {
        Thread serverThread = new Thread(server);
        List<Thread> clientThreads = new ArrayList<Thread>(clients.size());
        for (Client client : clients) {
            client.setServer(server);
            clientThreads.add(new Thread(client));
        }

        serverThread.run();
        for (Thread clientThread : clientThreads) {
            clientThread.run();
        }

    }
}
