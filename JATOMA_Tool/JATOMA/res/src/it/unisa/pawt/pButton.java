package it.unisa.pawt;


public class pButton extends pComponent{
	ActionListener _actionListener;
	String text;
	public pButton(String title){
		_type = "pButton";
		_title = title;
		text  = title;
	}
	
	public void addActionListener(ActionListener actionListener) {
		 this._actionListener = actionListener;
		
	}
	
	public void fire() {
		if(_actionListener != null){
			this._actionListener.actionPerformed(new ActionEvent(this, _title));
		}
	}

	public void setRoot(pComponent root){
		super.setRoot(root);
	}
	
	public void create( ) {
		parserHTML.addButton(this);
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}
	public void setLabel(String text) {
		this.text = text;
	}
	public String getLabel() {
		return text;
	}
}

