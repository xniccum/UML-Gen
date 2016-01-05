package asm.impl;

import asm.api.IInterphace;
import asm.api.IKlassPart;

/**
 * Created by Steven on 1/4/2016.
 */
public class Interphace extends KlassDecorator implements IInterphace {
    private String[] interphaceNames;
    private IKlassPart baseKlass;

    public Interphace(String[] interphaceNames, IKlassPart baseKlass) {
        super(baseKlass);
        this.interphaceNames = interphaceNames;
        this.baseKlass = baseKlass;
    }

    @Override
    public String[] getInterphase() {
        return this.interphaceNames;
    }


    @Override
    public String printEnd() {
        //TODO: check empty
        StringBuilder returnString = new StringBuilder();
        returnString.append(super.printEnd());
        returnString.append(" edge [ \n arrowhead = \"empty\" \n \n ");
        for(String interphaceName : this.interphaceNames) {
           returnString.append(String.format(" %s -> $s \n", super.getBaseName(), interphaceName));
        }
        return returnString.toString();
    }
}