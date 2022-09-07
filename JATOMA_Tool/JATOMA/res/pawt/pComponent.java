package it.unisa.pawt;


import org.apache.cordova.CordovaWebView;
import org.w3c.dom.Element;

import java.net.URISyntaxException;

public abstract class pComponent {
	
	String _title;
	pComponent _root;
	String _type;
	ComponentNode _nod;
	private Element _node;
	int _min;
	int _max;

	public void create(){}
	public void addActionListener(ActionListener actionListener) {}

	public void fire()  {}
	
	public void fire(String dest){}

    public void setRoot(pComponent root){}

	//nuovi metodi aggiunti
	public void requestFocus() {}
	public String getName(){ return _title; }
	public void setName(String name){_title = name; }
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


}
