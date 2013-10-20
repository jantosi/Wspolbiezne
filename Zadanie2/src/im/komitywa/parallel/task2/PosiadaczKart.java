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
    private Semaphore semaphore;

    /**
     * Posiadacz otrzymuje kartę i rozpatruje, czy pasuje mu do wymiany.
     *
     * @param karta Karta zaproponowana na wymiane
     * @return true: karta przyjęta. false: karta odrzucona.
     */
    public boolean przyjmijKarte(Karta karta){
        k.wezKarte(karta);
        return true;
    }

    /** Posiadacz proponuje do wymiany jedna ze swoich kart.
     *
     * @param karta Karta do wymiany.
     * @return true: karta przyjeta przez innego gracza. false: karta nieprzyjeta.
     */
    public synchronized boolean zaproponujKarte(Karta karta){

        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

//        while()
//        {
//
//        }

        if(partnerDoWymiany.przyjmijKarte(karta)){
            k.oddajKarte(karta);
            partnerDoWymiany.getSemaphore().release();
            return true;
        }
        else{
            k.wezKarte(karta);
            semaphore.release();
            return false;
        }

    }

    public Karta podajSkrajnaKarteZKolekcji(boolean czyNajmniejsza){
        return k.znajdzSkrajna(czyNajmniejsza);
    }

    /**
     * Uruchom wątek.
     */
    @Override
    public void run() {
        System.out.println(nazwa+": startuje.");

        Karta skrajnaKartaPartnera;

        while(!"hell".equals("frozen over")){

            skrajnaKartaPartnera = partnerDoWymiany.podajSkrajnaKarteZKolekcji(!czySzukaNajmniejszych());

            if (czySzukaNajmniejszych()) {
                System.out.println(nazwa+": szuka najmniejszych. Skrajna karta partnera: "+skrajnaKartaPartnera);
                Karta skrajnaMoja = podajSkrajnaKarteZKolekcji(czySzukaNajmniejszych());
                System.out.println(nazwa+": Skrajna moja: "+skrajnaMoja);
                boolean war = skrajnaKartaPartnera.compareTo(skrajnaMoja)>0;
                if (war) {
                    System.out.println(nazwa+": break");
                    break;
                }
            }
            else {
                System.out.println(nazwa+": szuka największych. Skrajna karta partnera: "+skrajnaKartaPartnera);
                Karta skrajnaMoja = podajSkrajnaKarteZKolekcji(czySzukaNajmniejszych());
                System.out.println(nazwa+": Skrajna moja: "+skrajnaMoja);
                boolean war =skrajnaKartaPartnera.compareTo(skrajnaMoja)<0;
                if (war) {
                    System.out.println(nazwa+": break");
                    break;
                }
            }

            Karta kartaDoWymiany = podajSkrajnaKarteZKolekcji(czySzukaNajmniejszych());
            zaproponujKarte(kartaDoWymiany);

            System.out.println(this+": proponuje kartę do wymiany: "+kartaDoWymiany);
        }

        System.out.println(this.toString());
    }


    public KolekcjaKart getK() {
        return k;
    }

    public void setK(KolekcjaKart k) {
        this.k = k;
    }

    public PosiadaczKart getPartnerDoWymiany() {
        return partnerDoWymiany;
    }

    public void setPartnerDoWymiany(PosiadaczKart partnerDoWymiany) {
        this.partnerDoWymiany = partnerDoWymiany;
    }

    public String getNazwa() {
        return nazwa;
    }

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

    public void setSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }
}
