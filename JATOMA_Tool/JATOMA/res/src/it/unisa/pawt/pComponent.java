package it.unisa.pawt;

import org.w3c.dom.Element;

public abstract class pComponent {
	
	private Element _node;
	public String _title;
	public String _type;
	private pComponent _root;
	ComponentNode _nod;
	public int _max;
	public int _min;
	
	public void create() {}	
    
	public void addActionListener(ActionListener actionListener) {}

	public void fire() {}
	
	public void fire(String dest){}

	//nuovi metodi aggiunti,
    public void setElement(Element node) { _node = node;}
    public Element getElement() { return _node;}
	public void setRoot(pComponent root){ _root = root;}
    public pComponent getRoot(){ return _root;}
	public void requestFocus() {}
	public String getName(){ return _title; }
	public void setName(String name){_title = name; }
	public void setBackground(Color c) {}
	
	
	public String toString() {
		return _type+"(\""+this.getName()+"\")";
	}
}
