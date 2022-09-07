package it.unisa.pawt;

import java.util.LinkedHashMap;
import org.apache.cordova.CordovaWebView;
import org.w3c.dom.Document;
import android.util.Log;
import it.unisa.javada.Utils;
import java.util.LinkedHashMap;

/**
 * Created by CARMINE on 01/09/2018.
 */

public class pPasswordField extends pComponent {
	
	String _text="";
	Integer _size;
	static int count = 1;
	
	static LinkedHashMap<pPasswordField, Boolean> visibility= new LinkedHashMap<pPasswordField, Boolean>();
	static LinkedHashMap<pPasswordField, String> values= new LinkedHashMap<pPasswordField, String>();
	
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
		values.put(this, _text);
		visibility.put(this, true);
	}
	
	public void copy() {
		Log.w(_title, "Invoked method in pawt not yet implemented");
	}
	
	public void cut() {
		Log.w(_title, "Invoked method in pawt not yet implemented");
	}
	
	public boolean echoCharIsSet() {
		return false; //not set by default
	}
	
	public void setEchoChar(char c) {
		Log.w(_title, "Invoked method in pawt not yet implemented");
	}
	
	public char getEchoChar() {
		return '\u25CF'; //default echo char java enc
	}
	
	public char[] getPassword() {
		Log.d("Password retrieved", values.get(this));
		return values.get(this).toCharArray();	
		
	}
	
	public String getText() {
		Log.w(_title, "Warning: getText() is deprecated. As of Java 2 platform v1.2, replaced by getPassword");
		return values.get(this);
	}
	
	public void setText(String t) {
		values.put(this, t);
		try {
			CordovaWebView wv = Constants.view;
			if(wv != null){
				wv.sendJavascript("app.setta('"+_title+"','"+values.get(this)+"')");

			}
		} catch(Exception e) {}
		
	}
	
	public String getUIClassID() {
		Log.w(_title, "Invoked method in pawt not yet implemented");
		return "";
	}
	
	public String paramString() {
		return _title;
	}
	
	public void updateUI() {
		Log.w(_title, "Invoked method in pawt not yet implemented");
	}
	
	public boolean getVisible(){
		return visibility.get(this).booleanValue();
	}
	
	public void setVisible(boolean b){
		visibility.put(this,b);
		try {
			CordovaWebView wv = Constants.view;
			if(wv != null){
				wv.sendJavascript("app.setVisible('"+_title+"','"+visibility.get(this).booleanValue()+"')");

			}
		} catch(Exception e) {}

	}
	
	public void setRoot(pComponent root){
    	super.setRoot(root);
    }
	
	public void requestFocus(){
        try {
            CordovaWebView wv = Constants.view;
            if(wv != null){
                Log.d("FOCUS", _title);
                wv.sendJavascript("app.scrollTo('"+_title+"')");
            }
        } catch(Exception e) {}
    }
	
	public void setBackground(Color c) {
		String color = c.toString();
		String valore = "rgb(" + color + ")";
		try {
			CordovaWebView wv = Constants.view;
			if (wv != null) {
				wv.sendJavascript("app.colora('" + _title + "','" + valore + "')");
				//Log.d("sono in if", "appsetta : " + _title);
			}
		} catch (Exception e) {
		}
	}
	

	protected int getCount() {
		return count++;
	}
	

}
