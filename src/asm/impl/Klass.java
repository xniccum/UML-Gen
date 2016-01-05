package asm.impl;

import asm.api.IKlass;
import asm.api.IKlassPart;

import java.util.Collection;

/**
 * Created by Steven on 1/4/2016.
 */
public class Klass implements IKlass {
    private String name;
    private int version;
    private int access;

    private Collection<IKlassPart> klassParts;

    public Klass(String name, int version, int access) {
        this.name = name;
        this.version = version;
        this.access = access;
    }

    @Override
    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public void setAccess(int access) {
        this.access = access;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getVersion() {
        return this.version;
    }

    @Override
    public String getBaseName() {
        return this.name;
    }

    @Override
    public int getAccess() {
        return this.access;
    }

    @Override
    public String printBefore() {
        return "";
    }

    @Override
    public String printNameBlock() {
        String[] nameTemp = this.name.split("/");
        return String.format("%s [ \n label = \" { %s |", nameTemp[nameTemp.length -1], nameTemp[nameTemp.length -1]);
    }

    @Override
    public String printFieldBlock() {
        return "|";
    }

    @Override
    public String printMethodBlock() {
        return "\\l|";
    }

    @Override
    public String printEnd() {
        return String.format(" \n } \" \n ]");
    }
}
