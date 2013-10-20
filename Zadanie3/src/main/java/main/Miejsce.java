/**
 * 
 */
package main;

import org.apache.log4j.Logger;

/**
 * @author Rafał Kołodziejek
 * 
 */
public class Miejsce {

	private static final Logger logger = Logger.getLogger(Miejsce.class);

	private Integer komunikaty = 0;

	public boolean dodajKomunikat() {
		synchronized (komunikaty) {
			komunikaty++;
			return true;
		}
	}

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
