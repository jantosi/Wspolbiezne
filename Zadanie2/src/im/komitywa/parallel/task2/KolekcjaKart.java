package im.komitywa.parallel.task2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Kuba
 * Date: 20.10.13
 * Time: 14:15
 * To change this template use File | Settings | File Templates.
 */
public class KolekcjaKart {
    private List<Karta> karty;
    private int docelowyRozmiarKolekcji;

    public KolekcjaKart() {
        karty = new ArrayList<Karta>();
    }

    public synchronized void wezKarte(Karta karta){
        karty.add(karta);
        Collections.sort(karty);
    }

    public synchronized Karta oddajKarte(Karta karta){
        karty.remove(karta);
        return karta;
    }

    public synchronized Karta znajdzSkrajna(boolean szukajNajmniejszej){
        if(szukajNajmniejszej) {
            return karty.get(karty.size() - 1);
        } else {
            return karty.get(0);
        }
    }

    @Override
    public synchronized String toString() {
        return "KolekcjaKart{" +
                "karty=" + karty +
                '}';
    }

    public int getDocelowyRozmiarKolekcji() {
        return docelowyRozmiarKolekcji;
    }

    public void setDocelowyRozmiarKolekcji(int docelowyRozmiarKolekcji) {
        this.docelowyRozmiarKolekcji = docelowyRozmiarKolekcji;
    }

    public int getRozmiarKolekcji(){
        return karty.size();
    }
}
