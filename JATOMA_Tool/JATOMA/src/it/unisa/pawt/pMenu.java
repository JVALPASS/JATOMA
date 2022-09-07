package it.unisa.pawt;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by ALFREDO on 25/02/2017.
 */


public class pMenu extends pComponent {

    ArrayList<pMenuItem> array_menu = new ArrayList<pMenuItem>();
    String text;
    public pMenu(String title){
        _title = title;
        _type = "pMenu";
        text = title;
    }

    public void add(pMenuItem pMenuItem){
        array_menu.add(pMenuItem);
    }

    public ArrayList<pMenuItem> getArray_menu() {
        return array_menu;
    }
    
    public pMenuItem getItem(int i) {
    	return array_menu.get(i);
    }

    public boolean has_item(){

        return !array_menu.isEmpty();
    }
    public String getText() {
    	return text;
    }
    public void setText(String text) {
    	this.text = text;
    }
}