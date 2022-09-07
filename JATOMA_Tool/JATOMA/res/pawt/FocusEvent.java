package it.unisa.pawt;

import java.util.EventObject;


public class FocusEvent extends EventObject {
	private String dest;

	public FocusEvent(pComponent source, String dest) {
		super(source);
		this.dest = dest;
	}

	public String getDest() {
		return dest;
	}


}
