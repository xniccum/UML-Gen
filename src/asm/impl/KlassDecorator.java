package asm.impl;

import asm.api.IKlass;
import asm.api.IKlassPart;

/**
 * Created by Steven on 1/4/2016.
 */
public abstract class KlassDecorator {// extends IKlass implements IKlassPart{
    //public KlassDecorator(String name, int version, int access) {
    //    super(name, version, access);
    //}

    private IKlassPart baseKlass;
    private String baseKlassName;

    public KlassDecorator(IKlassPart baseKlass) {
        this.baseKlass = baseKlass;
        this.baseKlassName = baseKlass.getBaseName();
    }

    String getBaseName() {
        return this.baseKlassName;
    }

    String getAccessStringLevel(String accessLevel){
        switch (accessLevel){
            case "public":
                return "+";
            case "protected":
                return "#";
            case "private":
                return "-";
            default:
                return "";
        }
    }
}
