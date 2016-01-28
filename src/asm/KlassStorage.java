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
    private final String className;
    private final int version;
    private final int access;


    public KlassStorage(String className, int version, int access) {
        this.currentPart = new Klass(className, version, access);
        this.className = className;
        this.version = version;
        this.access = access;
        Collections.shuffle(new ArrayList<>());
    }

    public IKlassPart getCurrentPart() {
        return currentPart;
    }

    public void setCurrentPart(IKlassPart currentPart) {
        this.currentPart = currentPart;
    }

    public String getClassName() {
        return className;
    }

    public int getVersion() {
        return version;
    }

    public int getAccess() {
        return access;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KlassStorage)) return false;
        KlassStorage that = (KlassStorage) o;

        if (getVersion() != that.getVersion()) return false;
        if (getAccess() != that.getAccess()) return false;
        return getClassName() != null ? getClassName().equals(that.getClassName()) : that.getClassName() == null;

    }

    @Override
    public int hashCode() {
        int result = getClassName() != null ? getClassName().hashCode() : 0;
        result = 31 * result + getVersion();
        result = 31 * result + getAccess();
        return result;
    }
}
