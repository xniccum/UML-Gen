package asm.impl2.StandardDataObjects;

import asm.StorageApi.IInterphace;
import asm.StorageApi.IKlassPart;
import asm.impl2.KlassDecorator;

/**
 * Created by Steven on 1/4/2016.
 */
public class Interphace extends KlassDecorator implements IInterphace {
    private String[] interphaceNames;


    public Interphace(String[] interphaceNames) {
        super();
        this.interphaceNames = interphaceNames;
    }

    @Override
    public String[] getInterphase() {
        return this.interphaceNames;
    }
}