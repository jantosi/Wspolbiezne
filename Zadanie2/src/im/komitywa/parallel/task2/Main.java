package im.komitywa.parallel.task2;

public class Main {

    public static void main(String[] args) {
        PosiadaczKart jace, lilliana;
        jace = new PosiadaczKart();
        lilliana = new PosiadaczKart();

        jace.setNazwa("Jace");
        jace.setPartnerDoWymiany(lilliana);
        Integer[] zbiorJ = new Integer[]{1,3,5,7,9,11,13,15,17,19};

        KolekcjaKart kolekcjaKartJace = new KolekcjaKart();
        for(Integer val: zbiorJ){
            kolekcjaKartJace.wezKarte(new Karta(val));
        }
        jace.setK(kolekcjaKartJace);


        lilliana.setNazwa("Lilliana");
        lilliana.setPartnerDoWymiany(jace);
        Integer[] zbiorL = new Integer[]{0,2,4,6,8,10,12,14,16,18};

        KolekcjaKart kolekcjaKartLilliana = new KolekcjaKart();
        for(Integer val: zbiorL){
            kolekcjaKartLilliana.wezKarte(new Karta(val));
        }
        lilliana.setK(kolekcjaKartLilliana);


    }
}
