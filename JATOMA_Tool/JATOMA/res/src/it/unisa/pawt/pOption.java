package it.unisa.pawt;


public class pOption extends pComponent {
    String _value = "";

    public pOption(String value) {
        _value = value;
 
    }

    public void setRoot(pComponent root){

    	super.setRoot(root);
    }
    
    public void create(){
    	parserHTML.addpOption(_value);
    	
		
	}

}
