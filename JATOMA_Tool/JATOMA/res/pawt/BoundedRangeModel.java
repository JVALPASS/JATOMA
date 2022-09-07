package it.unisa.pawt;

import it.unisa.table.ChangeListener;

public interface BoundedRangeModel {

	public void addChangeListener(ChangeListener x);
	public int getExtent();
	public int getMaximum();
	public int getMinimum();
	public int getValue();
	public boolean getValueIsAdjusting();
	public void removeChangeListener(ChangeListener x);
	public void setExtent(int newExtent);
	public void setMaximum(int newMaximum);
	public void setMinimum(int newMinimum);
	public void setRangeProperties(int value, int extent, int min, int max, boolean adjusting);
	public void setValue(int newValue);
	public void setValueIsAdjusting(boolean b);
	
}
