package asm.impl2.StandardDataObjects;

import asm.StorageApi.IKlassPart;
import asm.StorageApi.ISuperKlass;
import asm.impl2.KlassDecorator;
import asm.visitorApi.ITraverser;

/**
 * Created by Steven on 1/4/2016.
 */
public class SuperKlass extends KlassDecorator implements ISuperKlass{
    private String superKlassName;

    public SuperKlass( String superKlassName) {
        super();
        this.superKlassName = superKlassName;
    }

    @Override
    public String getSuperKlass() {
        return this.superKlassName;
    }
}
