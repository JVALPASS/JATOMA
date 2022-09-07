package it.unisa.pawt;

public class ComponentTree {
	private static ComponentNode _tree;
	public static ComponentNode _root;
	
	public static ComponentNode getNewInstance(pComponent data) {
		_tree = new ComponentNode(data);
		return _tree;
	}
	
	public static ComponentNode getInstance(){
		if( _tree == null) {
			_tree = new ComponentNode();
		}
		
		return _tree;
	}
	
	public static ComponentNode getRoot(){
		return _root;
	}


	
}
