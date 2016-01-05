package asm.impl;

import asm.api.IKlassPart;
import asm.api.ISuperKlass;

/**
 * Created by Steven on 1/4/2016.
 */
public class SuperKlass extends KlassDecorator implements ISuperKlass{
    private String superKlassName;
    private IKlassPart baseKlass;

    public SuperKlass(IKlassPart baseKlass, String superKlassName) {
        super(baseKlass);
        this.baseKlass = baseKlass;
        this.superKlassName = superKlassName;
    }

    @Override
    public String getSuperKlass() {
        return this.superKlassName;
    }

    @Override
    public String printEnd() {
        //TODO: check Filled
        return super.printEnd() + String.format("\n edge [ \n  arrowhead = \"filled\" \n \n %s -> $s \n", super.getBaseName() , superKlassName);
    }
}
