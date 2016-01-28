package asm.impl2;

import asm.StorageApi.IKlass;
import asm.StorageApi.IKlassPart;
import asm.impl2.KlassDecorator;
import asm.visitorApi.ITraverser;
import asm.visitorApi.IVisitor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by Steven on 1/4/2016.
 */
public class Klass implements IKlass, ITraverser {
    private String name;
    private int version;
    private int access;

    private Collection<IKlassPart> klassParts;

    public Klass(){
        this.name = "";
        this.version = 1;
        this.access = -1;
        this.klassParts = new HashSet<IKlassPart>();
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
    public void setName(String name) {
        this.name = name;
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
    public int getAccess() {
        return this.access;
    }
    //endregion

    @Override
    public void addKlassPart(IKlassPart part) {
        klassParts.add(part);
    }

    @Override
    public void accept(IVisitor v) {
        v.preVisit(this);
        for(IKlassPart part: klassParts){
            v.preVisit((ITraverser) part);
        }
        v.nameVisit(this);
        for(IKlassPart part: klassParts){
            v.nameVisit((ITraverser) part);
        }
        v.fieldVisit(this);
        for(IKlassPart part: klassParts){
            v.fieldVisit((ITraverser) part);
        }
        v.methodVisit(this);
        for(IKlassPart part: klassParts){
            v.methodVisit((ITraverser) part);
        }
        v.postVisit(this);
        for(IKlassPart part: klassParts){
            v.postVisit((ITraverser) part);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Klass)) return false;

        Klass klass = (Klass) o;

        if (getVersion() != klass.getVersion()) return false;
        if (getAccess() != klass.getAccess()) return false;
        return getName().equals(klass.getName());

    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getVersion();
        result = 31 * result + getAccess();
        return result;
    }
}
