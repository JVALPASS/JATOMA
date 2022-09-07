package it.unisa.pawt;

public class pPanel extends pFrame{
    pFrame frame;

    public pPanel(String title){

        super("Panel"+Math.random()+title);
    }

    public pPanel(){

        super("Panel"+Math.random());
    }
}
