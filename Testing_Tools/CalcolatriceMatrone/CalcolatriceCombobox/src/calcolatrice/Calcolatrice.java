package calcolatrice;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JComboBox;

public class Calcolatrice {
	private Frame mainFrame;
	private Button sum;
	private Button sub;
	private Button div;
	private Button mul;
	private TextField a;
	private TextField b;
	private TextField equals;
	private JComboBox combobox;
	public Calcolatrice() {
		mainFrame = new Frame("Java AWT Calc");
		sum = new Button("sum");
		sub = new Button("sub");
		div = new Button("div");
		mul = new Button("mul"); 
		a = new TextField("a", 3);
		b = new TextField("b", 3);
		equals = new TextField("equals", 3);
		combobox = new JComboBox();
		combobox.addItem("radice");
		combobox.addItem("modulo");
		combobox.addItem("quadrato");
		mainFrame.setSize(200, 200);
		mainFrame.setLayout(new FlowLayout());
		mainFrame.add(a);
		mainFrame.add(b);
		mainFrame.add(equals);
		mainFrame.add(sum);
		mainFrame.add(sub);
		mainFrame.add(mul);
		mainFrame.add(div);
		mainFrame.add(combobox);
		mainFrame.setVisible(true);
		sum.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						add(a.getText(), b.getText());
						}
					}
				);
		 equals.addFocusListener(new FocusListener() {
			 @Override
			 public void focusGained(FocusEvent e) {
				 equals.setBackground(new Color(255, 255, 0));
				 }
			 @Override
			 public void focusLost(FocusEvent e) {
				 equals.setBackground(new Color(255, 255, 255));
				 }});
		 sub.addActionListener(
					 new ActionListener() {
						 public void actionPerformed(ActionEvent e) {
							 subtract(a.getText(), b.getText());
							 }
						 } 
					 );
			 mul.addActionListener(new ActionListener() {
						 public void actionPerformed(ActionEvent e) {
							 multiply(a.getText(), b.getText());
							 }
						 }
			 );
			 div.addActionListener(
					 new ActionListener() { 
						 public void actionPerformed(ActionEvent e) {
							 divide(a.getText(), b.getText()); 
							 }
						 }
					 );
			 combobox.addActionListener(new ActionListener() {
				 public void actionPerformed(ActionEvent e) {
					 operazioni(equals.getText());
					 }
				 });
			 }
	private void add(String num1, String num2) {
		int a = Integer.parseInt(num1);
		int b = Integer.parseInt(num2);
		Integer ris = a + b;
		equals.setText(ris.toString());
		}  
	private void subtract(String num1, String num2) { 
		int a = Integer.parseInt(num1);
		int b = Integer.parseInt(num2);
		Integer ris = a - b;
		equals.setText(ris.toString());
		}
	private void multiply(String num1, String num2) {
		int a = Integer.parseInt(num1);
		int b = Integer.parseInt(num2);
		Integer ris = a * b;
		equals.setText(ris.toString());
		}
	private void divide(String num1, String num2) {
		double a = Double.parseDouble(num1);
		double b = Double.parseDouble(num2); 
		Double ris = a / b;
		equals.setText(ris.toString());
	}
	public void operazioni(String num) {
		String str = (String) combobox.getSelectedItem();
		int a = Integer.parseInt(num);
		if (str.equals("radice")) {
			Double ris = Math.sqrt(a);
			equals.setText(ris.toString());
		}
		if (str.equals("modulo")) {
			Integer ris = a % 2;
			equals.setText(ris.toString());
			}
		if (str.equals("quadrato")) {
			Integer ris = a * a;
			equals.setText(ris.toString());
			}
		}
	public static void main(String[] args) {
		Calcolatrice calc = new Calcolatrice();
		} 
}
	
	
	
				 
			 
						 
					 
						 
					 
						 
					 
							
							
						
					
				
	
