package it.unisa.pawt;

import java.util.ArrayList;

/**
 * Created by ALFREDO on 25/02/2017.
 */

public class pMenuItem extends pComponent{

    ActionListener _actionListener;
    ArrayList<pMenuItem> array_menu = new ArrayList<pMenuItem>();
    String text ;

    public pMenuItem(String title){
        _title = title;
        text = title;
    }

    public void add(pMenuItem pMenuItem){
        array_menu.add(pMenuItem);
    }

    public void addActionListener(ActionListener actionListener) {
        this._actionListener = actionListener;
    }

    public void fire() {
        if(_actionListener != null){
            this._actionListener.actionPerformed(new ActionEvent(this, _title));
        }
    }

    public ArrayList<pMenuItem> getArray_menu() {
        return array_menu;
    }
    public void setRoot(pComponent root){
    	super.setRoot(root);
    }
    public boolean has_item(){

        return !array_menu.isEmpty();
    }
    
    public void setEnable(String value) {
		/*
		if(!value.equals(_value)) {
			_value = value;
				try {
					CordovaWebView wv = Constants.view;
					if(wv != null){
						wv.sendJavascript("app.setta('"+_title+"','"+_value+"')");
				//Log.d("sono in if", "appsetta : " + _title);
						}	
				} catch(Exception e) {}
			}	
		*/
	}
    public String getText() {
    	return text;
    }
    public void setText(String text) {
    	this.text = text;
    }
    
}


