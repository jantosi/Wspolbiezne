package im.komitywa.wspolbiezne.zadanie5;

import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: rafal
 * Date: 27.11.13
 * Time: 16:00
 * To change this template use File | Settings | File Templates.
 */
public class BorrowerClient implements Client {
    private Integer moneyNeeds;
    private Integer borrowedAmount =0;
    private Server server;

    @Override
    public void run() {
        while(moneyNeeds>borrowedAmount){
            try {
                ChangeLoanStateTask borrowMoneyTask = new ChangeLoanStateTask();
                    borrowMoneyTask.setServer(server);
                    borrowMoneyTask.setClient(this);
                    borrowMoneyTask.setLoanChange(1);

                Promise promise = server.executeTask(borrowMoneyTask);
                synchronized (promise){
                    promise.wait();
                }

                if(promise.getTaskResult()!=null){
                    System.out.println("OK:not null");
                }
                else
                {
                    throw new RuntimeException("Everything is wrong.");
                }

                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
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

    @Override
    public void addMoney(Integer amount) {
        borrowedAmount+=amount;
    }
}
