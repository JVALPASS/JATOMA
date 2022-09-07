package it.unisa.pawt;

import java.util.LinkedHashMap;
import org.apache.cordova.CordovaWebView;
import org.w3c.dom.Document;
import it.unisa.javada.Utils;
import android.util.Log;
import java.util.LinkedHashMap;

/**
 * Created by CARMINE on 01/09/2018.
 */

public class pTextArea extends pComponent {
	
	static LinkedHashMap<pTextArea, String> values= new LinkedHashMap<pTextArea, String>();
	static LinkedHashMap<pTextArea, Boolean> visibility= new LinkedHashMap<pTextArea, Boolean>();
	
	int _rows;
	int _columns;
	String _text = "";
	static int count = 1;
	
	public pTextArea() {
		this(null, "", 0, 0);
	}
	
	public pTextArea(Document doc) {
		this(null, "", 0, 0);
	}
	
	public pTextArea(Document doc, String text, int rows, int columns) {
		_text = text;
		_rows = rows;
		_columns = columns;
		_type = "pTextArea";
		_title = "textarea"+getCount();
		values.put(this, _text);
		visibility.put(this, true);
	}
	
	public pTextArea(int rows, int columns) {
		this(null, "", rows, columns);
	}
	
	public pTextArea(String text, int rows, int columns) {
		this(null, text, rows, columns);
	}
	
	public pTextArea(String text) {
		this(null, text, 0, 0);
	}
	
	public void setText(String text) {
		values.put(this, text);
		try {
			CordovaWebView wv = Constants.view;
			if(wv != null){
				char[] s = values.get(this).toCharArray();
				wv.sendJavascript("app.setta('"+ _title +"','')");
				for (int i=0; i<s.length; i++){
					wv.sendJavascript("app.settaTextArea('"+ _title +"','"+s[i]+"')");
				}
			}
		} catch(Exception e) {}
		
		//requestFocus();
	}
	
	public String getText() {
		return values.get(this);
	}
	
	public void append(String str) {
		String s = values.get(this);
		s=s+str;
		setText(s);
	}
	
	protected Document createDefaultModel() {
		Log.w(_title, "Invoked method in pawt not yet implemented");
		return null;
	}
	
	public int getColumns() {
		return _columns;
	}
	
	protected int getColumnWidth() {
		Log.w(_title, "Invoked method in pawt not yet implemented");
		return 0;
	}
	
	public int getLineCount() {
		String s = values.get(this);
		if(s!=null) {
			String[] lines = s.split("\r\n|\r|\n");
			return lines.length;
		}
		else
			return 0;
	}
	
	public int getLineEndOffset(int line) {
		Log.w(_title, "Invoked method in pawt not yet implemented");
		return 0;
	}
	
	public int getLineOfOffset(int offset) {
		Log.w(_title, "Invoked method in pawt not yet implemented");
		return 0;
	}
	
	public int getLineStartOffset(int line) {
		Log.w(_title, "Invoked method in pawt not yet implemented");
		return 0;
	}
	
	public boolean getLineWrap() {
		Log.w(_title, "Invoked method in pawt not yet implemented");
		return false;
	}
	
	public Dimension getPreferredScrollableViewportSize() {
		Log.w(_title, "Invoked method in pawt not yet implemented");
		return null;
	}
	
	public Dimension getPreferredSize() {
		Log.w(_title, "Invoked method in pawt not yet implemented");
		return null;
	}
	
	protected int getRowHeight() {
		Log.w(_title, "Invoked method in pawt not yet implemented");
		return 0;
	}
	
	public int getRows() {
		return _rows;
	}
	
	public boolean getScrollableTracksViewportWidth() {
		Log.w(_title, "Invoked method in pawt not yet implemented");
		return false;
	}
	
	public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
		Log.w(_title, "Invoked method in pawt not yet implemented");
		return 0;
	}
	
	public int getTabSize() {
		Log.w(_title, "Invoked method in pawt not yet implemented");
		return 0;
	}
	
	public String getUIClassID() {
		Log.w(_title, "Invoked method in pawt not yet implemented");
		return "";
	}
	
	public boolean getWrapStyleWord() {
		Log.w(_title, "Invoked method in pawt not yet implemented");
		return false;
	}
	
	public void insert(String str, int pos) {
		String mytext = values.get(this);
		StringBuilder s = new StringBuilder(mytext);
		s.insert(pos, str);
		mytext=s.toString();
		setText(mytext);
	}
	
	protected String paramString() {
		return _title;
	}
	
	public void replaceRange(String str, int start, int end) {
		String mytext = values.get(this);
		StringBuilder s = new StringBuilder(mytext);
		s.replace(start, end, str);
		mytext=s.toString();
		setText(mytext);
	}
	
	public void setColumns(int columns) {
		_columns=columns;
	}
	
	public void setFont(Font f) {
		Log.w(_title, "Invoked method in pawt not yet implemented");
	}
	
	public void setLineWrap(boolean wrap) {
		Log.w(_title, "Invoked method in pawt not yet implemented");
	}
	
	public void setRows(int rows) {
		_rows=rows;
	}
	
	public void setTabSize(int size) {
		Log.w(_title, "Invoked method in pawt not yet implemented");
	}
	
	public void setWrapStyleWord(boolean word) {
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
    	this._root = root;
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
