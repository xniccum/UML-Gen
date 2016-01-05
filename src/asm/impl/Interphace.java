package asm.impl;

import asm.api.IInterphace;
import asm.api.IKlassPart;

/**
 * Created by Steven on 1/4/2016.
 */
public class Interphace extends KlassDecorator implements IInterphace {
    private String[] interphaceNames;
    private IKlassPart baseKlass;
    //private String baseClassName;

    public Interphace(String[] interphaceNames, IKlassPart baseKlass) {
        super(baseKlass);
        this.interphaceNames = interphaceNames;
        this.baseKlass = baseKlass;
        //this.baseClassName = baseKlass.getBaseName();
    }

    // this.baseClassName = baseKlass.getBaseName();

    @Override
    public String getBaseName() {
        return super.getBaseName();
    }

    @Override
    public String[] getInterphase() {
        return this.interphaceNames;
    }

    @Override
    public String printBefore() {
        return "";
    }

    @Override
    public String printMiddle() {
        return "";
    }

    @Override
    public String printEnd() {
        //TODO: check empty
        StringBuilder returnString = new StringBuilder();
        returnString.append(" edge [ \n arrowhead = \"empty\" \n \n ");
        for(String interphaceName : this.interphaceNames) {
           returnString.append(String.format(" %s -> $s \n", super.getBaseName(), interphaceName));
        }
        return returnString.toString();
    }
}