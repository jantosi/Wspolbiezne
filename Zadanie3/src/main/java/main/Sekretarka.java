/**
 * 
 */
package main;

import org.apache.log4j.Logger;

/**
 * @author Rafał Kołodziejek
 * 
 */
public class Sekretarka implements Runnable {

	private static final Logger logger = Logger.getLogger(Sekretarka.class);

	private long malaPrzerwa;
	private long duzaPrzerwa;

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
					// wait(malaPrzerwa);
				}
				Thread.sleep(duzaPrzerwa);
				// wait(duzaPrzerwa);
			}
		} catch (InterruptedException e) {
			logger.error(e, e);
		}
	}
}
