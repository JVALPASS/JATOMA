package codicefiscale;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;

public class CodiceFiscale_Java {
	int sesso;
	private JDialog dialog;
	public CodiceFiscale_Java(){
		JFrame mainFrame = new JFrame("Codice Fiscale");
		dialog= new JDialog(mainFrame, "dialog");
		TextField nome = new TextField("nome",20);
		TextField cognome = new TextField("cognome", 20);
		TextField dataNascita = new TextField("dataNascita", 20);
		TextField textD = new TextField("textD", 20);
		Button buttonD= new Button("buttonD");
		buttonD.setLabel("inserisci campo mancane");
		Button annulla= new Button("annulla");
		Label campoD = new Label("campoD");
		campoD.setText("inserisci campo mancante:");
		campoD.setFont(new Font("Courier New", Font.BOLD, 15));
		JRadioButton uomo = new JRadioButton("uomo");
		JRadioButton donna = new JRadioButton("donna");
		TextField comune = new TextField("comune", 20);
		mainFrame.add(nome);
		mainFrame.add(cognome);
		mainFrame.add(dataNascita);
		mainFrame.add(uomo);
		mainFrame.add(donna);
		mainFrame.add(comune); 
		dialog.add(campoD); 
		dialog.add(textD);
		dialog.add(buttonD);
		dialog.add(annulla);
		dialog.setSize(300,200);
		Button button = new Button("button"); 
		button.setLabel("Genera Codice");
		Label codice = new Label("codice");
		codice.setText("-------------");
		codice.setFont(new Font("Courier New", Font.BOLD, 15));
		/* MENU */
		JMenuBar barraMenu = new JMenuBar();
		JMenu menu_file = new JMenu("file");
		menu_file.setText("File");
		JMenuItem menuItem_nuovo = new JMenuItem("nuovo");
		menuItem_nuovo.setText("Nuovo");
		JMenuItem menuItem_genera = new JMenuItem("genera");
		menuItem_genera.setText("Genera");
		JMenuItem menuItem_resetta = new JMenuItem("resetta");
		menuItem_resetta.setText("Resetta");
		JMenuItem menuItem_salva = new JMenuItem("salva");
		menuItem_salva.setText("Salva");
		JMenuItem menuItem_chiudi = new JMenuItem("chiudi");
		menuItem_chiudi.setText("Chiudi");
		menu_file.add(menuItem_nuovo);
		menu_file.add(menuItem_genera);
		menu_file.add(menuItem_resetta);
		menu_file.add(menuItem_salva);
		menu_file.add(menuItem_chiudi);
		JMenu menu_impostazioni = new JMenu("impostazioni");
		menu_impostazioni.setText("Impostazioni");
		JMenuItem menuItem_coloreBlack = new JMenuItem("coloreBlack");
		menuItem_coloreBlack.setText("Colore Nero");
		JMenuItem menuItem_coloreBlue = new JMenuItem("coloreBlue");
		menuItem_coloreBlue.setText("Colore Blue");
		menu_impostazioni.add(menuItem_coloreBlack);
		menu_impostazioni.add(menuItem_coloreBlue);
		JMenu menu_altro = new JMenu("altro");
		menu_altro.setText("Altro");
		JMenuItem menu_esempioUno = new JMenuItem("esempioUno"); 
		menu_esempioUno.setText("Esempio1");
		JMenuItem menu_esempioDue = new JMenuItem("esempioDue");
		menu_esempioDue.setText("Esempio2");
		menu_altro.add(menu_esempioUno);
		menu_altro.add(menu_esempioDue);
		barraMenu.add(menu_file);
		barraMenu.add(menu_impostazioni);
		barraMenu.add(menu_altro);
		mainFrame.setJMenuBar(barraMenu);
		mainFrame.add(button);
        mainFrame.add(codice);
        //dialog.setLayout(new FlowLayout());
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
        		nome.setText("Gianmaria");
        		cognome.setText("Trezza");
        		dataNascita.setText("21/11/1994");
        		comune.setText("C361T");
        		uomo.setSelected(true);
        		donna.setSelected(false);
        		sesso = 0;
        		}
        	}); 
        button.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		String s = null ;
        		if(nome.getText().length()==0 || cognome.getText().length()==0 
        				|| dataNascita.getText().length()==0 || comune.getText().length()==0){ 
        			if(nome.getText().length()==0)
        				campoD.setText("inserisici campo mancante:nome");
        			else if(cognome.getText().length()==0)
        				campoD.setText("inserisici campo mancante:congome"); 
        			else if(dataNascita.getText().length()==0)
        				campoD.setText("inserisici campo mancante:dataNascita"); 
        			else if(comune.getText().length()==0) 
        				campoD.setText("inserisici campo mancante:comune");
        			dialog.show();
        			} 
        		else
        			s = calcoloCodiceFiscale(nome.getText(),
        					cognome.getText(), dataNascita.getText(), comune.getText(), sesso);  
        		codice.setText(s);
        		}
 
        	});
        annulla.addActionListener(new ActionListener(){ 
        	public void actionPerformed (ActionEvent e){ 
        		dialog.setVisible(false); 
        		}
        	});
        buttonD.addActionListener(new ActionListener(){  
        	public void actionPerformed (ActionEvent e){  
        		String c=textD.getText(); 
        		if(nome.getText().length()==0){  
        			nome.setText(c);  
        			textD.setText("");  
        			} 
        		else if (cognome.getText().length()==0){ 
        			cognome.setText(c);
        			textD.setText(""); 
        			}   
        		else if(dataNascita.getText().length()==0){ dataNascita.setText(c);  
        		textD.setText("");
        		}  
        		else if(comune.getText().length()==0){
        			comune.setText(c);  
        			textD.setText("");  
        			}  
        		dialog.setVisible(false);  
        		} 
        	});
        } 
	private String calcoloCodiceFiscale(String text,
			String text2, String text3, String text4, int sesso) {
		
		return "PSSVLR95T12L259H";
	}
	public static void main(String args[]){      
		new CodiceFiscale_Java();   
	}
}
