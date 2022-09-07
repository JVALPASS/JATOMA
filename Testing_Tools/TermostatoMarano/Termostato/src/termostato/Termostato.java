package termostato;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Termostato {
	int temperatura;
	boolean isAcceso;
	Frame mainFrame;
	public Termostato(){
		mainFrame = new Frame("Termostato");
		Panel display = new Panel();
		Panel tools = new Panel();
		Button accendi = new Button("accendi");
		Button spegni = new Button("spegni");
		Button temperaturaPIU = new Button("temperaturaPIU");
		Button temperaturaMENO = new Button("temperaturaMENO");
		Button timer = new Button("timer");
		Button risparmioEnergetico = new Button("risparmioEnergetico");
		TextField status = new TextField("status", 10);
		temperatura=0;
		isAcceso=false;
		mainFrame.setLayout(new FlowLayout());
		display.add(status); 
		display.add(temperaturaPIU);
		display.add(temperaturaMENO);
		tools.add(accendi);
		tools.add(spegni);
		tools.add(timer);
		tools.add(risparmioEnergetico);
		mainFrame.add(display);
		mainFrame.add(tools);
		mainFrame.setVisible(true); 
		accendi.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						turnOn(status); 
					}
				} 
		);
		spegni.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						turnOff(status);
					}
				} 
		);
		temperaturaPIU.addActionListener( 
				new ActionListener() { 
					public void actionPerformed(ActionEvent e) { 
						aumentaTemperatura(status); 
					} 
				} 
		);  
		temperaturaMENO.addActionListener( 
				new ActionListener() { 
					public void actionPerformed(ActionEvent e) { 
						diminuisciTemperatura(status); 
					} 
				} 
		);
		timer.addActionListener(
				new ActionListener() { 
					public void actionPerformed(ActionEvent e) { 
						timerSpegnimento(); 
					} 
				} 
		);  
		risparmioEnergetico.addActionListener( 
				new ActionListener() { 
					public void actionPerformed(ActionEvent e) { 
						risparmioEnergetico(); 
					} 
				} 
		); 
	}
	public void turnOn(TextField s){ 
		isAcceso = true; 
		s.setText(temperatura+"◦C"); 
	} 
	public void turnOff(TextField s){ 
		isAcceso = false;
		s.setText("OFF"); 
	}
	public void aumentaTemperatura(TextField status){
		if(isAcceso) 
			status.setText(++temperatura+"◦c");
	} 
	public void diminuisciTemperatura(TextField s){
		if(isAcceso) 
			s.setText(--temperatura+"◦c"); 
	} 
	public void timerSpegnimento(){  
		//  
	}  
	public void risparmioEnergetico(){
		// 
	}
	public static void main(String[] args) { 
		new Termostato(); 
	}
}
