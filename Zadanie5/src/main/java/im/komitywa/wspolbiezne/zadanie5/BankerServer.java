package im.komitywa.wspolbiezne.zadanie5;

import javafx.util.Pair;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created with IntelliJ IDEA.
 * User: rafal
 * Date: 27.11.13
 * Time: 15:59
 * To change this template use File | Settings | File Templates.
 */
public class BankerServer implements Server {

    private final Integer capitalMoney;
    private Integer currentMoney;
    private Queue<Pair<Task,Promise>> taskQueue = new ConcurrentLinkedQueue<Pair<Task, Promise>>();

    public BankerServer(Integer capitalMoney) {
        this.capitalMoney = capitalMoney;
        currentMoney = capitalMoney;
    }

    @Override
    public void executeTask(Task changeLoanStateTask, Promise promise) {
        taskQueue.add(new Pair<Task, Promise>(changeLoanStateTask,promise));
    }

    @Override
    public boolean borrowMoney(Client client, Integer loanChange) {
        if(canBorrowAmount(client,loanChange)){
            System.out.println("Lent money ("+loanChange+") to Client "+client+". Current money: "+currentMoney);
            currentMoney-=loanChange;
            client.addMoney(loanChange);
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        int idleLoopsQuota = 0;
        while (idleLoopsQuota<50) {
            if (!taskQueue.isEmpty()) {
                idleLoopsQuota=0;
                Pair<Task, Promise> taskPair = taskQueue.remove();
                BooleanTaskResult result = taskPair.getKey().execute();
                Promise promise= taskPair.getValue();
                synchronized (promise){
                    promise.setTaskResult(result);
                    promise.notifyAll();
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            idleLoopsQuota++;
            System.out.println("Idle loops: " + idleLoopsQuota);
        }
    }

    public Integer getCurrentMoney() {
        return currentMoney;
    }

    public void setCurrentMoney(Integer currentMoney) {
        this.currentMoney = currentMoney;
    }

    private boolean canBorrowAmount(Client borrower, Integer amount){
        return true; //TODO: Logika pożyczania!
    }
}
