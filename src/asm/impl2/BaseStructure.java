package asm.impl2;

import asm.api.IKlassPart;

/**
 * Created by Steven on 1/12/2016.
 */
public class BaseStructure implements IKlassPart{
    private String baseName;

    public BaseStructure(){

    }

    public BaseStructure(String baseName) {
        this.baseName = baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    @Override
    public String getBaseName() {
        return baseName == null? "" : baseName;
    }

    @Override
    public String printBefore() {
        return "";
    }

    @Override
    public String printNameBlock() {
        return null;
    }

    @Override
    public String printFieldBlock() {
        return null;
    }

    @Override
    public String printMethodBlock() {
        return null;
    }

    @Override
    public String printEnd() {
        return null;
    }
}
