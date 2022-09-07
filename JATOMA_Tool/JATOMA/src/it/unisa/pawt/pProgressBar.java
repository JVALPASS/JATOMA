package it.unisa.pawt;

import javax.accessibility.AccessibleContext;
import it.unisa.javada.Utils;
import it.unisa.table.ChangeListener;

/**
 * Created by CARMINE on 01/09/2018.
 */

public class pProgressBar extends pComponent{
	
	int _value;
	int _orientation=0; //by default SwingConstants.HORIZONTAL
	static int count = 1; //to avoid same id title
	
	public pProgressBar() {
		this(0, 100, 0);
	}
	
	public pProgressBar(BoundedRangeModel newModel) {
		this(0, 100, 0);
	}
	
	public pProgressBar(int min, int max) {
		this(min, max, 0);
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
	}	
	
	public void addChangeListener(ChangeListener l) {
		Utils.parsingWarning();
	}
	
	protected ChangeListener createChangeListener() {
		Utils.parsingWarning();
		return null;
	}

	protected void fireStateChanged() {
		Utils.parsingWarning();
	}
	
	public AccessibleContext getAccessibleContext() {
		Utils.parsingWarning();
		return null;
	}
	
	public ChangeListener[] getChangeListeners() {
		Utils.parsingWarning();
		return null;
	}
	
	public void setValue(int value) {
		_value = value;
	}
	
	public int getValue() {
		return _value;
	}
	
	public int getMinimum() {
		return _min;
	}
	
	public int getMaximum() {
		return _max;
	}
	
	public BoundedRangeModel getModel() {
		Utils.parsingWarning();
		return null;
	}
	
	public int getOrientation() {
		return _orientation;
	}
	
	public double getPercentComplete() {
		Utils.parsingWarning();
		return 0;
	}
	
	public String getString() {
		return _value+"%";
	}
	
	public ProgressBarUI getUI() {
		Utils.parsingWarning();
		return null;
	}
	
	public String getUIClassID() {
		Utils.parsingWarning();
		return "";
	}
	
	public boolean isBorderPainted() {
		Utils.parsingWarning();
		return false; //by default
	}
	
	public boolean isIndeterminate() {
		Utils.parsingWarning();
		return false; //by default
	}
	
	public boolean isStringPainted() {
		Utils.parsingWarning();
		return false; //by default
	}
	
	protected void paintBorder(Graphics g) {
		Utils.parsingWarning();
	}
	
	protected String paramString() {
		Utils.parsingWarning();
		return "";
	}
	
	public void removeChangeListener(ChangeListener l) {
		Utils.parsingWarning();
	}
	
	public void setBorderPainted(boolean b) {
		Utils.parsingWarning();
	}
	
	public void setIndeterminate(boolean newValue) {
		Utils.parsingWarning();
	}
	
	public void setMaximum(int n) {
		_max=n;
	}
	
	public void setMinimum(int n) {
		_min=n;
	}
	
	public void setModel(BoundedRangeModel newModel) {
		Utils.parsingWarning();
	}
	
	public void setOrientation(int newOrientation) {
		_orientation=newOrientation;
	}
	
	public void setString(String s) {
		Utils.parsingWarning();
	}
	
	public void setUI(ProgressBarUI ui) {
		Utils.parsingWarning();
	}
	
	public void updateUI() {
		Utils.parsingWarning();
	}
	
	public void setVisible(boolean b){
    	
    }
	
	public void setStringPainted(boolean b) {
		Utils.parsingWarning();
	}
	
	protected int getCount() {
		return count++;
	}

	public void create() {
		parserHTML.addProgressBar(_title, _min, _max, _orientation);
	}
	
}
