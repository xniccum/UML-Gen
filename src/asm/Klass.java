package asm;

/**
 * Created by Steven on 1/4/2016.
 */
public class Klass {
    private String name;
    private int version;
    private int access;
    private String signature;
    private String abstractClass;
    private String[] interfaces;

    public Klass(String name, int version, int access, String signature, String abstractClass, String[] interfaces) {
        this.name = name;
        this.version = version;
        this.access = access;
        this.signature = signature;
        this.abstractClass = abstractClass;
        this.interfaces = interfaces;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getAbstractClass() {
        return abstractClass;
    }

    public void setAbstractClass(String abstractClass) {
        this.abstractClass = abstractClass;
    }

    public String[] getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(String[] interfaces) {
        this.interfaces = interfaces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Klass)) return false;

        Klass klass = (Klass) o;

        return getName().equals(klass.getName());

    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }
}
