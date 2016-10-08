package pawlowsky;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Observer-Panel, welches von JPanel erbt, Observer implementiert und nur ein JLabel besitzt.
 * Das ObserverPanel bekommt per push eine alle Sekunden eine neue Zeit vom Timer, sobald es zu diesem
 * hinzugefuegt wurde. Das JPanel gibt diese Zeit anschliessend aus.
 * 
 * @author Gabriel Pawlowsky
 * @version 22-01-2012
 */

public class ObserverPanel extends JPanel implements Observer{
		
	//JPanel welches dafuer zustaendig ist, die Zeit vom Timer auszugeben
	private JLabel timelabel;
		
	/**
	 * Konstruktor, welcher das JLabel erzeut und zum Panel hinzufuegt
	 */
	public ObserverPanel(){
		this.timelabel = new JLabel();
		this.add(timelabel);
	}
	
	/**
	 * Ueberschreiben, der Methode update, welche das implementierte Interface Observer vorgibt.
	 * In dieser wird das Objekt, dass sie vom Timer bekommt zuerst auf GregrorianCalendar gecastet
	 * und anschliessend wird ins JLabel die vom Timer uebergebene Zeit hinein geschrieben.
	 * Wenn man das ganze mit pull uebernehmen wuerde muss man auch entweder hier oder irgendwo anders im Observer
	 * wenn er notifiziert wird das betroffene Objekt getten.
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		//casten des Objekts
		GregorianCalendar cal = (GregorianCalendar)arg1;
		//Anzeigen der aktuellen Zeit im JLabel
		this.timelabel.setText(cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND));
	}
}
