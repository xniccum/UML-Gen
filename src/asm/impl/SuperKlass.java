package asm.impl;

import asm.api.IKlass;
import asm.api.IKlassPart;
import asm.api.ISuperKlass;

/**
 * Created by Steven on 1/4/2016.
 */
public class SuperKlass implements ISuperKlass{
    private String superKlassName;
    private IKlassPart baseKlass;
    private String baseClassName;

    public SuperKlass(IKlassPart baseKlass, String superKlassName) {
        this.baseKlass = baseKlass;
        this.superKlassName = superKlassName;
        this.baseClassName = baseKlass.getBaseName();
    }

    @Override
    public String getBaseName() {
        return this.baseClassName;
    }

    @Override
    public String getSuperKlass() {
        return this.superKlassName;
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
        //TODO: check Solid
        return String.format(" edge [ \n  arrowhead = \"solid\" \n \n %s -> $s \n", baseClassName , superKlassName);
    }
}
