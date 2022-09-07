package it.unisa.pawt;


import java.util.ArrayList;
import android.util.Log;

public class pComboBox extends pComponent {
    String _value = "";
    ActionListener _actionListener;
    ArrayList<pOption> po=new ArrayList<pOption>();

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
    	Log.d("COMBOBOX VALUE", _value);
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

    public ArrayList<pOption> getComponents(){
        return po;
    }

    public void setRoot(pComponent root){

        this._root = root;
    }
}
