package it.unisa.pawt;

public class pScrollPane extends pComponent {
	
	static pComponent _component;
	
	public pScrollPane() {
		_type = "pScrollPane";
	}
	
	public pScrollPane(pComponent c) {
		_component = c;
		_type = "pScrollPane";
	}
	
	public pScrollPane(pComponent c, int vsbPolicy, int hsbPolicy) {
		this(c);
	}
	
	public pScrollPane(int vsbPolicy, int hsbPolicy) {
		this();
	}
	
	public pComponent getComponent() {
		return _component;
	}

}
