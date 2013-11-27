package im.komitywa.wspolbiezne.zadanie5;

/**
 * Created with IntelliJ IDEA.
 * User: rafal
 * Date: 27.11.13
 * Time: 16:00
 * To change this template use File | Settings | File Templates.
 */
public class ChangeLoanStateTask implements Task {
    private Server server;
    private Client client;

    private Integer loanChange;

    @Override
    public Server getServer() {
        return server;
    }

    @Override
    public void setServer(Server server) {
        this.server = server;
    }

    @Override
    public Client getClient() {
        return client;
    }

    @Override
    public void setClient(Client client) {
        this.client = client;
    }

    public Integer getLoanChange() {
        return loanChange;
    }

    public void setLoanChange(Integer loanChange) {
        this.loanChange = loanChange;
    }

    @Override
    public void execute() {
        server.executeTask(this);
    }
}
