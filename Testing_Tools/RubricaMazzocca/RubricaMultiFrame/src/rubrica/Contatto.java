package rubrica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Contatto {
	JFrame mainFrame;
	public Contatto(){
		mainFrame = new JFrame("Contatto");
		mainFrame.setSize(200, 200);
		JPanel frame=new JPanel();
		JButton salva = new JButton("Salva");
		JButton reset = new JButton("Reset");
		JButton indietro= new JButton("Indietro");
		final JTextField nome = new JTextField("nome",15);
		final JTextField cognome =  new JTextField("cognome",15);
		final JTextField numero =  new JTextField("numero",15); 
		final JTextField email = new JTextField("email",15); 
		frame.add(nome); 
		frame.add(cognome);
		frame.add(numero);
		frame.add(email);
		frame.add(salva);
		frame.add(reset);
		frame.add(indietro);
		mainFrame.add(frame);
		mainFrame.setVisible(true);
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nome.setText("");
				cognome.setText("");
				numero.setText("");
				email.setText("");
				}
			});
		salva.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				Rubrica.nomi.add(nome.getText());
				Rubrica.cognomi.add(cognome.getText());
				Rubrica.numeri.add(numero.getText());
				Rubrica.email.add(email.getText());
				new Rubrica();
				mainFrame.setVisible(false);
				}
			});
		indietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Rubrica(); 
				mainFrame.setVisible(false);
				}
			});
		} 
	public Contatto(String name2,String surname2,String number2,String email3, int n ){
		final int k=n;
		final String name=name2; 
		final String surname=surname2;
		final String number=number2;
		final String email2=email3;
		mainFrame = new JFrame("Contatto"); 
		mainFrame.setSize(200, 200);
		JPanel frame= new JPanel();
		JButton salva = new JButton("Salva"); 
		JButton reset = new JButton("Reset");
		JButton indietro= new JButton("Indietro");
		final JTextField nome = new JTextField("nome",15);
		final JTextField cognome =  new JTextField("cognome",15); 
		final JTextField numero =  new JTextField("numero",15); 
		final JTextField email = new JTextField("email",15);
		nome.setText(name);
		cognome.setText(surname);
		numero.setText(number);
		email.setText(email2);
		frame.add(nome);
		frame.add(cognome);
		frame.add(numero);
		frame.add(email);
		frame.add(salva);
		frame.add(reset);
		frame.add(indietro);
		mainFrame.add(frame);   
		mainFrame.setVisible(true);
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nome.setText(""); 
				cognome.setText(""); 
				numero.setText(""); 
				email.setText(""); 
				} 
			}); 
		salva.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				Rubrica.nomi.set(k, nome.getText()); 
				Rubrica.cognomi.set( k, cognome.getText());
				Rubrica.numeri.set(k, numero.getText());
				Rubrica.email.set(k, email.getText()); 
				new Rubrica(); 
				mainFrame.setVisible(false); 
				} 
			});
		indietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
 
				new Rubrica();
				mainFrame.setVisible(false);}  });    }  } 