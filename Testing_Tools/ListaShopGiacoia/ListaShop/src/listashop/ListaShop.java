package listashop;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ListaShop { 
	 
	   private JFrame mainFrame; 
	   private JTable tabella;
	   private JTextField oggetto;
	   private JTextField costo;   
	   private JTextField negozio;  
	   private JButton aggiungi; 
	   private JButton rimuovi;
	   private DefaultTableModel model;  
	   private String[] columnName = {  "Oggetto", "Costo", "Negozio"};
	   private Object[][] data = {   {"Giacca", "60", "Sottotono" },};
	   
	   public ListaShop(){
	   model = new DefaultTableModel(data,columnName);
	   mainFrame = new JFrame("Java AWT ListaShop");
	   tabella = new JTable(model);
	   oggetto = new JTextField("oggetto", 12);
	   costo = new JTextField("costo", 6);
	   negozio = new JTextField("negozio", 12);
	   aggiungi = new JButton("aggiungi");
	   rimuovi = new JButton("rimuovi");
	   JPanel panelUp = new JPanel();
	   mainFrame.setLayout(new FlowLayout());
	   panelUp.add(oggetto); 
	   panelUp.add(costo);
	   panelUp.add(negozio);
	   panelUp.add(aggiungi);
	   panelUp.add(rimuovi);
	   JPanel panelTable = new JPanel();
	   panelTable.add(tabella);
	   mainFrame.add(panelUp);
	   mainFrame.add(panelTable);
	   mainFrame.setVisible(true);
	   model.addTableModelListener(tabella);
	   aggiungi.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
			   Object[] obj = {oggetto.getText(),costo.getText(), negozio.getText()};
			   model.addRow(obj);
			   }
		   });
	   rimuovi.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
			   int[] rows = tabella.getSelectedRows();
			   for(int i: rows){
				   model.removeRow(i);
				   } 
			   }
		   });
	   } 
	   
	   public static void main(String[] args) {
		   new ListaShop();
		   }
	   } 