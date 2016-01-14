package asm.impl2.Methodimp;

import asm.StorageApi.MethodStorage.IMethodUsedKlass;

/**
 * Created by Steven on 1/13/2016.
 */
public class MethodUsedKlass extends MethodDecorator implements IMethodUsedKlass  {
    private String usedKlassName;



    public MethodUsedKlass(String usedKlassName) {
        this.usedKlassName = usedKlassName;
    }

    @Override
    public String getClassName() {
        return usedKlassName;
    }

}
