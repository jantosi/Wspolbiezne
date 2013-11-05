/**
 * 
 */
package wiadomosci;

/**
 * Klasa reprezentujaca skrytke z miejscami na komunikaty.
 * 
 * @author Rafał Kołodziejek
 * 
 */
public class Skrytka {

	/**
	 * Miejsca na komunikaty.
	 */
	private Miejsce[] miejsca;

	/**
	 * Konstruktor.
	 * 
	 * @param n liczba miejsc na komunikaty
	 */
	public Skrytka(int n) {
		miejsca = new Miejsce[n];
		for (int i = 0; i < n; i++) {
			miejsca[i] = new Miejsce();
		}
	}

	/**
	 * Getter miejsc na komunikaty.
	 * 
	 * @return tablica miejsc na komunikaty
	 */
	public Miejsce[] getMiejsca() {
		return miejsca;
	}

}
