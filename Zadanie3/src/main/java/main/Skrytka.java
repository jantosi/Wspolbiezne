/**
 * 
 */
package main;

/**
 * @author Rafał Kołodziejek
 * 
 */
public class Skrytka {

	private Miejsce[] miejsca;

	public Skrytka(int n) {
		miejsca = new Miejsce[n];
		for (int i = 0; i < n; i++) {
			miejsca[i] = new Miejsce();
		}
	}

	public Miejsce[] getMiejsca() {
		return miejsca;
	}

}
