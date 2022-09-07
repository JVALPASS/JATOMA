package calculator;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator { 
	private Frame mainFrame;
	private Button sum;
	private Button sub;
	private Button div;
	private Button mul;
	private TextField a; 
	private TextField b;
	private TextField equals;
	public Calculator(){
		mainFrame = new Frame("Java AWT Calc");
		sum=new Button("sum");
		sub=new Button("sub");
		div=new Button("div");
		mul=new Button("mul");
		a=new TextField("a",3);
		b=new TextField("b",3);
		equals=new TextField("equals",3);
		mainFrame.setSize(200,200);
		mainFrame.setLayout(new FlowLayout());
		mainFrame.add(a);
		mainFrame.add(b);
		mainFrame.add(equals);
		mainFrame.add(sum);
		mainFrame.add(sub);
		mainFrame.add(mul);
		mainFrame.add(div);
		mainFrame.setVisible(true);
		sum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add(a.getText(),b.getText());
				}
			} 
		);
		sub.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						subtract(a.getText(),b.getText());
				}
			}
	
		);
		mul.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					multiply(a.getText(),b.getText());
				} 
			} 
		); 
		div.addActionListener( 
				new ActionListener() { 
					public void actionPerformed(ActionEvent e) { 
						divide(a.getText(),b.getText()); 
						} 
					} 
				); 
		} 
	public void add (String num1, String num2){ 
		int a=Integer.parseInt(num1); 
		int b=Integer.parseInt(num2); 
		Integer ris= a+b; 
		equals.setText(ris.toString()); 
	} 
	public void subtract (String num1, String num2){ 
			int a=Integer.parseInt(num1); 
			int b=Integer.parseInt(num2); 
			Integer ris= a-b; 
			equals.setText(ris.toString()); 
			
	} 
	public void multiply (String num1, String num2){ 
		int a=Integer.parseInt(num1); 
		int b=Integer.parseInt(num2); 
		Integer ris= a*b; 
		equals.setText(ris.toString()); 
	} 
	public void divide (String num1, String num2){ 
		double a=Double.parseDouble(num1); 
		double b=Double.parseDouble(num2); 
		Double ris= a/b; 
		equals.setText(ris.toString()); 
	} 
 
	public static void main(String[] args) { 
		Calculator calc=new Calculator(); 
	} 
}