package pawlowsky;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Observable;

/**
 * Timer-Klasse, die Observable ist(auf davon erbt) und zu der somit Observer hinzugefuegt werden. Sie pusht
 * ueber einen interne Thread-Klasse immer nach einer Sekunde eine Zeitaenderung um eine Sekunde nach unten
 * veranlasst. Die Observer werden mittels einer internen Liste gespeichert und per notifyObservers benachrichtigt.
 * 
 * @author Gabriel Pawlowsky
 * @version 22-01-2012
 */

public class Timer extends Observable{
	
	//Zeit, die der Timer besitzt und an die Observer weiter gibt
	GregorianCalendar time;
	
	/**
	 * Konstruktor, welcher einen uebergebenen gregorianischen Kalender ins Attribut einfuegt, um
	 * zu wissen bei welcher Zeit er starten soll. Ausserdem wird hier der Thread erzeugt und gestartet
	 * mit dem die Zeit hinuntergezaehlt und alle Observer benachrichtigt werden.
	 */
	public Timer(GregorianCalendar time){
		this.time = time;
		MyThread thread = new MyThread();
		thread.start();
	}
	
	/**
	 * Interne Klasse, welche ein Thread ist und beim Start so lange der Timer nicht abgelaufen ist zuerst eine
	 * Sekunde wartet, dann die Timerzeit um eine Sekunde verringert
	 */
	private class MyThread extends Thread{
		public void run(){
			//Ueberpruefung ob der Timer schon abgelaufen ist, wenn nicht dauerschleife bis man auf 0 ist.
			while(!(time.get(Calendar.HOUR_OF_DAY) ==0 && time.get(Calendar.MINUTE) ==0 && time.get(Calendar.SECOND) ==0)){
				try {
					//eine Sekunde warten
					Thread.sleep(1000);
					//Zeit um eine Sekunde verringern
					time.set(Calendar.SECOND, time.get(Calendar.SECOND)-1);
					//Variable, die angibt ob sie was veraendert hat, dass die Observer interressiert, auf true setzen
					setChanged();
					//Alle Observer benachrichtigen und ihnen per Push ueber die update-Methode in den Observern
					//die geaenderte Zeitvariable schicken. Wenn man Pull programmieren wollte wuerde man hier kein
					//Objekt uebergeben und die Observer muessten sich nach einer Benachrichtigung, wenn sie es wollen,
					//das geaenderte Objekt selbst holen.
					notifyObservers(time);
				//Exception falls er beim Warten unterbrochen wird abfangen
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
