package it.unisa.pawt;


public class pCheckBox extends pComponent {
    ActionListener _actionListener;
    boolean _flag = false;


    public pCheckBox(String title) {
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
        if (!_flag)
            _flag = true;
        else
            _flag = false;

        if (_actionListener != null) {
            this._actionListener.actionPerformed(new ActionEvent(this, _title));
        }
    }

    public void setRoot(pComponent root){

        super.setRoot(root);
    }
    
    public void create() {
		parserHTML.addCheckBox(_title);
	}

}
