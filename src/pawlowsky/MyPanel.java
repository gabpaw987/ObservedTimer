package pawlowsky;

import java.awt.GridLayout;
import java.util.GregorianCalendar;
import java.util.Observer;

import javax.swing.JPanel;

/**
 * MyPanel-Klasse, die von JPanel erbt und einen Timer(Observable) erstellt und diesem zwei Observer hinzufuegt.
 * Die Observer sind ebenfalls JPanels, die die Zeit die sie annehmen sollen vom Timer bekommen. 
 * 
 * @author Gabriel Pawlowsky
 * @version 22-01-2012
 */

public class MyPanel extends JPanel {
	/**
	 * Konstruktor
	 */
	public MyPanel() {
		//Layout auf GridLayout setzen, um die ObserverPanels schoen anzuordnen
		this.setLayout(new GridLayout(2,1));
		
		//Timer, der bei einer Zeit von 30 Sekunden beginnt erstellen
		Timer timer = new Timer(new GregorianCalendar(0,0,0,0,0,30));
		
		//Observer-Array mit zwei Observers erstellen, die Observer zum Array und Panel hinzufuegen und
		//dem Timer als Observer uebergeben. Hint:das GridLayout auf 40*50 aendern, 2000 Observer erstellen
		//und fullscreen machen sieht extrem gut aus, deswegen habe ich auch fuer zwei aufgrund der Aenderbarkeit
		//ein Array gemacht
		Observer[] opa = new Observer[2];
		
		for(int i = 0; i < 2; i++){
			opa[i] = new ObserverPanel();
			timer.addObserver(opa[i]);
			this.add((JPanel)opa[i]);
		}
	}
}