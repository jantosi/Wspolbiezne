package im.komitywa.parallel.task2;

import java.util.ArrayList;
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

    public KolekcjaKart() {
        karty = new ArrayList<Karta>();
    }

    public synchronized void wezKarte(Karta karta){
        karty.add(karta);
    }

    public synchronized Karta oddajKarte(Karta karta){
        karty.remove(karta);
        return karta;
    }

    public synchronized Karta znajdzSkrajna(boolean szukajNajmniejszej){
        Karta szukanaKarta = karty.get(0);
        for(Karta iterKarta: karty){
            if (szukajNajmniejszej) {
                if (iterKarta.compareTo(szukanaKarta) < 0) {
                    szukanaKarta = iterKarta;
                }
            }
            else
            {
                if (iterKarta.compareTo(szukanaKarta) > 0) {
                    szukanaKarta = iterKarta;
                }
            }
        }

        return szukanaKarta;
    }

    public synchronized Karta znajdzNajmniejsza(){
        return znajdzSkrajna(true);
    }

    public synchronized Karta znajdzNajwieksza(){
        return znajdzSkrajna(false);
    }

}
