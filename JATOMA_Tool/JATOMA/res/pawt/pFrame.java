package it.unisa.pawt;

import android.util.Log;
import it.unisa.run.AWTPlugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;

import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.lang.reflect.*;

class Repository {

    Hashtable<String, pComponent> components = new Hashtable<String, pComponent>();
    pTextField temp;
    public void addComponent(String key, pComponent component) {
        components.put(key, component);
    }

    public void click(String key) {
        pComponent component = find(key);
        Log.d("awtrun", "CLICK: " + component._title);
        if (component != null && (component instanceof pButton || component instanceof pRadioButton || component instanceof pCheckBox || component instanceof pComboBox) || component instanceof pMenuItem) {
            if (temp != null) {
                temp.focusLost();
                temp = null;
            }
            component.fire();
        }
        if (component != null && component instanceof pTextField) {
        	pTextField f = (pTextField) component;
            if(temp!=null){
                temp.focusLost();
            }
            if(f.focusGained()) {
                temp = f;
            }
        }
    }

    public void click(String key, String value) {
        pComponent component = find(key);
        if (component != null) {
            if (temp != null) {
                temp.focusLost();
                temp = null;
            }
            component.fire(value);
        }
    }

    private pComponent find(String key){
        Set<Entry<String, pComponent>> entrySet = components.entrySet();
        Iterator iterator = entrySet.iterator();
        while(iterator.hasNext()){
            Entry<String, pComponent> entry = (Entry<String, pComponent>) iterator.next();
            if(entry.getValue() instanceof pPanel){
                pPanel panel = (pPanel) entry.getValue();
                pComponent toReturn = panel.rep.find(key);
                if(toReturn != null) {
                    return toReturn;
                }
            } else if(entry.getKey().equals(key)){
                return entry.getValue();
            }
        }
        return null;
    }

    public void change(String key, String value) {
        pComponent component = find(key);
        if(component != null && component instanceof pTextField){
            pTextField f = (pTextField)component;
            f.setText(value);
        }
        else if(component != null && component instanceof pPasswordField){
            pPasswordField f = (pPasswordField)component;
            f.setText(value);
        }
        else if(component != null && component instanceof pTextArea){
            pTextArea f = (pTextArea)component;
            f.setText(value);
        }
    }

    public void fireRow(String key, String posRow) {
        pComponent component = find(key);
        if(component != null && component instanceof pTable){
            pTable t = (pTable)component;
            if(t.getRowSelectionAllowed()){
                t.setRowSelectionInterval(Integer.parseInt(posRow), Integer.parseInt(posRow));
            }
        }
    }



    public void fireCell(String key, String posRow, String posColumn) {
        pComponent component = find(key);
        if(component != null && component instanceof pTable){
            pTable t = (pTable)component;
            if(t.isCellEditable(Integer.parseInt(posRow), Integer.parseInt(posColumn))){
                t.editCellAt(Integer.parseInt(posRow), Integer.parseInt(posColumn));
            }
        }
    }

    public void change(String key, String posRow, String posColumn, String value) {
        pComponent component = find(key);
        if(component != null && component instanceof pTable){
            pTable t = (pTable)component;
            t.getModel().setValueAt(value, Integer.parseInt(posRow),(Integer.parseInt(posColumn)));
        }
    }
}

public class  pFrame extends pComponent{
    Repository rep = new Repository();
    ActionListener _actionListener;
    pMenuBar menu;

    public pFrame(String title){
        _title = title;
        menu = null;


    }

    /*public void setTitle(String title){
        _title = title;
    }*/

    public void setSize(int x,int y) {}

    public void add(pComponent c) {
    	if(c instanceof pPanel) {
    		//c.setRoot(this);
    		ArrayList<pComponent> list = ((pPanel) c).getComponents();
    		for(int i=0; i<list.size(); i++) {
    			add(list.get(i));
    		}
    	}
    	
    	else if(c instanceof pScrollPane) {
    		add(((pScrollPane) c).getComponent());
    	}
    	else {
        c.setRoot(this);
        rep.addComponent(c._title, c );
    	}
    }

    public void setMenuBar(pMenuBar menuBar){

        this.menu = menuBar;
        add_pMenu(menuBar.getArray_menu());
    }

    public void setJMenuBar(pMenuBar menuBar){

        this.menu = menuBar;
        add_pMenu(menuBar.getArray_menu());
    }

    public void add_pMenu(ArrayList<pMenu> bar){
        int i;

        for(i=0;i<bar.size();i++){
            this.add(bar.get(i));
            if(bar.get(i).has_item()) //se il pMenu ha dei sottomenu
                add_pMenuItem(bar.get(i).getArray_menu());
        }
    }

    private  void add_pMenuItem(ArrayList<pMenuItem> pmenui){
        int i;
        for(i=0;i<pmenui.size();i++){
            this.add(pmenui.get(i));
            if(pmenui.get(i).has_item()) //se il pMenuItem ha dei sottomenu
                add_pMenuItem(pmenui.get(i).getArray_menu());
        }
    }

    public ArrayList<pComponent> getComponents(){
        ArrayList<pComponent> components = new ArrayList<pComponent>();
        Set<Entry<String, pComponent>> entrySet = rep.components.entrySet();
        Iterator i = entrySet.iterator();

        while(i.hasNext())
            components.add(((Entry<String, pComponent>) i.next()).getValue());
        return components;
    }

    public void addActionListener(ActionListener actionListener) {
        this._actionListener = actionListener;
    }

    public void fire() {
        if(_actionListener != null){
            this._actionListener.actionPerformed(new ActionEvent(this, this._title));
        }
    }

    public void fire(String dest) {
        rep.click(dest);
    }

    public void change(String dest, String value) {
        rep.change(dest,value);
    }

    public void fire(String dest, String value) {
        rep.click(dest, value);
    }

    public void setLayout(Object l){

    }

    public void reload(){
        ArrayList<pComponent> p=this.getComponents();
        for(int i=0; i<p.size();i++) {
            if (p.get(i).getClass() != null && p.get(i).getClass() == pTextField.class) {
                pTextField a = (pTextField) p.get(i);
                Log.d("awtrun", a.getText());
                a.setText(a.getText());
                a.setVisible(a.getVisible());
            }

            else if (p.get(i).getClass() != null && p.get(i).getClass() == pButton.class) {
                pButton a = (pButton) p.get(i);
                a.setVisible(a.getVisible());
            }
        }
    }

    public void setVisible(boolean b) {
            if(b==true){

                AWTPlugin a = AWTPlugin.getInstance();
                a.setFrame(this);
            }
    }

    public void fireRow(String id, String row) {
        rep.fireRow(id,row);

    }

    public void fireCell(String id, String row, String column) {
        rep.fireCell(id,row, column);

    }

    public void change(String id, String row, String column, String value) {
        rep.change(id, row, column, value);
    }

}