package rubrica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RubricaContatto {
	private JFrame mainFrame;
	private JPanel panel,paneldialog;
	private JButton salva,reset,logout;
	final JTextField nome,cognome,numero,email;
	private JScrollPane sp;
	final JTextArea note;
	private JDialog dialog;
	private JLabel label;
	private JMenuBar menubar;
	private JMenu menu;
	private JMenuItem menuitem;
	int pos =-1;
	private static File file = new File("C:\\Users\\Valerio\\Desktop\\rubrica.txt");
	public RubricaContatto() {
		mainFrame = new JFrame("frame_2");
		mainFrame.setSize(300,300);
		panel =  new JPanel();
		paneldialog = new JPanel();
		salva = new JButton("Salva");
		reset = new JButton("Reset"); 
		logout = new JButton("Logout");
		nome = new JTextField("nome",15);
		cognome = new JTextField("cognome",15);
		numero = new JTextField("numero",15);
		email = new JTextField("email",15);
		note = new JTextArea("note",4,20);
		sp = new JScrollPane(note);//pero me lo mettee con "note" nel costruttore
		
		menubar = new JMenuBar();
		menu = new JMenu("Seleziona");
		menubar.add(menu);
		
		for(int i = 0; i<5;i++) {
			menuitem = new JMenuItem("Contatto"+(i+1));
			menu.add(menuitem);
		}
		
		refreshRubrica();
		
		label = new JLabel("Contatto salvato!");
		dialog = new JDialog(mainFrame,"Alert");
		dialog.setSize(200,150);
		dialog.setVisible(false);
		
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nome.setText("");
				cognome.setText("");
				numero.setText("");
				email.setText("");
				note.setText("");
			}
		});
		
		salva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pos!=-1) {
					Rubrica.nomi.set(pos, nome.getText());
					Rubrica.cognomi.set(pos, cognome.getText());
					Rubrica.numeri.set(pos, numero.getText());
					Rubrica.email.set(pos, email.getText());
					Rubrica.note.set(pos, note.getText());
					saveRubricaToFile();
					label.setText("Contatto salvato!");
					dialog.setVisible(true);
				}else {
					label.setText("Nessun contatto selezionato");
					dialog.setVisible(true);
				}
			}
		});
		
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Rubrica();
				mainFrame.setVisible(false);
			}
		});
		panel.add(nome);
		panel.add(cognome);
		panel.add(numero);
		panel.add(email);
		panel.add(sp);
		panel.add(salva);
		panel.add(reset);
		panel.add(logout);
		paneldialog.add(label);
		dialog.add(paneldialog);
		mainFrame.add(panel);
		mainFrame.setJMenuBar(menubar);
		mainFrame.setVisible(true);
	}
	
	public void refreshRubrica() {
		menu.getItem(0).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nome.setText(Rubrica.nomi.get(0));
				cognome.setText(Rubrica.cognomi.get(0));
				numero.setText(Rubrica.numeri.get(0));
				email.setText(Rubrica.email.get(0));
				note.setText(Rubrica.note.get(0));
				label.setText("Selezionato contatto 1");
				dialog.setVisible(true);
				pos = 0;
			}
		});
		
		menu.getItem(1).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nome.setText(Rubrica.nomi.get(1));
				cognome.setText(Rubrica.cognomi.get(1));
				email.setText(Rubrica.email.get(1));
				note.setText(Rubrica.note.get(1));
				label.setText("Selezionato contatto 2");
				dialog.setVisible(true);
				pos=1;

			}
		});
		menu.getItem(2).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nome.setText(Rubrica.nomi.get(2));
				cognome.setText(Rubrica.cognomi.get(2));
				numero.setText(Rubrica.numeri.get(2));
				email.setText(Rubrica.email.get(2));
				note.setText(Rubrica.note.get(2));
				label.setText("Selezionato contatto 3");
				dialog.setVisible(true);
				pos=2;

			}
		});
		menu.getItem(3).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nome.setText(Rubrica.nomi.get(3));
				cognome.setText(Rubrica.cognomi.get(3));
				numero.setText(Rubrica.numeri.get(3));
				email.setText(Rubrica.email.get(3));
				note.setText(Rubrica.note.get(3));
				label.setText("Selezionato contatto 4");
				dialog.setVisible(true);
				pos=3;

			}
		});
		menu.getItem(4).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nome.setText(Rubrica.nomi.get(4));
				cognome.setText(Rubrica.cognomi.get(4));
				numero.setText(Rubrica.numeri.get(4));
				email.setText(Rubrica.email.get(4));
				note.setText(Rubrica.note.get(4));
				label.setText("Selezionato contatto 5");
				dialog.setVisible(true);
				pos=4;

			}
		});
	}
	public void saveRubricaToFile() {
		try {
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			for(int i = 0;i<5;i++) {
				bufferedWriter.write(Rubrica.nomi.get(i)+"#"+Rubrica.cognomi.get(i)+"##"
						+"###"+Rubrica.email.get(i)+"####"+Rubrica.note.get(i));
				bufferedWriter.newLine();
			}
			bufferedWriter.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
