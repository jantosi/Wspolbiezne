/**
 * 
 */
package pracownicy;

import main.Main;

import org.apache.log4j.Logger;

import wiadomosci.Miejsce;

/**
 * Klasa reprezentujaca sekretarke.
 * 
 * @author Rafał Kołodziejek
 * 
 */
public class Sekretarka implements Runnable {

	/**
	 * Loger.
	 */
	private static final Logger logger = Logger.getLogger(Sekretarka.class);

	/**
	 * Czas pomiedzy zajrzeniem do dwoch sasiednich miejsc w skrytce.
	 */
	private long malaPrzerwa;

	/**
	 * Czas pomiedzy dwoma kolejnymi przegladaniami skrytki.
	 */
	private long duzaPrzerwa;

	/**
	 * Konstruktor.
	 * 
	 * @param malaPrzerwa czas pomiedzy zajrzeniem do dwoch sasiednich miejsc w skrytce
	 * @param duzaPrzerwa czas pomiedzy dwoma kolejnymi przegladaniami skrytki
	 */
	public Sekretarka(long malaPrzerwa, long duzaPrzerwa) {
		this.malaPrzerwa = malaPrzerwa;
		this.duzaPrzerwa = duzaPrzerwa;
	}

	public void run() {
		int licznik = Main.licznik * Main.N;
		try {
			while (licznik > 0) {
				int i = 0;
				for (Miejsce miejsce : Main.skrytka.getMiejsca()) {
					if (miejsce.usunKomunikat()) {
						if (logger.isInfoEnabled()) {
							logger.info("sekretarka usunela komunikat " + i);
						}
						licznik--;
					}
					i++;
					Thread.sleep(malaPrzerwa);
				}
				Thread.sleep(duzaPrzerwa);
			}
		} catch (InterruptedException e) {
			logger.error(e, e);
		}
	}
}
