package it.unisa.pawt;

import java.util.ArrayList;

/**
 * Created by ALFREDO on 25/02/2017.
 */

public class pMenuBar extends pComponent{

    
    private ArrayList<pMenu> array_menu = new ArrayList<pMenu>();

    public pMenuBar(){
    	_title="menubar";
    }

    public void add(pMenu menu){
        array_menu.add(menu);
    }

    public ArrayList<pMenu> getArray_menu() {
        return array_menu;
    }
   
    public void create() {
    	parserHTML.addmenu(array_menu);
    }
    
}
