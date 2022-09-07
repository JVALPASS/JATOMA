package it.unisa.pawt;

public class pTextField extends pComponent {
	String _value;

	Integer _size;
	FocusListener _focusListener;

    public pTextField(String title, Integer size){
		_title = title;
		_size = size;
		_type = "pTextField";
		_value = title;
	}
    
    public String getText() {
		return _value;	
		
	}
    
    public void setVisible(boolean b){
    	
    }
    
	public void setText(String value) {
		_value = value;
		/*if(!value.equals(_value)) {
			_value = value;
				try {
					CordovaWebView wv = Constants.view;
					if(wv != null){
						wv.sendJavascript("app.setta('"+_title+"','"+_value+"')");
				//Log.d("sono in if", "appsetta : " + _title);
						}	
				} catch(Exception e) {}
			}	*/
		
		 
	}
	
    public void setRoot(pComponent root){
    	super.setRoot(root);
    }
    
    public void create(){
		parserHTML.addTextField(this, this.getRoot().getElement());
	}

	public void addFocusListener(FocusListener actionListener) {
		this._focusListener = actionListener;

	}

	public boolean focusGained() {
		if (_focusListener != null) {
			this._focusListener.focusGained(new FocusEvent(this, _title));
			return true;
		}
		return false;
	}

	public void focusLost() {
		if (_focusListener != null) {
			this._focusListener.focusLost(new FocusEvent(this, _title));
		}
	}

}

