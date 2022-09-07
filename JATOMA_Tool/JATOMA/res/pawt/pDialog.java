package it.unisa.pawt;

import android.util.Log;

import org.apache.cordova.CordovaWebView;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

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
	pFrame frame;
	
	public pDialog(String title){
		_type = "pDialog";
		_title= title;
		frame = null;

		_nod=ComponentTree.getInstance().addChild(this);
		
		
	}
	
	public pDialog(pFrame f, String title) {
		this(title);
		if(f!=null) {
			f.add(this);	
			frame = f;
		}
	}
	
	 

	public void setSize(int x,int y){
		
	}
	
	public void add(pComponent component){
		
		frame.add(component);
		System.out.println(component.getName() + " add to dialog parent "+ this.getName());
		r.addComponent(component._title, component);
		_nod.addChild(component);
//				parserHTML.aggiungi(component);
		System.out.println(component);
//		b.add(component);	
		
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
//	public void add(pComponent c) {
//        c.setRoot(this);
//        System.out.println(c.getName() + " add to frame parent "+ this.getName());
//        r.addComponent(c._title, c );
//       
//
//    }
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
		try {
			CordovaWebView wv = Constants.view;
			if(wv != null){
				if(v) {
					wv.sendJavascript("app.showDialog('" + _title + "')");
					Log.d("CCC", "CCC");
				}
				else{
					wv.sendJavascript("app.closeDialog('" + _title + "')");
					Log.d("CCC", "CCC");
				}

			}
		} catch(Exception e) {}
		
	}
//
    
//
    public void change(String dest, String value) {
        r.change(dest,value);
    }
//	public void setRoot(pComponent root){
//		super.setRoot(root);
//	}
	


}
