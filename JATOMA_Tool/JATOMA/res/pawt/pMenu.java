package it.unisa.pawt;

import java.util.ArrayList;

import org.apache.cordova.CordovaWebView;

/**
 * Created by ALFREDO on 25/02/2017.
 */

public class pMenu extends pComponent {

    ArrayList<pMenuItem> array_menu = new ArrayList<pMenuItem>();
    String text;
    public pMenu(String title){
        _title = title;
        text = title;
    }

    public void add(pMenuItem pMenuItem){
        array_menu.add(pMenuItem);
    }

    public ArrayList<pMenuItem> getArray_menu() {
        return array_menu;
    }
    
    public pMenuItem getItem(int i) {
    	return array_menu.get(i);
    }

    public boolean has_item(){

        return !array_menu.isEmpty();
    }
    public String getText() {
    	return text;
    }
    public void setText(String value) {

		String _value = value;


		try {
			CordovaWebView wv = Constants.view;
			if(wv != null){
				wv.sendJavascript("app.setta('"+_title+"','"+"- "+_value+"')");
		//Log.d("sono in if", "appsetta : " + _title+"','"+"- "+_value);
			}	
		} catch(Exception e) {}
	}
}

