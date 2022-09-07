package it.unisa.pawt;
import android.util.Log;

import org.apache.cordova.CordovaWebView;

import java.util.LinkedHashMap;

public class pTextField extends pComponent {
	//static String _value="";
	static LinkedHashMap<pTextField, String> values= new LinkedHashMap<pTextField, String>();
	static LinkedHashMap<pTextField, Boolean> visibility= new LinkedHashMap<pTextField, Boolean>();
	Integer _size;
	FocusListener _focusListener;

    public pTextField(String title, Integer size){
		_title = title;
		_size = size;
		values.put(this,"");
		visibility.put(this, true);

	}

	public String getText() {
		return values.get(this);
	}

	public String getTitle() {
		return _title;
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
	
	public void setText(String value) {
		//if(!value.equals(_value)) {
			values.put(this,value);
			Log.d("textfield", values.get(this));
				try {
					CordovaWebView wv = Constants.view;
					if(wv != null){
						wv.sendJavascript("app.setta('"+_title+"','"+values.get(this)+"')");

					}
				} catch(Exception e) {}
		//}
			requestFocus();
		}

	public void addFocusListener(FocusListener actionListener) {
		this._focusListener = actionListener;

	}

	public boolean focusGained() {
		if (_focusListener != null) {
			this._focusListener.focusGained(new FocusEvent(this, _title));
			return true;
		}
		return false;
	}

	public void focusLost() {
		if (_focusListener != null) {
			this._focusListener.focusLost(new FocusEvent(this, _title));
		}
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

    public void setRoot(pComponent root){
        this._root = root;
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

	public void setFocusable(boolean focusable){

	}
}
