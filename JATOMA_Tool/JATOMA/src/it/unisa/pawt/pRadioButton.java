package it.unisa.pawt;


public class pRadioButton extends pComponent {
    ActionListener _actionListener;
    boolean _flag = false;


    public pRadioButton(String title) {
        _title = title;
    }


    public void addActionListener(ActionListener actionListener) {
        this._actionListener = actionListener;

    }

    public boolean isSelected() {
        return _flag;

    }

    public void setSelected(boolean flag) {

    }

    public void fire() {
        _flag = true;
        if (_actionListener != null) {
            this._actionListener.actionPerformed(new ActionEvent(this, _title));
        }
        _flag = false;
    }
    
    public void setRoot(pComponent root){
    	super.setRoot(root);
	}
    
    public void create() {
        parserHTML.addRadioButton(_title);
    }
    
}
