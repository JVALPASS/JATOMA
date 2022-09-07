package it.unisa.pawt;

import javax.accessibility.AccessibleContext;
import org.w3c.dom.Document;
import it.unisa.javada.Utils;

/**
 * Created by CARMINE on 01/09/2018.
 */

public class pPasswordField extends pComponent {
	
	String _text="";
	Integer _size;
	static int count = 1; //to avoid same id title
	
	public pPasswordField() {
		this(null, "", 0);
	}
	
	public pPasswordField(String text) {
		this(null, text, 0);
	}
	
	public pPasswordField(int columns) {
		this(null, "", columns);
	}
	
	public pPasswordField(String text, int columns) {
		this(null, text, columns);
	}
	
	public pPasswordField(Document document, String text, int columns) {
		_text = text;
		_type = "pPasswordField";
		_title = "passwordfield"+getCount();
	}
	
	public void copy() {
		Utils.parsingWarning();
	}
	
	public void cut() {
		Utils.parsingWarning();
	}
	
	public boolean echoCharIsSet() {
		return false; //not set by default
	}
	
	public void setEchoChar(char c) {
		Utils.parsingWarning();
	}
	
	public AccessibleContext getAccessibleContext() {
		Utils.parsingWarning();
		return null;
	}
	
	public char getEchoChar() {
		return '\u25CF'; //default echo char java enc
	}
	
	public char[] getPassword() {
		return _text.toCharArray();	
		
	}
	
	public String getText() {
		System.out.println("Warning: getText() is deprecated. As of Java 2 platform v1.2, replaced by getPassword");
		return _text;
	}
	
	public void setText(String t) {
		_text=t;
	}
	
	public String getUIClassID() {
		Utils.parsingWarning();
		return "";
	}
	
	public String paramString() {
		return _title;
	}
	
	public void updateUI() {
		Utils.parsingWarning();
	}
	
	public void setVisible(boolean b){
    	
    }
	
	public void setRoot(pComponent root){
    	super.setRoot(root);
    }
	
	protected int getCount() {
		return count++;
	}
	
	public void create(){
		parserHTML.addPasswordField(_title, _text, this.getRoot().getElement());
	}

}
