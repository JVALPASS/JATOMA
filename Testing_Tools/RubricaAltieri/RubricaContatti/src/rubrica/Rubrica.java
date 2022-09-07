package rubrica;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

public class Rubrica {
	public static ArrayList<String> nomi = new ArrayList<String>();
	public static ArrayList<String> cognomi = new ArrayList<String>();
	public static ArrayList<String> email = new ArrayList<String>();
	public static ArrayList<String> note = new ArrayList<String>();
	public static ArrayList<String> numeri = new ArrayList<String>();
	private static File file = new File("C:\\Users\\Valerio\\Desktop\\rubrica.txt");
	
	private JFrame mainFrame;
	private JPanel panel;
	private JTextField name;
	private JPasswordField pass;
	private JProgressBar progBar;
	private JLabel label;
	private JButton btn;
	private Timer t;
	private boolean inProgress=false;
	public Rubrica() {
		if(!file.exists())
			createRubricaFile();
		readContactsFromFile();
		mainFrame = new JFrame("frame_1");
		mainFrame.setSize(400, 400);
		panel = new JPanel();
		name = new JTextField("user",10);
		char[] correctPass = {'u','n','i','s','a'};
		pass = new JPasswordField(10);
		pass.setName("passwordfield1");
		btn = new JButton("Accedi");
		progBar = new JProgressBar(0,5);
		progBar.setName("progressbar1");
		progBar.setVisible(false);
		label = new JLabel("Digita user e password per accedere");
		
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!inProgress) {
					if(name.getText().equals("unisa")&&Arrays.equals(pass.getPassword(),correctPass) )
					{
						label.setText("");
						btn.setBackground(new Color(0,204,0));
						progBar.setVisible(true);
						inProgress = true;
						progBar.setValue(0);
						startCountdown();
					}
					else {
						btn.setBackground(new Color(255,51,51));
						label.setText("Credenziali errate");
					}
				}
			}
		});
		panel.add(label);
		panel.add(name);
		panel.add(pass);
		panel.add(btn);
		panel.add(progBar);
		mainFrame.add(panel);
		mainFrame.setVisible(true);
	}
	public void startCountdown() {
		t = new Timer();
		t.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				progBar.setValue(progBar.getValue()+1);
				label.setText("Accesso tra "+
				(progBar.getMaximum()-progBar.getValue())+" secondi");
				if(progBar.getValue()==progBar.getMaximum()) {
					t.cancel();
					inProgress=false;
					new RubricaContatto();
					mainFrame.setVisible(false);
				}
			}	
		}, 1*1000, 1*1000);
	}
	public static boolean readContactsFromFile() {
		try(BufferedReader reader = new BufferedReader(new FileReader(file))){
				String record = reader.readLine();
				while(record!=null) {
					String n = record.substring(0,record.indexOf("#"));
					String c = record.substring(record.indexOf("#")+1,record.indexOf("##"));
					String t = record.substring(record.indexOf("##")+2,record.indexOf("###"));
					String e = record.substring(record.indexOf("###")+3,record.indexOf("####"));
					String nt = record.substring(record.indexOf("####")+4,record.length());
					nomi.add(n);
					cognomi.add(c);
					numeri.add(t);
					email.add(e);
					note.add(nt);
					record = reader.readLine();
				}
				reader.close();
				return true;
		}
		catch(IOException e) {
			System.out.println(e);
		}
		return false;
	}
	
	public static void createRubricaFile() {
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(file);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			bufferedWriter.write("Nome#Cognome##Email###Note####");
			bufferedWriter.newLine();
			bufferedWriter.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Rubrica();
	}

}
	

