package asm;

import asm.StorageApi.IKlassPart;
import asm.impl2.Klass;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by Steven on 1/5/2016.
 */
public class KlassStorage {
    private IKlassPart currentPart;

    public KlassStorage(String className, int version, int access) {
        this.currentPart = new Klass(className, version, access);
        Collections.shuffle(new ArrayList<>());
    }

    public KlassStorage(){
        this.currentPart = null;
    }

    public IKlassPart getCurrentPart() {
        return currentPart;
    }

    public void setCurrentPart(IKlassPart currentPart) {
        this.currentPart = currentPart;
    }

}
