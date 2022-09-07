package it.unisa.pawt;
import java.util.ArrayList;

/**
 * Created by ALFREDO on 25/02/2017.
 */

public class pMenuBar extends pComponent{

    ActionListener _actionListener;
    ArrayList<pMenu> array_menu = new ArrayList<pMenu>();


    public pMenuBar(){

    }


    public void addActionListener(ActionListener actionListener) {
        this._actionListener = actionListener;

    }

    public void fire() {
        if(_actionListener != null){
            this._actionListener.actionPerformed(new ActionEvent(this, _title));
        }
    }

    public void add(pMenu menu){
        array_menu.add(menu);
    }

    public ArrayList<pMenu> getArray_menu() {
        return array_menu;
    }
}
