/**
 * 
 */
package main;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Rafał Kołodziejek
 * 
 */
public class Main {

	public static final int N = 5;
	public static final int licznik = 101;
	public static Skrytka skrytka;
	private static Sekretarka sekretarka;
	private static Dyrektor[] dyrektorzy;

	public static void main(String[] args) {
		skrytka = new Skrytka(N);
		sekretarka = new Sekretarka(10, 20);
		dyrektorzy = new Dyrektor[N];
		for (int i = 0; i < N; i++) {
			dyrektorzy[i] = new Dyrektor(i, 115);
		}

		List<Thread> watki = new LinkedList<Thread>();
		watki.add(new Thread(sekretarka));
		for (Dyrektor dyrektor : dyrektorzy) {
			watki.add(new Thread(dyrektor));
		}

		for (Thread t : watki) {
			t.start();
		}

		sekretarka = null;
		dyrektorzy = null;
	}

}
