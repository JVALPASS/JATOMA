package concessionaria;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Concessionaria { 
	public static ArrayList<String> marcaTabella = new ArrayList<>();
	public static ArrayList<String> modelloTabella = new ArrayList<>();
	public static ArrayList<String> telaioTabella = new ArrayList<>();
	public static ArrayList<String> annoTabella = new ArrayList<>();
	public static ArrayList<String> prezzoTabella = new ArrayList<>();
	private JFrame mainFrame; 
	private JTable tabella;
	private JPanel paneldialog;
	private JTextField marca;
	private JTextField modello;   
	private JTextField numeroTelaio;  
	private JTextField anno;  
	private JTextField prezzo;  
	private JButton logout;
	private JMenuBar menubar;
	private JMenu menu;
	private JMenuItem menuitem;
	private JDialog dialog;
	private JButton aggiungi; 
	private JButton rimuovi;
	private JButton salva;
	private DefaultTableModel model;  
	private String[] columnName = {  "Marca", "Modello", "Numero Telaio", "Anno", "Prezzo"};
	private Object[][] data = {   {"Fiat", "Punto", "ZLA841500813539","2005","12.800\u20ac" },};
	private static File file = new File("C:\\Users\\Valerio\\Desktop\\autoSalvate.txt");


	public Concessionaria(){
		model = new DefaultTableModel(data,columnName);
		mainFrame = new JFrame("Concessionaria");
		tabella = new JTable(model);
		tabella.setName("Tabella0");
		marca = new JTextField("marca", 12);
		marca.setText("Marca");
		modello = new JTextField("modello", 12);
		modello.setText("Modello");
		numeroTelaio = new JTextField("numeroTelaio", 12);
		numeroTelaio.setText("Numero Telaio");
		anno = new JTextField("anno", 12);
		anno.setText("Anno");
		prezzo = new JTextField("prezzo", 12);
		prezzo.setText("Prezzo");
		Label campoD = new Label("campoD");
		aggiungi = new JButton("aggiungi");
		rimuovi = new JButton("rimuovi");
		salva = new JButton("salva");
		logout = new JButton("Logout");
		JPanel panelUp = new JPanel();
		paneldialog = new JPanel();
		mainFrame.setLayout(new FlowLayout());
		campoD.setFont(new Font("Courier New", Font.BOLD, 15)); 
		panelUp.add(marca); 
		panelUp.add(modello);
		panelUp.add(numeroTelaio);
		panelUp.add(anno);
		panelUp.add(prezzo);
		panelUp.add(aggiungi);
		panelUp.add(rimuovi);
		panelUp.add(salva);
		panelUp.add(logout);
		paneldialog.add(campoD); 
		JPanel panelTable = new JPanel();
		panelTable.add(tabella);
		mainFrame.add(panelUp);
		mainFrame.add(panelTable);
		menubar = new JMenuBar();
		menu = new JMenu("Seleziona");
		menubar.add(menu);
		for(int i = 0; i<3;i++) {
			menuitem = new JMenuItem("Auto"+(i+1));
			menuitem.setText("Esempio Auto"+(i+1));
			menu.add(menuitem);
		}
		dialog = new JDialog(mainFrame,"Alert");
		dialog.setSize(200,150);
		dialog.setVisible(false);
		mainFrame.setJMenuBar(menubar);
		dialog.add(paneldialog);
		mainFrame.setVisible(true);
		model.addTableModelListener(tabella);
		marca.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				marca.setBackground(new Color(255, 255, 0));
			}
			@Override
			public void focusLost(FocusEvent e) {
				marca.setBackground(new Color(255, 255, 255));
			}});
		modello.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				modello.setBackground(new Color(255, 255, 0));
			}
			@Override
			public void focusLost(FocusEvent e) {
				modello.setBackground(new Color(255, 255, 255));
			}});
		numeroTelaio.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				numeroTelaio.setBackground(new Color(255, 255, 0));
			}
			@Override
			public void focusLost(FocusEvent e) {
				numeroTelaio.setBackground(new Color(255, 255, 255));
			}});
		anno.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				anno.setBackground(new Color(255, 255, 0));
			}
			@Override
			public void focusLost(FocusEvent e) {
				anno.setBackground(new Color(255, 255, 255));
			}});
		prezzo.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				prezzo.setBackground(new Color(255, 255, 0));
			}
			@Override
			public void focusLost(FocusEvent e) {
				prezzo.setBackground(new Color(255, 255, 255));
			}});
		rimuovi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] rows = tabella.getSelectedRows();
				for(int i: rows){
					model.removeRow(i);
				} 
			}
		});
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Accesso();
				mainFrame.setVisible(false);
			}
		});

		aggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] obj = {marca.getText(),modello.getText(),
						numeroTelaio.getText(),anno.getText(),prezzo.getText()};
				model.addRow(obj);
			}
		});
		salva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			   	for(int i = 0;i<model.getRowCount();i++){
			   		marcaTabella.add(String.valueOf(model.getValueAt(i,0)));
			   		modelloTabella.add(String.valueOf(model.getValueAt(i,1)));
			   		telaioTabella.add(String.valueOf(model.getValueAt(i,2)));
			   		annoTabella.add(String.valueOf(model.getValueAt(i,3)));
			   		prezzoTabella.add(String.valueOf(model.getValueAt(i,4)));
			   	}		
				saveCarToFile(model.getRowCount());
				campoD.setText("Salvate Automoboli");
				dialog.setVisible(true);	
			}
		});
		menu.getItem(0).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marca.setText(Accesso.marcaA.get(0));
				modello.setText(Accesso.modelloA.get(0));
				numeroTelaio.setText(Accesso.telaioA.get(0));
				anno.setText(Accesso.annoA.get(0));
				prezzo.setText(Accesso.prezzoA.get(0));
				campoD.setText("Selezionata Auto 1");
				dialog.setVisible(true);
			}
		});

		menu.getItem(1).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marca.setText(Accesso.marcaA.get(1));
				modello.setText(Accesso.modelloA.get(1));
				numeroTelaio.setText(Accesso.telaioA.get(1));
				anno.setText(Accesso.annoA.get(1));
				prezzo.setText(Accesso.prezzoA.get(1));
				campoD.setText("Selezionata Auto 2");
				dialog.setVisible(true);
			}
		});
		menu.getItem(2).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marca.setText(Accesso.marcaA.get(2));
				modello.setText(Accesso.modelloA.get(2));
				numeroTelaio.setText(Accesso.telaioA.get(2));
				anno.setText(Accesso.annoA.get(2));
				prezzo.setText(Accesso.prezzoA.get(2));
				campoD.setText("Selezionata Auto 3");
				dialog.setVisible(true);
			}
		});	   

	}	
	public void saveCarToFile(int n) {
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		try {
			fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);
			for(int i = 0;i<n;i++) {
				bufferedWriter.write(marcaTabella.get(i)+"#"+modelloTabella.get(i)+"##"
						+telaioTabella.get(i)+"###"+annoTabella.get(i)+"####"+prezzoTabella.get(i)+"#####");
				bufferedWriter.newLine();
			}
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
}
