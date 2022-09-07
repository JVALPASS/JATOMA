package it.unisa.pawt;

import java.util.ArrayList;

public class ComponentNode {

	private pComponent _data;
	private ComponentNode _parent;
	private ArrayList<ComponentNode> _children = new ArrayList<ComponentNode>();
	
	public ComponentNode() {
		
	}

	public ComponentNode(pComponent data) {
		this._data = data;
	}

	public ComponentNode(ComponentNode parent, pComponent data) {
		this._data = data;
		this._parent = parent;
	}
	
	
	public void addChild(ComponentNode child){
		_children.add(child);
		child.setParent(this);
	}
	
	public ComponentNode addChild(pComponent data){
		ComponentNode node = new ComponentNode(data);
		addChild(node);
		return node;
	}

	public ComponentNode getParent() {
		return _parent;
	}

	public void setParent(ComponentNode parent) {
		this._parent = parent;
	}

	public pComponent getData() {
		return _data;
	}

	public ArrayList<ComponentNode> get_children() {
		return _children;
	}	
	
	public String toString() {
		String format= _data.toString() + "\n";
		for(ComponentNode node : _children) {
			format = format + " -- "+ node.toString() +"\n";
		}
		return format;
	}
	
}
