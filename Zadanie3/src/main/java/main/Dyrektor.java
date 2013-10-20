/**
 * 
 */
package main;

import org.apache.log4j.Logger;

/**
 * @author Rafał Kołodziejek
 * 
 */
public class Dyrektor implements Runnable {

	private static final Logger logger = Logger.getLogger(Dyrektor.class);

	private int k;
	private long przerwa;

	public Dyrektor(int k, long przerwa) {
		this.k = k;
		this.przerwa = przerwa;
	}

	public void run() {
		int licznik = Main.licznik;
		while (licznik-- > 0) {
			try {
				if (Main.skrytka.getMiejsca()[k].dodajKomunikat()) {
					if (logger.isInfoEnabled()) {
						logger.info("dyrektor " + k + " dodal komunikat");
					}
				}
				Thread.sleep(przerwa);
				// wait(przerwa);
			} catch (InterruptedException e) {
				logger.error(e, e);
			}
		}
	}

}
