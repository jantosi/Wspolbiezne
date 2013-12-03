package im.komitywa.wspolbiezne.zadanie5;

/**
 * Created with IntelliJ IDEA.
 * User: Kuba
 * Date: 03.12.13
 * Time: 21:14
 * To change this template use File | Settings | File Templates.
 */
public class Promise {
    private BooleanTaskResult taskResult = null;

    public BooleanTaskResult getTaskResult() {
        return taskResult;
    }

    public void setTaskResult(BooleanTaskResult taskResult) {
        this.taskResult = taskResult;
        System.out.println("Promise wakes up all.");
    }
}
