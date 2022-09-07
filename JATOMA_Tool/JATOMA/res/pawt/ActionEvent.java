package it.unisa.pawt;
import java.util.EventObject;


// contiene una variabile di istanza che memorizza anche la destinazione dell'evento(quale bottone cliccare)
public class ActionEvent extends EventObject {
	private String dest;
	
	public ActionEvent(pComponent source, String dest) {
		super(source);
		this.dest=dest;
	}
	
	public String getDest() {

		return dest;
	}
	

}
