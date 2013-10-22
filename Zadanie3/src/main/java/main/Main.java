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

	/**
	 * Ilosc dyrektorow/miejsc w skrytce.
	 */
	public static final int N = 5;

	/**
	 * Liczba komunikatow wkladanych przez kazdego z dyrektorow.
	 */
	public static final int licznik = 101;

	/**
	 * Kolekcja miejsc na komunikaty.
	 */
	public static Skrytka skrytka;

	/**
	 * Obiekt reprezentujacy sekretarke.
	 */
	private static Sekretarka sekretarka;

	/**
	 * Kolekcja obiektow reprezentujacych dyrektorow.
	 */
	private static Dyrektor[] dyrektorzy;

	/**
	 * Metoda glowna programu.
	 * 
	 * @param args argumenty programu
	 */
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
