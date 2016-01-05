package asm;

import asm.api.IKlassPart;

/**
 * Created by Steven on 1/5/2016.
 */
public class KlassStorage {
    private IKlassPart currentPart;

    public KlassStorage() {
        this.currentPart = null;
    }

    public IKlassPart getCurrentPart() {
        return currentPart;
    }

    public void setCurrentPart(IKlassPart currentPart) {
        this.currentPart = currentPart;
    }
}
