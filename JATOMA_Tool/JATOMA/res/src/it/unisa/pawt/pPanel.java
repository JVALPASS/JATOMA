package it.unisa.pawt;

import java.util.ArrayList;
import javax.accessibility.AccessibleContext;
import it.unisa.javada.Utils;


public class pPanel extends pComponent{
	pFrame frame;
	
	public pPanel(){
		frame = new pFrame("a");
		_title = "Panel"+Math.random();
	}
	
	public pPanel(boolean isDoubleBuffered){
		this();
	}	
	
	public pPanel(LayoutManager layout) {
		this();
	}
	
	public pPanel(LayoutManager layout, boolean isDoubleBuffered) {
		this();
	}
	
	public void add(pComponent comp){
        //comp.setRoot(this);
        //System.out.println(comp.getName() + " add to panel parent "+ this.getName());		
		frame.add(comp);
	}
	
	 public ArrayList<pComponent> getComponents(){
		 return frame.getComponents();
	 }
	 
	 public AccessibleContext getAccessibleContext() {
		 Utils.parsingWarning();
		 return null;
	 }
	 
	 public PanelUI getUI() {
		 Utils.parsingWarning();
		 return null;
	 }
	 
	 public String getUIClassID() {
		 Utils.parsingWarning();
		 return "";
	 }
	 
	 
	 protected String paramString() {
		 return _title;
	 }
	 
	 public void setUI(PanelUI ui) {
		 Utils.parsingWarning();
	 }
	 
	 
	 public void updateUI() {
		 Utils.parsingWarning();
	 }
	 
}

