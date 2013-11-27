package im.komitywa.wspolbiezne.zadanie5;

/**
 * Created with IntelliJ IDEA.
 * User: rafal
 * Date: 27.11.13
 * Time: 16:00
 * To change this template use File | Settings | File Templates.
 */
public class BorrowerClient implements Client {
    private Integer moneyNeeds;
    private Server server;

    @Override
    public void run() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Integer getMoneyNeeds() {
        return moneyNeeds;
    }

    public void setMoneyNeeds(Integer moneyNeeds) {
        this.moneyNeeds = moneyNeeds;
    }

    @Override
    public Server getServer() {
        return server;
    }

    @Override
    public void setServer(Server server) {
        this.server = server;
    }
}
