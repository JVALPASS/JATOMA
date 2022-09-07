package it.unisa.pawt;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

class Repository {

    LinkedHashMap<String, pComponent> components = new LinkedHashMap<String, pComponent>();

    public void addComponent(String key, pComponent component) {
    	components.put(key, component);
    	
    }

    public  void click(String key) {
        pComponent component = components.get(key);
        if(component != null){
            component.fire();
        }
    }

    public void change(String key, String value) {
        pComponent component = components.get(key);
        if(component != null && component instanceof pTextField){
            pTextField f = (pTextField)component;
            f.setText(value);
        }
    }
}

public class pFrame extends pComponent{
	
	Repository rep = new Repository();
    ActionListener _actionListener;
    pMenuBar menu;

    public pFrame(String title){
    	_type = "pFrame";
        _title = title;
        menu = null;
        
        _nod = ComponentTree.getInstance().addChild(this);
        
    }

    public void setSize(int x,int y) {}
   
    public void setLayout(Object l){

    }
    public void setVisible(boolean b){}


    

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
           _nod.addChild(c);
    	}
        
    }


    public void setMenuBar(pMenuBar menuBar){

        this.menu = menuBar;
        parse_menu(menuBar.getArray_menu());
    }
    
    public void setJMenuBar(pMenuBar menuBar){

        this.menu = menuBar;
        parse_menu(menuBar.getArray_menu());
    }


    public void parse_menu(ArrayList<pMenu> bar){
        int i;

        for(i=0;i<bar.size();i++){

            this.add(bar.get(i));
            if(bar.get(i).has_item())
                parse_menu_item(bar.get(i).getArray_menu());
        }
    }


    private  void parse_menu_item(ArrayList<pMenuItem> pmenui){
        int i;
        for(i=0;i<pmenui.size();i++){
            this.add(pmenui.get(i));
            if(pmenui.get(i).has_item())
                parse_menu_item(pmenui.get(i).getArray_menu());
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
    
    public boolean hasMenu() {
    	if(menu==null)
    		return false;
    	else
    		return true;
    		
    }
    
    public ArrayList<pMenu> getMainBarra(){
    	return menu.getArray_menu();
    }
   
    
}
