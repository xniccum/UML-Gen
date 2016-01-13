package asm.impl2;

import asm.StorageApi.IKlassPart;
import asm.StorageApi.ISuperKlass;
import asm.impl2.KlassDecorator;
import asm.visitorApi.ITraverser;

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
}
