package concessionaria;

import java.awt.Color;
import java.awt.Font;
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

public class Accesso{
	public static ArrayList<String> marcaA = new ArrayList<>();
	public static ArrayList<String> modelloA = new ArrayList<>();
	public static ArrayList<String> telaioA = new ArrayList<>();
	public static ArrayList<String> annoA = new ArrayList<>();
	public static ArrayList<String> prezzoA = new ArrayList<>();
	private JFrame mainFrame;
	private JPanel panel;
	private JTextField name;
	private JPasswordField pass;
	private JProgressBar progBar;
	private JLabel label;
	private JButton btn;
	private Timer t;
	private boolean inProgress=false;
	private static File file = new File("C:\\Users\\Valerio\\Desktop\\auto.txt");
	
	public Accesso() {
		if(!file.exists())
			createConcessionaria();
		readCarFromFile();
		mainFrame = new JFrame("Login");
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
					if(name.getText().equals("unisa")
							&&Arrays.equals(pass.getPassword(),correctPass) )
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
					new Concessionaria();
					mainFrame.setVisible(false);
				}
			}	
		}, 1*1000, 1*1000);
	}
	public static boolean readCarFromFile() {
		BufferedReader reader = null;
		FileReader fr = null;
		try{
				fr = new FileReader(file);
				reader = new BufferedReader(fr);
				String record = reader.readLine();
				while(record!=null) {
					String marca = record.substring(0,record.indexOf("#"));
					String modello = record.substring(record.indexOf("#")+1,record.indexOf("##"));
					String telaio = record.substring(record.indexOf("##")+2,record.indexOf("###"));
					String anno = record.substring(record.indexOf("###")+3,record.indexOf("####"));
					String prezzo = record.substring(record.indexOf("####")+4,record.indexOf("#####"));
					marcaA.add(marca);
					modelloA.add(modello);
					telaioA.add(telaio);
					annoA.add(anno);
					prezzoA.add(prezzo);
					record = reader.readLine();
				}
				return true;
		}
		catch(IOException e) {
			System.out.println(e);
		}finally {
			try {
				if(reader!=null)
					reader.close();
				if(fr!=null)
					fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
		return false;
	}
	public static void createConcessionaria() {
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		try {
			fileWriter = new FileWriter(file);
		    bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write("Nissan#Juke##ZLA841000013537###2012####12.220\\u20ac#####");
			bufferedWriter.newLine();
			bufferedWriter.write("Fiat#Qubo##ZLA84100121338###2016####13.990\\u20ac#####");
			bufferedWriter.newLine();
			bufferedWriter.write("Mazda#RX-8##ZLA84100151349###2014####22.000\\u20ac#####");
			bufferedWriter.newLine();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
				try {
					if(bufferedWriter!=null)
						bufferedWriter.close();
					if(fileWriter!=null)
						fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	public static void main(String[] args) {
		new Accesso();
	}

}
