package it.unisa.pawt;


import org.apache.cordova.CordovaWebView;

import java.util.LinkedHashMap;

public class pButton extends pComponent{
	static LinkedHashMap<pButton, Boolean> visibility= new LinkedHashMap<pButton, Boolean>();
	ActionListener _actionListener;
	String text;
	public pButton(String title){
		_title = title;
		visibility.put(this, true);
		text = title;
	}

	public void addActionListener(ActionListener actionListener) {
		 this._actionListener = actionListener;

	}

	public void fire() {
		if(_actionListener != null){
			this._actionListener.actionPerformed(new ActionEvent(this, _title));
		}
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
		this._root = root;
	}
	public void setLabel(String text) {//renderlo dinamico
		this.text = text;
	}
	public String getLabel() {
		return text;
	}
}
