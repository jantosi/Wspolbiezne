package im.komitywa.parallel.task2;

/**
 * Created with IntelliJ IDEA.
 * User: Kuba
 * Date: 20.10.13
 * Time: 14:14
 * To change this template use File | Settings | File Templates.
 */
public class Karta implements Comparable<Karta> {
    private int value;

    public Karta(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int compareTo(Karta najmniejszaKarta) {
        return Integer.compare(this.value, najmniejszaKarta.value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
