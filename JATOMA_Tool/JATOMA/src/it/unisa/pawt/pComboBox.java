package it.unisa.pawt;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import org.w3c.dom.Element;

public class pComboBox extends pComponent {
    String _value = "";
    ActionListener _actionListener;
    ArrayList<pOption>po=new ArrayList<pOption>();

    public pComboBox(String title) {
        _title = title;
    }
    
    public pComboBox() {
    	this("combobox");
    }

    public void addActionListener(ActionListener actionListener) {
        this._actionListener = actionListener;

    }

    public String getSelectedItem() {
        return _value;
    }

    public void addItem(String title) {
    	pOption o = new pOption(title);
    	po.add(o);
    	
    	
    	
    }

    public void fire(String value) {
        _value = value;
        if (_actionListener != null) {
            this._actionListener.actionPerformed(new ActionEvent(this, _title));
        }

    }
    
    public void setRoot(pComponent root){

    	super.setRoot(root);
    }
    
    public ArrayList<pOption> getComponents(){
    	return po;
    }
    
    public void create() {
for(int i=0;i<po.size();i++){
			
    		parserHTML.add_opzione(po.get(i));

    	}
		Element comboBox = parserHTML.addComboBox(_title);
		
		
		
	}
}
