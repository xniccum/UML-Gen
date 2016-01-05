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

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append(currentPart.printBefore());
        string.append(currentPart.printNameBlock());
        string.append(currentPart.printFieldBlock());
        string.append(currentPart.printMethodBlock());
        string.append(currentPart.printEnd());
        return string.toString();
    }
}
