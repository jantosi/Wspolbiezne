package im.komitywa.wspolbiezne.zadanie5;

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

    public BankerServer(Integer capitalMoney) {
        this.capitalMoney = capitalMoney;
    }

    @Override
    public void executeTask(Task changeLoanStateTask) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void run() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Integer getCurrentMoney() {
        return currentMoney;
    }

    public void setCurrentMoney(Integer currentMoney) {
        this.currentMoney = currentMoney;
    }
}
