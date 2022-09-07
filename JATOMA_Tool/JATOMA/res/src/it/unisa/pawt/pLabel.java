package it.unisa.pawt;

public class pLabel extends pComponent{
	
	ActionListener _actionListener;
	Font f;
	String text;
	
	public pLabel(String title){
		_title = title;
		_type = "pLabel";
		f = null;
		text=title;
	}
	
	public void addActionListener(ActionListener actionListener) {
		 this._actionListener = actionListener;
		
	}
	public void setFont(Font f) {
		this.f = f;
	}
	public Font getFont() {
		return f;
	}
	public void fire() {
		if(_actionListener != null){
			this._actionListener.actionPerformed(new ActionEvent(this, _title));
		}
	}

	public void setRoot(pComponent root){
		super.setRoot(root);
	}
	
	public void create() {
		parserHTML.addLabel(this, this.getRoot().getElement());
	}
	
	public void setText(String value) {
		text = value;
	}
	public String getText() {
		return text;
	}
	
	
	public void setVisible(boolean value) {
		
	}
	
}