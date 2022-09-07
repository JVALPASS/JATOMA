package rubrica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Rubrica {
	JFrame mainFrame;
	public static ArrayList<String> nomi= new ArrayList<String>();
	public static ArrayList<String> cognomi= new ArrayList<String>();
	public static ArrayList<String> numeri= new ArrayList<String>();
	public static ArrayList<String> email= new ArrayList<String>();
	public static LinkedHashMap<Integer, JTextField> text=new LinkedHashMap<Integer, JTextField>(); 



	public static LinkedHashMap<Integer, JButton> buttonModifica=new LinkedHashMap<Integer, JButton>();
	public static LinkedHashMap<Integer, JButton> buttonElimina=new LinkedHashMap<Integer, JButton>();
	public Rubrica(){
		mainFrame = new JFrame("Rubrica");
		mainFrame.setSize(200, 500);
		JPanel frame= new JPanel();
		JButton aggiungi=new JButton("Nuovo Contatto");
		aggiungi.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				new Contatto();
				mainFrame.setVisible(false); 
			} 
		});
		frame.add(aggiungi); 
		for(int i=0; i<5;i++){
			final Integer n = new Integer(i);
			text.put(n,new JTextField("Contatto"+" "+(i+1), 15)); 
			buttonModifica.put(n,new JButton("Modifica Contatto"+" "+(i+1)));
			buttonElimina.put(n,new JButton("Elimina Contatto" + " "+(i+1)));
			buttonModifica.get(n).addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent e) { 
					new Contatto(nomi.get(n.intValue()), cognomi.get(n.intValue()), numeri.get(n.intValue()), email.get(n.intValue()), n.intValue());
					mainFrame.setVisible(false);
				}}); 
			buttonElimina.get(n).addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int j=n.intValue(); 
					nomi.remove(j); 
					cognomi.remove(j);
					numeri.remove(j);
					email.get(j);
					new Rubrica();
					mainFrame.setVisible(false); 
				}
			});
			text.get(n).setVisible(false);
			buttonModifica.get(n).setVisible(false);
			buttonElimina.get(n).setVisible(false); 
			frame.add(text.get(n));
			frame.add(buttonModifica.get(n));
			frame.add(buttonElimina.get(n)); 
		}
		if(nomi.size()!=0){
			for(int i=0; i<nomi.size(); i++){
				Integer n = new Integer(i);
				text.get(n).setText(nomi.get(n.intValue())+" "+cognomi.get(n.intValue()));
				text.get(n).setVisible(true);
				buttonModifica.get(n).setVisible(true);
				buttonElimina.get(n).setVisible(true);
			}
		}
		mainFrame.add(frame); 
		mainFrame.setVisible(true); 
	}
	public static void main(String[] args) {    
		new Rubrica();
	} 

}
