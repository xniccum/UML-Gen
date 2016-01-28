package asm.StorageApi;

/**
 * Created by Steven on 1/4/2016.
 */
public interface IKlass extends IKlassPart {
    void setName(String name);
    String getName();
    void setVersion(int version);
    int getVersion();
    void setAccess(int access);
    int getAccess();
    void addKlassPart(IKlassPart part);
    void addAction(IAction action);

}
