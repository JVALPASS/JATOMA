package codice_fiscale;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;

public class CodiceFiscale_Java {
	int sesso;   
	public CodiceFiscale_Java(){
		JFrame mainFrame = new JFrame("CodiceFiscale");
		TextField nome = new TextField("nome", 20);
		TextField cognome = new TextField("cognome", 20);
		TextField dataNascita = new TextField("dataNascita", 20);
		dataNascita.setText("dataNascita:21/07/17");
		JRadioButton uomo = new JRadioButton("uomo");
		JRadioButton donna = new JRadioButton("donna");
		TextField comune = new TextField("comune", 20);
		mainFrame.add(nome);
		mainFrame.add(cognome);
		mainFrame.add(dataNascita);
		mainFrame.add(uomo);
		mainFrame.add(donna);
		mainFrame.add(comune);
		Button button = new Button("button");
		button.setLabel("Genera Codice");
		Label codice = new Label("codice");
		codice.setText("Codice Fiscale");
		codice.setFont(new Font("Courier New", Font.BOLD, 15));
		JMenuBar barraMenu = new JMenuBar();
		JMenu menu_file = new JMenu("menu_file");
		menu_file.setText("File");
		JMenuItem menuItem_nuovo = new JMenuItem("menuItem_nuovo");
		menuItem_nuovo.setText("Nuovo");
		JMenuItem menuItem_genera = new JMenuItem("menuItem_genera");
		menuItem_genera.setText("Genera");
		JMenuItem menuItem_resetta = new JMenuItem("menuItem_resetta");
		menuItem_resetta.setText("Resetta");
		JMenuItem menuItem_salva = new JMenuItem("menuItem_salva");
		menuItem_salva.setText("Salva");
		JMenuItem menuItem_chiudi = new JMenuItem("menuItem_chiudi");
		menuItem_chiudi.setText("Chiudi");
		menu_file.add(menuItem_nuovo);
		menu_file.add(menuItem_genera);
		menu_file.add(menuItem_resetta);
		menu_file.add(menuItem_salva);
		menu_file.add(menuItem_chiudi);
		JMenu menu_impostazioni = new JMenu("menu_impostazioni");
		menu_impostazioni.setText("Impostazioni");
		JMenuItem menuItem_coloreBlack = new JMenuItem("menuItem_coloreBlack");
		menuItem_coloreBlack.setText("Black");
		JMenuItem menuItem_coloreBlue = new JMenuItem("menuItem_coloreBlue");
		menuItem_coloreBlue.setText("Blue");
		menu_impostazioni.add(menuItem_coloreBlack);
		menu_impostazioni.add(menuItem_coloreBlue);
		JMenu menu_altro = new JMenu("menu_altro"); 
		menu_altro.setText("Altro");
		JMenuItem menu_esempioUno = new JMenuItem("menu_esempioUno");
		menu_esempioUno.setText("Esempio uno");
		JMenuItem menu_esempioDue = new JMenuItem("menu_esempioDue");
		menu_esempioDue.setText("Esempio Due");
		menu_altro.add(menu_esempioUno);
		menu_altro.add(menu_esempioDue);
		barraMenu.add(menu_file);
		barraMenu.add(menu_impostazioni);
		barraMenu.add(menu_altro);
		mainFrame.setJMenuBar(barraMenu);
		mainFrame.add(button);
		mainFrame.add(codice);
		mainFrame.setLayout(new FlowLayout());
		mainFrame.setSize(220, 350);
		mainFrame.setVisible(true);
		uomo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(uomo.isSelected())
					sesso = 0;
				}
			});
		donna.addActionListener(new ActionListener() {  
			@Override
			public void actionPerformed(ActionEvent e) {
				if(donna.isSelected())
					sesso = 1;    
			   }
			});
		menuItem_nuovo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nome.setText("");
				cognome.setText("");
				dataNascita.setText("");
				comune.setText("");
				uomo.setSelected(false);
				donna.setSelected(false);
				sesso = 0;
				codice.setText("");
				}
			}); 
		menu_esempioUno.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nome.setText("Valerio");
				cognome.setText("Passamano");
				dataNascita.setText("12/12/1995");
				comune.setText("G012");
				uomo.setSelected(true);
				donna.setSelected(false);
				sesso = 0; 
				} 
			});
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = calcoloCodiceFiscale(nome.getText(), 
						cognome.getText(), dataNascita.getText(), comune.getText(), sesso);
				codice.setText(s);
				}
			});
		}
	public String calcoloCodiceFiscale(String s,String g, String c,String a,int b) {
		return "PSSVLR95T12L259H";
	}
	public static void main(String args[]){
		new CodiceFiscale_Java(); 
	}   
}
		
		
			
		
			
