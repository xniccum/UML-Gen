package asm.impl2;

import asm.StorageApi.IKlass;
import asm.StorageApi.IKlassPart;
import asm.impl2.KlassDecorator;
import asm.visitorApi.ITraverser;
import asm.visitorApi.IVisitor;

import java.util.Collection;

/**
 * Created by Steven on 1/4/2016.
 */
public class Klass implements IKlass, ITraverser {
    private String name;
    private int version;
    private int access;

    private Collection<IKlassPart> klassParts;

    public Klass(String name){
        this.name = name;
        this.version = 1;
        this.access = -1;
    }

    public Klass(String name, int version, int access) {
        this.name = name;
        this.version = version;
        this.access = access;
    }
    //region IKlass methods
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
        return KlassDecorator.stripFilePath(this.name);
    }

    @Override
    public int getAccess() {
        return this.access;
    }
    //endregion


    @Override
    public void accept(IVisitor v) {
        v.preVisit(this);
        v.nameVisit(this);
        v.fieldVisit(this);
        v.methodVisit(this);
        v.postVisit(this);
    }
}
