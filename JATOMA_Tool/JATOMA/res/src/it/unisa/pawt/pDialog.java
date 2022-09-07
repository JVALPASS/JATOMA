package it.unisa.pawt;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import org.w3c.dom.Element;
class RepositoryDialog{
	Hashtable<String, pComponent> compone= new Hashtable<String,pComponent>();
	
	public void addComponent(String key,pComponent c){
		compone.put(key, c);
	}
	
	public void click(String key){
		pComponent c=compone.get(key);
		if(c!=null){
			c.fire();
		}
	}
	public void change(String key,String value){
		pComponent c=compone.get(key);
		if(c!=null && c instanceof pTextField){
			pTextField f=(pTextField)c;
			f.setText(value);
		}
	}
}
public class pDialog extends pComponent {
	RepositoryDialog r= new  RepositoryDialog();
	ActionListener _actionListener;
	
	
	public pDialog(String title){
		_type = "pDialog";
		_title= title;
	  

		_nod=ComponentTree.getInstance().addChild(this);
		
		
	}
	
	public pDialog(pFrame f, String title) {
		this(title);
		f.add(this);
	}
	
	 

	public void setSize(int x,int y){
		
	}
	
	public void add(pComponent component){
		
		if(component instanceof pPanel) {
    		//c.setRoot(this);
    		ArrayList<pComponent> list = ((pPanel) component).getComponents();
    		for(int i=0; i<list.size(); i++) {
    			add(list.get(i));
    		}
    	}
		
		else {
			component.setRoot(this);
			r.addComponent(component._title, component);
			_nod.addChild(component);
			//System.out.println(component);
			//b.add(component);
		}
		
	}
	 public ArrayList<pComponent> getComponents(){
	        ArrayList<pComponent> components = new ArrayList<pComponent>();
	        Set<Entry<String, pComponent>> entrySet = r.compone.entrySet();
	        Iterator i = entrySet.iterator();

	        while(i.hasNext())
	            components.add(((Entry<String, pComponent>) i.next()).getValue());
	        return components;
	    }
	
	
	public void addActionListener(ActionListener actionListener){
		this._actionListener= actionListener;
	}

	public void fire() {
        if(_actionListener != null){
            this._actionListener.actionPerformed(new ActionEvent(this, this._title));
        }
    }
	public void fire(String dest){
		r.click(dest);
	}
	public void show(){
		setVisible(true);
	}
	public void close(){
		setVisible(false);
	}

	public void setVisible(boolean v){
		
	}
//
    
//
    public void change(String dest, String value) {
        r.change(dest,value);
    }
	public void setRoot(pComponent root){
		super.setRoot(root);
	}
	

	
	public void create(){
		System.out.println("Sono in pDialog create()");
		Enumeration<String>listaChiavi = r.compone.keys();
		for(int i=0; i<r.compone.size();i++){
			parserHTML.aggiungi(r.compone.get(listaChiavi.nextElement()));
		}
		Element dialog=parserHTML.addDialog(_title);
		
//		parserHTML.add_Component(dialog);
	}


	
	

	

	
	

}
