/**
 * 
 */
package main;

import org.apache.log4j.Logger;

/**
 * Klasa reprezentujaca miejsce w skrytce.
 * 
 * @author Rafał Kołodziejek
 * 
 */
public class Miejsce {

	/**
	 * Loger.
	 */
	private static final Logger logger = Logger.getLogger(Miejsce.class);

	/**
	 * Liczba komunikatow znajdujacych sie w skrytce.
	 */
	private Integer komunikaty = 0;

	/**
	 * Metoda dodajaca nowy komunikat.
	 * 
	 * @return true, jesli dodano nowy komunikat.
	 */
	public boolean dodajKomunikat() {
		synchronized (komunikaty) {
			komunikaty++;
			return true;
		}
	}

	/**
	 * Metoda usuwajaca komunikat. Komunikat mozna wyjac tylko wtedy, gdy na miejscu w skrytce sa
	 * jakies komunikaty.
	 * 
	 * @return true, jesli usunieto komunikat komunikat.
	 */
	public boolean usunKomunikat() {
		synchronized (komunikaty) {
			if (komunikaty > 0) {
				komunikaty--;
				return true;
			} else {
				logger.warn("komunikatow juz nie ma");
				return false;
			}
		}
	}

}
