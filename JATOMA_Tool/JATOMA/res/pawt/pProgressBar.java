package it.unisa.pawt;

import java.util.LinkedHashMap;
import android.util.Log;
import it.unisa.javada.Utils;
import it.unisa.table.ChangeListener;
import org.apache.cordova.CordovaWebView;
import java.util.LinkedHashMap;

/**
 * Created by CARMINE on 01/09/2018.
 */

public class pProgressBar extends pComponent{
	
	int _value;
	int _orientation=0; //by default SwingConstants.HORIZONTAL
	
	static LinkedHashMap<pProgressBar, Integer> values= new LinkedHashMap<pProgressBar, Integer>();
	static LinkedHashMap<pProgressBar, Boolean> visibility= new LinkedHashMap<pProgressBar, Boolean>();
	static int count = 1;
	
	public pProgressBar() {
		this(0, 100, 0);
	}
	
	public pProgressBar(int min, int max) {
		this(min, max, 0);
	}
	
	public pProgressBar(BoundedRangeModel newModel) {
		this(0, 100, 0);
	}
	
	public pProgressBar(int orient) {
		this(0, 100, orient);
	}
	
	public pProgressBar(int min, int max, int orient) {
		_min = min;
		_max = max;
		_orientation=orient;
		_type = "pProgressBar";
		_title = "progressbar"+getCount();
		values.put(this,min);
		visibility.put(this, true);
	}	
	
	public void addChangeListener(ChangeListener l) {
		Log.w(_title, "Invoked method in pawt not yet implemented");
	}
	
	protected ChangeListener createChangeListener() {
		Log.w(_title, "Invoked method in pawt not yet implemented");
		return null;
	}

	protected void fireStateChanged() {
		Log.w(_title, "Invoked method in pawt not yet implemented");
	}
	
	public ChangeListener[] getChangeListeners() {
		Log.w(_title, "Invoked method in pawt not yet implemented");
		return null;
	}
	
	public BoundedRangeModel getModel() {
		Log.w(_title, "Invoked method in pawt not yet implemented");
		return null;
	}
	
	public int getOrientation() {
		return _orientation;
	}
	
	public double getPercentComplete() {
		Log.w(_title, "Invoked method in pawt not yet implemented");
		return 0;
	}
	
	public String getString() {
		return values.get(this)+"%";
	}
	
	public ProgressBarUI getUI() {
		Log.w(_title, "Invoked method in pawt not yet implemented");
		return null;
	}
	
	public String getUIClassID() {
		Log.w(_title, "Invoked method in pawt not yet implemented");
		return "";
	}
	
	public boolean isBorderPainted() {
		Log.w(_title, "Invoked method in pawt not yet implemented");
		return false; //by default
	}
	
	public boolean isIndeterminate() {
		Log.w(_title, "Invoked method in pawt not yet implemented");
		return false; //by default
	}
	
	public boolean isStringPainted() {
		Log.w(_title, "Invoked method in pawt not yet implemented");
		return false; //by default
	}
	
	protected void paintBorder(Graphics g) {
		Log.w(_title, "Invoked method in pawt not yet implemented");
	}
	
	protected String paramString() {
		Log.w(_title, "Invoked method in pawt not yet implemented");
		return "";
	}
	
	public void removeChangeListener(ChangeListener l) {
		Log.w(_title, "Invoked method in pawt not yet implemented");
	}
	
	public void setBorderPainted(boolean b) {
		Log.w(_title, "Invoked method in pawt not yet implemented");
	}
	
	public void setIndeterminate(boolean newValue) {
		Log.w(_title, "Invoked method in pawt not yet implemented");
	}
	
	public void setValue(int value) {
		values.put(this,value);
		try {
			CordovaWebView wv = Constants.view;
			if(wv != null){
				wv.sendJavascript("app.setta('"+_title+"','"+values.get(this)+"')");

			}
		} catch(Exception e) {}

	}
	
	public int getValue() {
		return values.get(this);
	}
	
	public int getMaximum() {
		return _max;
	}
	
	public int getMinimum() {
		return _min;
	}
	
	public void setMaximum(int n) {
		_max=n;
		try {
			CordovaWebView wv = Constants.view;
			if(wv != null){
				wv.sendJavascript("app.setProgressMax('"+_title+"','"+_max+"')");

			}
		} catch(Exception e) {}
	}
	
	public void setMinimum(int n) {
		_min=n;
	}
	
	public void setModel(BoundedRangeModel newModel) {
		Log.w(_title, "Invoked method in pawt not yet implemented");
	}
	
	public void setOrientation(int newOrientation) {
		Log.w(_title, "Invoked method in pawt not yet implemented");
	}
	
	public void setString(String s) {
		Log.w(_title, "Invoked method in pawt not yet implemented");
	}
	
	public void setUI(ProgressBarUI ui) {
		Log.w(_title, "Invoked method in pawt not yet implemented");
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
  
	
	public void setStringPainted(boolean b) {
		Log.w(_title, "Invoked method in pawt not yet implemented");
	}
	
	protected int getCount() {
		return count++;
	}
	
}
