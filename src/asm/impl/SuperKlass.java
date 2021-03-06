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
        return (super.printEnd() + String.format("\n edge [ \n  style=\"solid\", arrowhead = \"normal\" \n ] \n %s -> %s \n", super.stripFilePath(super.getBaseName()) , stripFilePath(this.superKlassName)));
    }
}
