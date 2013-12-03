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
    }

    @Override
    public synchronized Promise executeTask(Task changeLoanStateTask) {
        Promise promise = new Promise();
        taskQueue.add(new Pair<Task, Promise>(changeLoanStateTask,promise));
        System.out.println("Promise returned.");
        return promise;
    }

    @Override
    public boolean borrowMoney(Client client, Integer loanChange) {
        if(canBorrowAmount(client,loanChange)){
            currentMoney-=loanChange;
            client.addMoney(loanChange);
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        while(true){
        while(!taskQueue.isEmpty()){
           Pair<Task, Promise> taskPair = taskQueue.remove();
           BooleanTaskResult result = taskPair.getKey().execute();
           taskPair.getValue().setTaskResult(result);
           System.out.println("Promise value set.");
       }}
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
