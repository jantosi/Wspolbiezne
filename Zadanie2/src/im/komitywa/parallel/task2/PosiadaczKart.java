package im.komitywa.parallel.task2;

import java.util.concurrent.Semaphore;

/**
 * Created with IntelliJ IDEA.
 * User: Kuba
 * Date: 20.10.13
 * Time: 14:13
 * To change this template use File | Settings | File Templates.
 */
public class PosiadaczKart implements Runnable{
    private String nazwa;
    private boolean czySzukaNajmniejszych;

    private KolekcjaKart k;
    private PosiadaczKart partnerDoWymiany;
    private Semaphore semaphoreWrite = new Semaphore(1);
    private Semaphore semaphoreRead = new Semaphore(0);
    private Karta skrajnaKarta = new Karta(0);

    public Karta podajSkrajnaKarteZKolekcji(boolean czyNajmniejsza){
        return k.znajdzSkrajna(czyNajmniejsza);
    }

/**
 * Uruchom wątek.
 */
@Override
public void run() {
    System.out.println(this+": startuje.");

    while(!"hell".equals("frozen over"))
    {
        System.out.println(nazwa+": szuka "+(czySzukaNajmniejszych() ? "najmniejszych" : "największych"));

        Karta skrajnaMoja = podajSkrajnaKarteZKolekcji(czySzukaNajmniejszych());
        System.out.println(nazwa+": Skrajna moja: "+skrajnaMoja);

        try {
            partnerDoWymiany.semaphoreWrite.acquire();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        partnerDoWymiany.skrajnaKarta = skrajnaMoja;
        partnerDoWymiany.semaphoreRead.release();

        try {
            semaphoreRead.acquire();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Karta skrajnaKartaPartnera = skrajnaKarta;
        System.out.println(nazwa+": Skrajna karta partnera: "+skrajnaKartaPartnera);
        semaphoreWrite.release();

        int compare = skrajnaKartaPartnera.compareTo(skrajnaMoja);
        boolean war = czySzukaNajmniejszych() ? compare > 0 : compare < 0;
        if (war) {
            System.out.println(nazwa+": break");
            break;
        }

        System.out.println(this+": proponuje kartę do wymiany: "+skrajnaMoja);

        k.oddajKarte(skrajnaMoja);
        k.wezKarte(skrajnaKartaPartnera);
    }

    System.out.println(nazwa+": bylo "+k.getDocelowyRozmiarKolekcji()+"; jest "+k.getRozmiarKolekcji());

    if (k.getDocelowyRozmiarKolekcji() != k.getRozmiarKolekcji()) {
        throw new RuntimeException("nie zgadza sie");
    }

    System.out.println(this+": konczy.");
}


//    public KolekcjaKart getK() {
//        return k;
//    }

    public void setK(KolekcjaKart k) {
        this.k = k;
    }

//    public PosiadaczKart getPartnerDoWymiany() {
//        return partnerDoWymiany;
//    }

    public void setPartnerDoWymiany(PosiadaczKart partnerDoWymiany) {
        this.partnerDoWymiany = partnerDoWymiany;
    }

//    public String getNazwa() {
//        return nazwa;
//    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public boolean czySzukaNajmniejszych() {
        return czySzukaNajmniejszych;
    }

    public void szukaNajmniejszych(boolean czySzukaNajmniejszych) {
        this.czySzukaNajmniejszych = czySzukaNajmniejszych;
    }

    @Override
    public String toString() {
        return "PosiadaczKart{" +
                "nazwa='" + nazwa + '\'' +
                ", k=" + k +
                '}';
    }

//    public void setSemaphore(Semaphore semaphore) {
//        this.semaphore = semaphore;
//    }

//    public Semaphore getSemaphore() {
//        return semaphore;
//    }
}
