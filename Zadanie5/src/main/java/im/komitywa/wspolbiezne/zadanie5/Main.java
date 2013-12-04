package im.komitywa.wspolbiezne.zadanie5;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;

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
    public static final int MAX_NEEDS = 10;
    public static final int numberOfClients = 15;
    static List<Client> clients;
    static Server server;

    public static void main(String[] args) throws InterruptedException {


        server = new BankerServer(120);
        server.setNumberOfRunningClients(numberOfClients);
        clients = new ArrayList<Client>();
        for (int i = 0; i < numberOfClients; i++) {
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

    public static boolean safetyProcedure() {
        Integer work = server.getAvailable();
        boolean[] finish = new boolean[numberOfClients];
        int unfinish;
        do {
            unfinish = unfinished(work, finish);
        } while(unfinish != -1);
        int unsafe = ArrayUtils.indexOf(finish, false);
        boolean res = (unsafe == ArrayUtils.INDEX_NOT_FOUND);
        System.out.println("Will the system stay in safe state? "+res);
        return res;
    }

    private static int unfinished(Integer work, boolean[] finish) {
        for (int i = 0; i < numberOfClients; i++) {
            if (!finish[i] && clients.get(i).getNeed() <= work) {
                work += clients.get(i).getAllocation();
                finish[i] = true;
                return i;
            }
        }
        return -1;
    }

}
