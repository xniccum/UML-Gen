package asm.StorageApi;

/**
 * Created by Steven on 1/4/2016.
 */
public interface IKlass extends IKlassPart {
    String getName();
    void setVersion(int version);
    int getVersion();
    void setAccess(int access);
    int getAccess();

}
