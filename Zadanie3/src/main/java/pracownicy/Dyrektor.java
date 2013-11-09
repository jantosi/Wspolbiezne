/**
 * 
 */
package pracownicy;

import main.Main;

import org.apache.log4j.Logger;

/**
 * Obiekt wstawiający komunikaty do skrytki (obiekt klasy Miejsce bedacy elementem kolekcji klasy
 * Skrytka).
 * 
 * @author Rafał Kołodziejek
 * 
 */
public class Dyrektor implements Runnable {

	/**
	 * Loger.
	 */
	private static final Logger logger = Logger.getLogger(Dyrektor.class);

	/**
	 * Numer skrytki.
	 */
	private int k;

	/**
	 * Czas pomiedzy wstawieniem dwoch komunikatow.
	 */
	private long przerwa;

	/**
	 * Konstruktor.
	 * 
	 * @param k numer skrytki
	 * @param przerwa czas pomiedzy wstawieniem dwoch komunikatow
	 */
	public Dyrektor(int k, long przerwa) {
		this.k = k;
		this.przerwa = przerwa;
	}

	public void run() {
		int licznik = Main.licznik;
		while (licznik > 0) {
			try {
				if (Main.skrytka.getMiejsca()[k].dodajKomunikat()) {
					if (logger.isInfoEnabled()) {
						logger.info("dyrektor " + k + " dodal komunikat");
					}
					licznik--;
				}
				Thread.sleep(przerwa);
			} catch (InterruptedException e) {
				logger.error(e, e);
			}
		}
	}

}
