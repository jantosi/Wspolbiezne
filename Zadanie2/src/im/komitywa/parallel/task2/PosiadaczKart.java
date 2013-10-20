package im.komitywa.parallel.task2;

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

    /**
     * Posiadacz otrzymuje kartę i rozpatruje, czy pasuje mu do wymiany.
     *
     * @param karta Karta zaproponowana na wymiane
     * @return true: karta przyjęta. false: karta odrzucona.
     */
    public boolean przyjmijKarte(Karta karta){
        return true;
    }

    /** Posiadacz proponuje do wymiany jedna ze swoich kart.
     *
     * @param karta Karta do wymiany.
     * @return true: karta przyjeta przez innego gracza. false: karta nieprzyjeta.
     */
    public boolean zaproponujKarte(Karta karta){
        return partnerDoWymiany.przyjmijKarte(karta);
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
            skrajnaKartaPartnera = partnerDoWymiany.podajSkrajnaKarteZKolekcji(czySzukaNajmniejszych());

            if (czySzukaNajmniejszych()) {
                if (skrajnaKartaPartnera.compareTo(podajSkrajnaKarteZKolekcji(czySzukaNajmniejszych()))>0) {
                    break;
                }
            }
            else {
                if (skrajnaKartaPartnera.compareTo(podajSkrajnaKarteZKolekcji(czySzukaNajmniejszych()))<0) {
                    break;
                }
            }

            Karta kartaDoWymiany = podajSkrajnaKarteZKolekcji(czySzukaNajmniejszych());
            zaproponujKarte(kartaDoWymiany);
        }
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
}
