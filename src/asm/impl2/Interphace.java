package asm.impl2;

import asm.StorageApi.IInterphace;
import asm.StorageApi.IKlassPart;
import asm.impl2.KlassDecorator;

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
}