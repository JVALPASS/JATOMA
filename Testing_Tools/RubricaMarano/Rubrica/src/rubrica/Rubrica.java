package rubrica;

import java.awt.Button;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Rubrica{
	public Rubrica(){
	    Frame mainFrame = new Frame("Rubrica"); 
		Panel panelPulsanti = new Panel();
		Panel panelAnagrafica = new Panel();
		Panel panelContatti = new Panel(); 
		Button salva = new Button("salva"); 
		Button reset = new Button("reset");
		TextField residenza = new TextField("residenza",510); 
		TextField codiceFiscale = new TextField("codiceFiscale",10);
		TextField nome = new TextField("nome",10);
		TextField cognome = new TextField("cognome",10);
		TextField skype = new TextField("skype",10);
		TextField twitter = new TextField("twitter",10);
		TextField numero = new TextField("numero",10);
		TextField email = new TextField("email",10);
		TextField sitoWeb = new TextField("sitoWeb",10);
		TextField facebook = new TextField("facebook",10);
		panelContatti.add(skype);
		panelContatti.add(twitter); 
		panelContatti.add(numero); 
		panelContatti.add(email);
		panelContatti.add(sitoWeb);
		panelContatti.add(facebook);
		panelPulsanti.add(salva); 
		panelPulsanti.add(reset);
		panelAnagrafica.add(nome);
		panelAnagrafica.add(cognome);
		panelAnagrafica.add(residenza);
		panelAnagrafica.add(codiceFiscale);
		mainFrame.add(panelAnagrafica);
		mainFrame.add(panelContatti);
		mainFrame.add(panelPulsanti);
		mainFrame.setVisible(true); 
		reset.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					reset(panelAnagrafica);
					reset(panelContatti);
					}
				}
		);
	}
	public void reset(Panel p){ 
			for(Component component : p.getComponents())
				((TextField) component).setText("");
	}
	public static void main(String args[]) {
		new Rubrica();
	}	
}