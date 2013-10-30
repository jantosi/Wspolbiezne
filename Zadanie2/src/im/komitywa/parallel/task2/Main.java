package im.komitywa.parallel.task2;

public class Main {

    public static void main(String[] args) {
        PosiadaczKart jace, lilliana;
        jace = new PosiadaczKart();
        lilliana = new PosiadaczKart();

        jace.setNazwa("Jace");
        jace.setPartnerDoWymiany(lilliana);
        jace.szukaNajmniejszych(true);
        Integer[] zbiorJ = new Integer[]{1,3,5,13,15,17};

        KolekcjaKart kolekcjaKartJace = new KolekcjaKart();
        for(Integer val: zbiorJ){
            kolekcjaKartJace.wezKarte(new Karta(val));
        }
        kolekcjaKartJace.setDocelowyRozmiarKolekcji(kolekcjaKartJace.getRozmiarKolekcji());
        jace.setK(kolekcjaKartJace);


        lilliana.setNazwa("Lilliana");
        lilliana.setPartnerDoWymiany(jace);
        lilliana.szukaNajmniejszych(false);
        Integer[] zbiorL = new Integer[]{0,2,4,6,8,10,12,14,16,18};

        KolekcjaKart kolekcjaKartLilliana = new KolekcjaKart();
        for(Integer val: zbiorL){
            kolekcjaKartLilliana.wezKarte(new Karta(val));
        }
        kolekcjaKartLilliana.setDocelowyRozmiarKolekcji(kolekcjaKartLilliana.getRozmiarKolekcji());
        lilliana.setK(kolekcjaKartLilliana);


        Thread s = new Thread(jace);
        Thread t = new Thread(lilliana);

        s.start();
        t.start();

    }
}
